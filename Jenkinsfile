pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'echo \'Build\''
      }
    }

    stage('Checkout') {
      steps {
        echo 'echo \'Checkout\''
      }
    }

    stage('Smoke Tests') {
      steps {
        echo 'echo \'Smoke tests\''
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