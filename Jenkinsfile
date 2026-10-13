
pipeline {
    agent any

    environment {
    ```groovy
    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.12.1'
        PATH = 'C:\\Tools\\apache-maven-3.10.0-bin\\apache-maven-3.10.0\\bin;C:\\Program Files\\Java\\jdk-21.0.12.1\\bin;C:\\Windows\\System32;C:\\Windows'
    }
```
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        always {
            junit(
                testResults: 'course-registration-app/target/surefire-reports/TEST-*.xml',
                allowEmptyResults: true
            )
        }

        success {
            archiveArtifacts(
                artifacts: 'course-registration-app/target/*.jar',
                fingerprint: true
            )
        }
    }
}
