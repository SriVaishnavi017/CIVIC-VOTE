# Requirement-to-Rubric Mapping

## Rubric 1: Problem Analysis and Requirement Specification

- Requirement document created in `docs/requirements.md`
- Includes problem analysis, actors, constraints, privacy, accessibility, and traceability
- Suitable for senior academic review and internal presentation

## Rubric 2: Microservice Identification and Service Discovery

- Backend split into separate Auth, Election, Voting, Result, Gateway, and Eureka modules
- Eureka server included for service registration and discovery
- Each business service is independently deployable and configured as an Eureka client
- API gateway centralizes routing and reduces direct client coupling
- Voting-to-result communication uses an OpenFeign client resolved through Eureka

## Rubric 3: JWT Authentication

- Auth service implements user registration and login
- BCrypt hashing is used for password storage
- JWT generation and validation are implemented with a shared secret across Auth, Voting, and Result services
- Authorization rules enforce ADMIN and VOTER roles
- Voter identity is taken from the validated JWT subject rather than a client-supplied identity header

## Rubric 4: API Gateway Configuration

- Gateway routes requests to Auth, Election, Voting, and Result services
- Centralized port 8080 is used for external access
- Routes support discovery-based addressing and protected downstream security

## Evidence Collection Points

- Documentation files under `docs/`
- Spring Boot microservices in `backend/`
- Gateway routes in `backend/api-gateway`
- Eureka configuration in `backend/eureka-server`
- Auth security logic in `backend/auth-service`

## Verification Evidence

- Backend Maven tests pass for Auth, Voting, and Result services.
- JWT service unit coverage passes in Auth Service.
- Result refresh requires the configured internal service key.
- Docker remains intentionally deferred.
