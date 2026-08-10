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
                // 1. CHANGE THIS to your actual repository URL
                git branch: 'main', url: 'https://github.com/nethmikulasooriya/productapi-new.git'
            }
        }
        stage('Build with Maven') {
            steps {
                // Compile and package the Spring Boot app
                bat 'mvn clean package -DskipTests'
            }
        }
        tage('SonarQube Analysis') {
            steps {
               
                    bat 'mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar-Dsonar.projectKey=sqa_df935212d0d9af44599a0dd57f0807638da1100e'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the image from the Dockerfile
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }
        stage('Stop Old Container') {
            steps {
                // Ignore failure if no container is currently running
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
                // 2. Port changed from 8080:8080 to 8500:8500 to match the app
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

