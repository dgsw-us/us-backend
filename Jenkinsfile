pipeline {
    agent any

    stages {
        stage ('Docker Image Pull') {
            steps {
                script {
                    sshPublisher (
                        failOnError: true,
                        publishers: [
                            sshPublisherDesc (
                                configName: 'UBUNTU-RASP',
                                verbose: true,
                                transfers: [
                                    sshTransfer (
                                        execCommand: """
                                        echo Unkwonpassword1! | docker login -u gwon11225 --password-stdin
                                        docker pull gwon11225/us-backend:latest
                                        """
                                    )
                                ]
                            )
                        ]
                    )
                    sshPublisher (
                        failOnError: true,
                        publishers: [
                            sshPublisherDesc (
                                configName: 'UBUNTU-RASP',
                                verbose: true,
                                transfers: [
                                    sshTransfer (
                                        execCommand: """
                                        docker run gwon11225/us-backend:latest
                                        """
                                    )
                                ]
                            )
                        ]
                    )
                }
            }
        }
    }
}