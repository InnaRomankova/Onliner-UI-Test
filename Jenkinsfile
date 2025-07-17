pipeline {
    agent any

    parameters {
        choice(
        name: 'TEST_SCENARIO',
        choices: [
            'test',
            'sanityTest',
            'negativeTest'
            ],
            description: 'Какую задачу запустить'
            )
        choice(
        name: 'BROWSER',
        choices: [
            'chrome',
            'firefox'
            ],
            description: 'В каком браузере запустить тесты'
            )
    }

    tools {
        allure 'allure-2.22.0'
    }

    stages {
        stage('Check network') {
            steps {
                sh 'ping -c 3 github.com'
                sh 'curl -I --connect-timeout 5 https://github.com'
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/InnaRomankova/Onliner-UI-Test.git'
            }
        }

        stage('Verify Source Code') {
            steps {
                echo "--- Checking WebDriverFactory.java ---"
                sh '''
                    grep -A 5 "BROWSER_NAME" src/test/java/by/onliner/core/driver/WebDriverFactory.java || true
                '''
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew --no-daemon dependencies'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        def browser = params.BROWSER
                        def task = params.TEST_SCENARIO

                        echo "Running ${task} on ${browser}"
                        sh "./gradlew clean ${task} -Dbrowser=${browser} --no-daemon"

                    } catch (Exception e) {
                        echo "Tests failed! Saving logs..."
                        sh 'cat chromedriver.log || echo "No chromedriver.log"'
                        archiveArtifacts artifacts: '**/screenshots/*.png', allowEmptyArchive: true
                        error("Tests failed!")
                    }
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: "allure-results"]],
                    reportBuildPolicy: 'ALWAYS'
                ])
            }
        }
    }

    post {
        always {
            echo "Pipeline status: ${currentBuild.result}"
            archiveArtifacts artifacts: '**/build/reports/**/*', allowEmptyArchive: true
        }
        failure {
            emailext (
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Тесты упали: ${env.BUILD_URL}",
                to: 'team@example.com'
            )
            slackSend channel: '#ci-alerts', message: "❌ ${env.JOB_NAME} #${env.BUILD_NUMBER} упал!"
        }
    }
}