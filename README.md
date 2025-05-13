# How to Run Your Project

This project is a microservices-based application that leverages Docker Compose to orchestrate various services, including MySQL, Kafka, Elasticsearch, and Kibana. Additionally, it integrates 
Java-based microservices such as Eureka Server, API Gateway, and other services like Orders, Manufacture, Logistics, and Sales.

Follow these steps to get your project running:

## Prerequisites

Make sure you have the following installed on your system:

- **Docker** (for containerization)
- **Java 11 or higher** (for microservices and backend)
- **Maven** (for building and running Java-based microservices)


## Steps to Run the Project

### 1. Clone the repository

First, clone the project repository to your local machine:

```bash
git clone https://github.com/NikitaMarinov/diplomaWork
cd diplomaWork
```

### 2. Build Docker images

Before running the project, ensure that Docker images are built for all necessary services:

```bash
docker-compose build
```

### 3. Start the docker services

Run Docker Compose to start all services in the project:

```bash
docker-compose up -d
```

### 4. Run other application microservices

In addition to the core services like Eureka Server and API Gateway, you can run the other microservices manually. It's recommended to run them in the following order to ensure proper dependencies:

In the following order ->
1. Eureka service
2. All others  services
3. Order service

```bash
cd {microservice}
mvn spring-boot:run
cd ..
```

### 5. Access the services
Once the services are up and running, you can access them via the following URLs:

* Eureka Server: http://localhost:8761
* API Gateway: http://localhost:8080
* Kibana: http://localhost:5601
* Elasticsearch: http://localhost:9200

Swagger UI for the Microservices:

* Orders Service (Swagger): http://localhost:8081/swagger-ui.html
* Manufacture Service (Swagger): http://localhost:8082/swagger-ui.html
* Logistics Service (Swagger): http://localhost:8083/swagger-ui.html
* Sales Service (Swagger): http://localhost:8085/swagger-ui.html
* GateWay API Service (Swagger): http://localhost:8088/swagger-ui.html

Note:
* If your services are running on different ports, update the port number accordingly for each service.

### 6. Stopping the services

To stop the services, run the following command:

```bash
docker-compose down
```

This will stop all containers and remove them.

### 7. Conclusion

With these steps, your microservices-based project should be up and running with Docker Compose. If you have any questions or run into issues, feel free to open an issue on the GitHub repository.
