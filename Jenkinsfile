pipeline {

    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JDK'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Code checkout completed'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

    }
}
