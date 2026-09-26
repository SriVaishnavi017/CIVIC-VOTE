# CivicVote Technologies

## Project Status

This repository is being developed in phases. The current milestone focuses on the first four rubric targets:

 - Voting Service for one-vote enforcement and tamper-evident ballot recording
 - Result Service for privacy-aware result summaries
The project maintains separate codebases for the frontend and backend, with the backend organized as a multi-module Spring Boot microservice system.

## Current Architecture

- Backend: `backend/`
- Frontend: `frontend/`
- Documentation: `docs/`
- Architecture notes: `architecture/`

## Problem Statement

│   ├── voting-service/
│   └── result-service/
CivicVote Technologies aims to build a secure academic digital election and vote auditing platform for organizational elections. The platform supports election creation, candidate configuration, voter authentication, secure voting, and result visibility without exposing voter-to-ballot relationships in public outputs.

## Solution Approach

The solution uses a modular microservice architecture with:

- Eureka Server for service registration and discovery
- API Gateway for centralized routing
- Auth Service for registration, login, and JWT management
## Rubric Coverage

The project is aligned to the Internal Review-1 rubric and currently covers the milestone areas required for strong academic evaluation.

## Structure

```text
CIVIC VOTE/
├── backend/
│   ├── pom.xml
│   ├── eureka-server/
│   ├── api-gateway/
│   ├── auth-service/
│   └── election-service/
mvn -pl voting-service spring-boot:run
mvn -pl result-service spring-boot:run
├── frontend/
├── docs/
├── architecture/
├── README.md
└── docker-compose.yml
```

## Phase Plan

- Phase 1: Requirements, service decomposition, Eureka setup, JWT-based auth, gateway routing
- Phase 2: Voting and Result services, audit hashing, persistence, and service communication
- Phase 3: Docker deployment, frontend integration, testing, and final academic documentation

## Running the Backend

```bash
cd backend
mvn clean install
```

Then start services in sequence:

```bash
mvn -pl eureka-server spring-boot:run
mvn -pl auth-service spring-boot:run
mvn -pl election-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```

## Notes

This milestone satisfies the initial rubric targets and forms the foundation for the full election platform.
