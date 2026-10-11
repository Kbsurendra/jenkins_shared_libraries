def call(String projectName,String imageTag,String dockerHubUser){
  echo "image build started"
  sh "docker build -t ${dockerHubUser}/${projectName}:${imageTag}
  echo "docker image created"
}
