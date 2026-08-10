pipeline {
    agent any
    tools {
        maven 'maven3'
        jdk 'jdk21'
    }
    environment {
        IMAGE_NAME = "product-api"
        CONTAINER_NAME = "product-api-container"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/nethmikulasooriya/productapi-new.git'
            }
        }
        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                bat 'mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=sqa_df935212d0d9af44599a0dd57f0807638da1100e'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }
        stage('Stop Old Container') {
            steps {
                bat 'docker stop %CONTAINER_NAME% || exit 0'
            }
        }
        stage('Remove Old Container') {
            steps {
                bat 'docker rm %CONTAINER_NAME% || exit 0'
            }
        }
        stage('Run New Container') {
            steps {
                bat 'docker run -d -p 8500:8500 --name %CONTAINER_NAME% %IMAGE_NAME%'
            }
        }
    }
    post {
        success {
            echo 'Deployment successful.'
        }
        failure {
            echo 'Pipeline failed — check console output.'
        }
    }
}