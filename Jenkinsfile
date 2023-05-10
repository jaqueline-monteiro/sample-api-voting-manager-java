pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK'
    }
    
    stages {
        stage('Build') {
            agent {
                docker { image 'maven' }
            }
            steps {
                echo 'Building the application...'
                sh 'mvn -B -DskipTests clean install'
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
