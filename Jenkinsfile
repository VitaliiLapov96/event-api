pipeline {

    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
//
//     environment {
//         DOCKERHUB_CREDENTIALS = credentials('dockerhub')
//     }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

//         stage('Push to Docker Hub') {
//             steps {
//                 sh 'docker build -t vetal96/event-core .'
//                 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
//                 sh 'docker push vetal96/event-core'
//             }
//         }
//
//         stage('Push to Docker Hub') {
//             steps {
//                 sh 'docker build -t vetal96/event-core .'
//                 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
//                 sh 'docker push vetal96/event-core'
//             }
//         }
//
//         stage('Deploy') {
//             steps {
//                 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
//                 sh 'docker pull vetal96/event-core'
//                 sh 'docker run -d -p 8080:8080 vetal96/event-core'
//             }
//         }

    }
}