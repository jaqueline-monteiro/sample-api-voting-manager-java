pipeline {
    agent any

    environment {
        POM_VERSION = ""
    }

    stages {
        stage('Build') {
            agent {
                docker { image 'maven' }
            }
            steps {
                sh "mvn package -Dmaven.test.skip=true"
                script {
                    pom = readMavenPom file: 'pom.xml'
                    POM_VERSION = pom.version
                }
                stash 'source'
            }
        }

    }
}
