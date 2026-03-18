pipeline {
    agent any

    environment {
        GIT_REPO_URL   = 'https://github.com/epk4429/my-springboot-repo.git'
        GIT_BRANCH     = 'main'

        APP_NAME        = 'skala-springboot-app'
        IMAGE_NAME      = 'skala-springboot-app'
        IMAGE_TAG       = "${BUILD_NUMBER}"
        CONTAINER_NAME  = 'skala-springboot-app'

        APP_PORT       = '8080'
        HOST_PORT      = '8080'
    }

    stages {
        stage('Checkout from GitHub') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO_URL}"
            }
        }

        stage('Build Jar') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar -x test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                    docker rm -f $CONTAINER_NAME || true

                    docker run -d \
                      --name $CONTAINER_NAME \
                      -p $HOST_PORT:$APP_PORT \
                      $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }
    }

    post {
        success {
            echo "백엔드 App 배포 성공: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "백엔드 App 배포 실패"
        }
    }
}