pipeline {
    agent any

    environment {
        POM_VERSION = ""
    }

    stages {
        stage('Build') {
            agent {
                docker { image 'maven:3.8.1-openjdk-11' }
            }
            steps {
                sh "mvn package -Dmaven.test.skip=true"
                script {
                    pom = readMavenPom file: 'pom.xml'
                    env.POM_VERSION = pom.version
                }
                stash includes: '**/*', name: 'source'
            }
        }

    }
}
