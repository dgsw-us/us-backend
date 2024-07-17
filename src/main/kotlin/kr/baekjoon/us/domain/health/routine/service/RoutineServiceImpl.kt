package kr.baekjoon.us.domain.health.routine.service

import jakarta.transaction.Transactional
import kr.baekjoon.us.domain.health.routine.domain.Do
import kr.baekjoon.us.domain.health.routine.domain.Routine
import kr.baekjoon.us.domain.health.routine.dto.*
import kr.baekjoon.us.domain.health.routine.repository.DoRepository
import kr.baekjoon.us.domain.health.routine.repository.ExerciseRepository
import kr.baekjoon.us.domain.health.routine.repository.RoutineRepository
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.hibernate.Hibernate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.security.Principal

@Service
@Transactional
class RoutineServiceImpl(
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val doRepository: DoRepository,
): RoutineService {
    override fun createRoutine(request: CreateRoutineRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val routine = Routine(
            name = request.name,
            writer = user,
        )

        routineRepository.save(routine)
    }

    override fun createRoutine(routineId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")
        val routine = routineRepository
            .findByIdOrNull(routineId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "루틴을 찾을 수 없음")

        val doUser = Do(user = user, routine = routine)

        doRepository.save(doUser)
    }

    override fun getRoutineList(principal: Principal): List<RoutineResponse> {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        val doList = doRepository.findByUser(user)

        print(doList)

        return doList.map {
            RoutineResponse(
                id = it.routine.routineId!!,
                name = it.routine.name,
                exerciseList = it.routine.exerciseList.map { exercise ->
                    ExerciseResponse(
                        id = exercise.id!!,
                        name = exercise.name,
                    )
                }
            )
        }
    }

    override fun getRoutineList(): List<RoutineResponse> {
        return routineRepository
            .findAll()
            .map {
                RoutineResponse(
                    id = it.routineId!!,
                    name = it.name,
                    exerciseList = it.exerciseList.map { exercise ->
                        ExerciseResponse(
                            id = exercise.id!!,
                            name = exercise.name,
                        )
                    }
                )
            }
    }

    override fun updateRoutine(request: UpdateRoutineRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val routine = routineRepository
            .findByIdOrNull(request.id)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "루틴을 찾을 수 없음")

        if (user.id != routine.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없음.")
        }

        routineRepository.save(routine.copy(
            name = request.name
        ))
    }

    override fun deleteRoutine(routineId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val routine = routineRepository
            .findByIdOrNull(routineId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "루틴을 찾을 수 없음")

        if (user.id != routine.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없음.")
        }

        doRepository.deleteAll(doRepository.findByRoutine(routine))
        exerciseRepository.deleteAll(exerciseRepository.findByRoutine(routine))
        routineRepository.delete(routine)
    }

    override fun createExercise(request: CreateExerciseRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val routine = Routine(
            name = request.name,
            writer = user
        )

        routineRepository.save(routine)
    }

    override fun updateExercise(request: UpdateExerciseRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val exercise = exerciseRepository
            .findByIdOrNull(request.id)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "운동을 찾을 수 없음.")

        if (user.id != exercise.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한 없음.")
        }

        exerciseRepository.save(exercise.copy(
            name = request.name
        ))
    }

    override fun deleteExercise(exerciseId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val exercise = exerciseRepository
            .findByIdOrNull(exerciseId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "운동을 찾을 수 없음.")

        if (user.id != exercise.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한 없음.")
        }

        exerciseRepository.delete(exercise)
    }
}