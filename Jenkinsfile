pipeline {
    agent any

    stages {
        stage ('Checkout') {
            git branch: 'main',
                credentialsId: 'gwongithub',
                url: 'https://github.com/dgsw-us/us-backend.git'
        }

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
                                        echo password | docker login -u gwon11225 --password-stdin
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