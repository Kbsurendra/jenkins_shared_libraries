def call(String projectName, String imageTag, String dockerHubUser) {
    echo "Deployment started for ${projectName}:${imageTag}"

    withCredentials([usernamePassword(
        credentialsId: 'dockerHubCred',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh """
            set -e
            echo "\$DOCKER_PASS" | docker login \
                -u "\$DOCKER_USER" --password-stdin

            docker image tag "${projectName}:${imageTag}" \
                "${dockerHubUser}/${projectName}:${imageTag}"

            docker push "${dockerHubUser}/${projectName}:${imageTag}"

            docker logout
        """
    }

    echo "Deployment completed successfully"
}
