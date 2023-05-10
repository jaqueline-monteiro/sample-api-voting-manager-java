pipeline {
    agent none

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8.1'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building the application...'
                sh "mvn package -Dmaven.test.skip=true"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing the application...'
                // Here you can add specific commands to test your project, like 'gradle test' for a Gradle project
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Here you can add specific commands to deploy your project in a specific environment
            }
        }
    }
}
