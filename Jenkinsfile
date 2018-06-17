node {
    checkout scm
    stage('Build') {
        bat 'gradle test'
    }
    stage('Test') {
        echo 'Building....'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}