pipeline {
    agent any

    parameters {
        choice(
            name: 'TEST_SCENARIO',
            choices: ['test', 'sanityTest', 'negativeTest'],
            description: 'Какую задачу запустить'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'В каком браузере запустить тесты'
        )
    }

    tools {
        allure 'allure-2.22.0'
    }

    options {
            gitConnectionTimeout(60)
            skipDefaultCheckout(true)
        }

    stages {
        stage('Check network') {
            steps {
                powershell 'Test-Connection github.com -Count 3'
                powershell 'Test-NetConnection github.com -Port 443 -InformationLevel Quiet'
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/InnaRomankova/Onliner-UI-Test.git'
            }
        }

        stage('Verify Source Code') {
            steps {
                echo "--- Checking WebDriverFactory.java ---"
                powershell '''
                    Select-String -Path "src/test/java/by/onliner/core/driver/WebDriverFactory.java" -Pattern "BROWSER_NAME" -Context 0,5
                '''
            }
        }

        stage('Install Dependencies') {
            steps {
                powershell './gradlew --no-daemon dependencies'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        def browser = params.BROWSER
                        def task = params.TEST_SCENARIO

                        echo "Running ${task} on ${browser}"
                        powershell "./gradlew clean ${task} -Dbrowser=${browser} --no-daemon"

                    } catch (Exception e) {
                        echo "Tests failed! Saving logs..."
                        powershell 'Get-Content chromedriver.log -ErrorAction SilentlyContinue'
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
        }
    }
}