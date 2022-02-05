pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'echo \'Build\''
        git(url: 'https://github.com/Dhairya197/DummyRepo.git', branch: 'main')
      }
    }

    stage('Checkout') {
      steps {
        echo 'echo \'Checkout\''
        sh 'mvn checkout'
      }
    }

    stage('Smoke Tests') {
      steps {
        echo 'echo \'Smoke tests\''
        sh 'mvn test'
      }
    }

    stage('Reports') {
      parallel {
        stage('Reports') {
          steps {
            echo 'echo \'reports\''
          }
        }

        stage('Regression Testing') {
          steps {
            echo 'echo \'Regression\''
          }
        }

      }
    }

  }
}