pipeline {

    agent any

    parameters {
        string(name: 'jobName', defaultValue: 'release', description: 'The name of the build job')
    }

    stages {
        stage('Build') {
            steps {
                script {
                    echo "The build job name is ${params.jobName}"
                    build job: "${params.jobName}"
                }
            }
        }
    }
}