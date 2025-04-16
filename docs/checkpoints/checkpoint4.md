
Jenkis is a continuous integration and continuous delivery (CI/CD) tool that automates the process of building, testing, and deploying software. It is widely used in DevOps practices to streamline the software development lifecycle.

## Containerized Jenkins

In this checkpoint, we will set up a Jenkins server using Docker Compose. This will allow us to run Jenkins in a containerized environment, making it easy to manage and deploy.

``` { .tree .copy .select }
api/
jenkins/
    compose.yaml
```

??? info "Source"

    === "compose.yaml"

        ``` { .yaml .copy .select linenums="1" }
        --8<-- "https://raw.githubusercontent.com/Insper/platform/refs/heads/main/jenkins/compose.yaml"
        ```

<!-- termynal -->

To run this container:

``` { .bash .copy .select }
docker compose up -d --build
```

``` bash
jenkins/# docker compose up -d --build

[+] Running 2/2
 ✔ jenkins Created              0.1s 
 ✔ Container jenkins Started    0.2s 
```

Jenkins is now running on port 9080. You can access it by navigating to [http://localhost:9080/](http://localhost:9080/){target="_blank"} in your web browser.

## Jenkins Configuration

Once Jenkins is running, you will need to configure it. The first time you access Jenkins, you will be prompted to unlock it using an initial admin password.

!!! warning "Admin"
    Please, to avoid permission issues, run the console as administrator.

Setting the a number of executors to 10 will allow us to run two jobs in parallel. This is useful for speeding up the build process, especially when we have multiple projects or stages that can be executed concurrently.

![](../assets/images/jenkins.system.nexecutors.png){width=100%}


## Pipeline

In this checkpoint, we will create a Jenkins pipeline that will build and deploy our application, [Pipeline as Code](https://www.jenkins.io/doc/book/pipeline/pipeline-as-code/){target="_blank"}. The pipeline will be defined in a `Jenkinsfile` located in the root of our project.

![](../assets/images/jenkins.pipeline.png){width=100%}

``` { .tree .copy .select }
api/
    account/
        Jenkinsfile
```
??? info "Source"

    === "Jenkinsfile"

        ``` { .groovy .copy .select linenums="1" }
        --8<-- "https://raw.githubusercontent.com/Insper/platform/refs/heads/main/api/account/Jenkinsfile"
        ```

The `Jenkinsfile` defines the stages of our pipeline, including building the application, running tests, and deploying the application. Each stage can be customized to fit the needs of your project.
The pipeline can be triggered manually or automatically based on events such as code commits or pull requests. This allows for continuous integration and continuous delivery (CI/CD) of our application.


[^1]: [Jenkins](https://www.jenkins.io/doc/book/using/){target="_blank"} - Jenkins documentation.