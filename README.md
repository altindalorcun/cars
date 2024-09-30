# A simple Microservice Project

This is a simple microservice project, where I demonstrate the use of some technologies. The microservice architecture consists of several services that interact with each other using gRPC and Feign Client for communication and Spring Cloud Config for centralized configuration. Secrets such as database passwords are managed through Vault. Zipkin is used to trace requests across services to monitor performance and detect issues.

## Technologies Used
- **Java 17**
- **Spring Boot 3.0**
    - Spring Web
    - Spring Cloud Config
- **Zipkin** - Distributed tracing for microservices.
- **gRPC** - Communication between microservices (Between garage-service and car-service).
- **HashiCorp Vault** - Secrets management for securely handling sensitive data.

## Setup

### How to Start
```bash
docker-compose up -d
```
Visit http://localhost:8200 to ensure Vault is unsealed. Once Vault is unsealed, the remaining microservices will start.

## Configuration

### Important Step: Clearing Vault Volume

If Vault has been previously initialized or you are setting it up for the first time, it is essential to clear the volume files to ensure that Vault is initialized from scratch, and you can create your own token.

### Start the Vault
```bash
docker-compose up -d vault
```
- Visit http://localhost:8200
- You need to create an initial token that will be used by your microservices to access Vault securely.
- Copy the generated token and set it as an environment variable on your local machine:
```
export VAULT_TOKEN=your-generated-token
```
- The folder structure for secrets in Vault will look like this:
```
secret/
├── car-service
│   ├── dev
│   └── prod
│       ├── db.password
│       ├── db.url
│       └── db.username
├── garage-service
│   ├── dev
│   └── prod
│       ├── db.password
│       ├── db.url
│       └── db.username
└── user-service
    ├── dev
    │   └── count
    └── prod
        ├── db.password
        ├── db.url
        ├── db.username
        └── count
```

## Usage
- Once all services are up, you can interact with the microservices through gateway endpoints.
- Use Zipkin at http://localhost:9411/zipkin/ to monitor and trace the requests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.