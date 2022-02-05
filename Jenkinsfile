pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'echo \'Build\''
        sh 'mvn build'
      }
    }

    stage('Checkout') {
      steps {
        echo 'echo \'Checkout\''
        git(url: 'https://github.com/Dhairya197/DummyRepo.git', branch: 'main')
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