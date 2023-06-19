pipeline {

    agent any

    parameters {
        extendedChoice(name: 'jobName', description: 'Select the job name', 
            type: 'PT_SINGLE_SELECT', 
            groovyScript: """
                return ['release', 'continuous', 'deploy', 'deploy_cloud', 'delivery_pipeline']
            """
        )
    }

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
