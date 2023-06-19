pipeline {

    agent any

    stages {
        stage('Run job') {
            steps {
                script {
                    echo "The build job name is ${params.jobName}"
                    build job: "${params.jobName}"
                }
            }
        }
    }
}
