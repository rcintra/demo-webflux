# demo-webflux
Spring Boot Reactive Web

## Running the application

Execute `mvnw spring-boot:run`.

### Docker

The docker folder contains a `docker-compose` file that runs the Mongo database, the backend application. It also contains a simplified version, `docker-compose-mongo-only.yml`, which runs only the MongoDB instance. This is useful in case you want to run the applications without docker (e.g. from your IDE).

## Running the applications with Docker

Make sure to build the applications first, from the `docker` folder:

`docker-compose build`

Then, you can run the set of containers with:

`docker-compose up`

After the services are executed, you can navigate to `localhost:8080` to see the applications running. If you're running Docker in a different machine (like when using a VM in Windows), replace `localhost` for the Docker machine IP.