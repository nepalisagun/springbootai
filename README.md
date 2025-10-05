# Spring AI Microservices

A microservices architecture project using Spring Boot and Spring AI.

## Project Structure

- `ai-service/` - AI service microservice using Spring AI and OpenAI
- `gateway-service/` - API Gateway service using Spring Cloud Gateway
- `shared-common/` - Shared common utilities, DTOs, and models
- `user-service/` - User management service with JPA and H2 database

## Services

### Gateway Service (Port 8080)
- API Gateway that routes requests to appropriate microservices
- Routes `/api/users/**` to User Service
- Routes `/api/ai/**` to AI Service

### User Service (Port 8081)
- User management with CRUD operations
- H2 in-memory database
- RESTful API endpoints
- H2 Console available at `/h2-console`

### AI Service (Port 8082)
- AI text generation using Spring AI
- OpenAI integration
- Chat and text generation endpoints

### Shared Common
- Common DTOs and utilities
- ApiResponse wrapper for consistent API responses
- Validation annotations

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- OpenAI API Key (for AI service)

### Running the Application

1. **Clone the repository**
   ```bash
   git clone https://github.com/nepalisagun/springbootai.git
   cd springbootai
   ```

2. **Set up OpenAI API Key**
   ```bash
   export OPENAI_API_KEY=your-api-key-here
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run individual services**
   ```bash
   # Terminal 1 - Gateway Service
   cd gateway-service
   mvn spring-boot:run
   
   # Terminal 2 - User Service
   cd user-service
   mvn spring-boot:run
   
   # Terminal 3 - AI Service
   cd ai-service
   mvn spring-boot:run
   ```

5. **Or run with Docker**
   ```bash
   docker-compose up --build
   ```

### API Endpoints

#### User Service
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

#### AI Service
- `POST /api/ai/generate` - Generate AI response with full request
- `POST /api/ai/chat` - Simple chat endpoint
- `GET /api/ai/health` - Health check

#### Gateway Service
- All user and AI endpoints are accessible through the gateway at port 8080

## Technologies

- **Spring Boot 3.2.0** - Main framework
- **Spring AI 1.0.0-M4** - AI integration
- **Spring Cloud Gateway** - API Gateway
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Maven** - Build tool
- **Docker** - Containerization
- **OpenAI API** - AI text generation

## Development

### Project Structure
```
spring-ai-microservices/
├── pom.xml                    # Parent POM
├── shared-common/             # Shared module
│   ├── pom.xml
│   └── src/main/java/com/example/springai/common/
├── user-service/              # User microservice
│   ├── pom.xml
│   └── src/main/java/com/example/springai/user/
├── ai-service/                # AI microservice
│   ├── pom.xml
│   └── src/main/java/com/example/springai/ai/
├── gateway-service/           # Gateway microservice
│   ├── pom.xml
│   └── src/main/java/com/example/springai/gateway/
├── Dockerfile
├── docker-compose.yml
└── README.md
```

### Adding New Features
1. Create DTOs in `shared-common` module
2. Implement business logic in respective service
3. Add REST endpoints
4. Update gateway routes if needed
5. Test with Docker Compose

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
