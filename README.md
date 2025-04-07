# Kotlin Client-Server Application with Docker

This project demonstrates a simple client-server architecture built with Kotlin and Ktor, containerized using Docker and orchestrated with Docker Compose.

## Project Structure

```
lab3_docker_compose/
├── client/               # Client application
│   ├── src/              # Client source code
│   ├── build.gradle.kts  # Client build configuration
│   └── Dockerfile        # Client Docker configuration
├── server/               # Server application
│   ├── src/              # Server source code
│   │   └── resources/    # Server resources (sample.txt)
│   ├── build.gradle.kts  # Server build configuration
│   └── Dockerfile        # Server Docker configuration
├── docker-compose.yml    # Docker Compose configuration
└── README.md             # This file
```

## Features

- **Server**: Kotlin-based web server using Ktor framework
  - Serves static text content from a file
  - Provides a simple HTML web page
  - REST API endpoint for retrieving text content

- **Client**: Kotlin application that connects to the server
  - Makes HTTP requests to the server
  - Retrieves and displays text content

- **Docker Configuration**:
  - Multi-stage builds for optimized container images
  - Non-root user execution for enhanced security
  - Health checks for robust container orchestration
  - Volume mounting for dynamic resource updates

## Prerequisites

- [Docker](https://www.docker.com/get-started) (version 20.10.0 or higher)
- [Docker Compose](https://docs.docker.com/compose/install/) (version 2.0.0 or higher)

## Getting Started

1. **Clone the repository (if applicable)**

2. **Build and start the containers**:

   ```bash
   docker-compose up --build
   ```

   This command builds both the client and server images and starts the containers.

3. **Access the application**:

   - The server is available at [http://localhost:8080](http://localhost:8080)
   - Web interface: [http://localhost:8080/website](http://localhost:8080/website)
   - Text content: [http://localhost:8080/text](http://localhost:8080/text)

## How It Works

1. The server container starts and hosts a Ktor web server on port 8080
2. The client container connects to the server using the internal Docker network
3. The client requests the content of `sample.txt` from the server
4. The server reads the file from its mounted resources volume and returns the content
5. The client displays the received text content

## Docker Configuration Details

### Server Container

- **Base Image**: Eclipse Temurin JRE 21 Alpine (lightweight JRE)
- **Security**: Runs as non-root user for enhanced security
- **Volume Mounting**: Resources directory is mounted from host for dynamic updates
- **Health Check**: Periodic health checks ensure the server is operational
- **Optimization**: Multi-stage build to minimize final image size

### Client Container

- **Base Image**: Eclipse Temurin JRE 21 Alpine (lightweight JRE)
- **Security**: Runs as non-root user for enhanced security
- **Dependencies**: Waits for the server to be healthy before starting
- **Optimization**: Multi-stage build to minimize final image size

## Docker Compose Configuration

- **Networking**: Custom bridge network for secure service communication
- **Dependencies**: Client depends on server's health status
- **Restart Policies**: Configured for robustness in case of failures
- **Resource Limiting**: Memory constraints for Java processes

## Customization

### Modifying Server Resources

You can modify the content of `sample.txt` in `server/src/main/resources/` directory. The changes will be immediately available due to volume mounting.

### Scaling

To run multiple client instances:

```bash
docker-compose up -d --scale client=3
```

## Troubleshooting

### Server Not Accessible

1. Check if containers are running:
   ```bash
   docker-compose ps
   ```

2. Check server logs:
   ```bash
   docker-compose logs server
   ```

3. Ensure port 8080 is not in use by another application

### Client Cannot Connect to Server

1. Check client logs:
   ```bash
   docker-compose logs client
   ```

2. Verify that the server health check is passing
   ```bash
   docker inspect kotlin-server | grep -A 10 Health
   ```


## Acknowledgements

- Kotlin Programming Language
- Ktor Framework
- Docker and Docker Compose