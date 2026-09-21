# CivicVote Requirements and Problem Analysis

## 1. Problem Statement

Academic organizational elections require a secure, auditable, and privacy-aware process for collecting ballots and publishing results. The core challenge is balancing transparency, integrity, and voter privacy while maintaining a modular, distributed system that can be demonstrated in a live microservice environment.

## 2. Business Use Case

Administrators must be able to create elections, define candidates, activate voting periods, close elections, and review aggregated results. Voters must authenticate, view active elections, cast one vote per election, and receive confirmation of recording without exposing their private voting choices publicly.

## 3. Existing Problem

Traditional election workflows are often centralized, difficult to audit, and prone to duplication or privacy concerns. A monolithic system would increase coupling and make independent deployment and fault isolation harder to demonstrate.

## 4. Proposed Solution

A Spring Boot microservice design with Spring Cloud components, JWT-based authentication, API gateway routing, and service discovery through Eureka. This provides a modular architecture that supports clear responsibilities, independent deployment, and a secure demonstration flow.

## 5. Functional Requirements

- User registration and login
- Role-based access control for ADMIN and VOTER
- Election creation and lifecycle management
- Candidate management within an election
- Active election visibility for authenticated voters
- One vote per voter per election
- Result aggregation after voting closes
- Secure token-based authentication
- Audit verification for tamper-evident ballot chains

## 6. Non-Functional Requirements

- Maintain modular service boundaries
- Use stateless authentication
- Validate all incoming data
- Support load balancing and service discovery
- Provide clean API routing and centralization
- Keep frontend independent from backend service details
- Produce documentation suitable for academic review

## 7. Actors

- Admin
- Voter
- System Auditor
- API Gateway
- Eureka Service Registry

## 8. Use Cases

- Admin registers and logs in
- Admin creates an election
- Admin activates and closes an election
- Voter logs in and views active elections
- Voter casts a vote once
- System rejects duplicate vote attempts
- Admin retrieves election results and hash audit status

## 9. System Constraints

- Use Java and Spring Boot
- Use Spring Cloud microservice components
- Keep JWT stateless and role-aware
- Avoid direct exposure of voter identity in results
- Keep professional academic documentation accurate and transparent

## 10. Security Requirements

- BCrypt-based password hashing
- JWT signing and validation
- Role-based authorization
- Input validation for registration and elections
- Avoid plaintext secrets in code
- Protect authentication endpoints
- Handle unauthorized or invalid requests consistently

## 11. Privacy Requirements

- Do not expose voter-to-candidate mappings through public APIs
- Keep participation tracking separate from ballot content as far as practical
- Limit audit access to authorized admin users
- Document the privacy assumptions and technical limitations honestly

## 12. Availability Requirements

- Services should register with Eureka
- Gateway should route requests centrally
- Service discovery should reduce hard-coded service dependency
- Load balancing support should be demonstrated at the architecture level

## 13. Scalability Requirements

- Individual services should be independently deployable
- Multiple service instances can be added using service discovery
- Backend should remain modular and extendable for voting and result services

## 14. Data Integrity Requirements

- Validate election dates and candidate data
- Enforce one vote per voter per election using a unique constraint
- Track a tamper-evident hash chain for recorded votes
- Prevent invalid or duplicate submissions

## 15. Assumptions

- This is an academic election system, not a production public-election platform
- PostgreSQL is the target production database, while a lightweight in-memory database is used during early implementation and testing
- Gateway and discovery are used for demonstration and routing rather than full production-grade resilience

## 16. Limitations

- The platform is not designed for national-scale secure elections
- JWT-based stateless authentication requires secure secret management in production
- Hash-chain integrity indicates tamper evidence, not absolute proof against malicious actors with direct database access

## Requirement Traceability Summary

| Requirement | Service | API | Implementation | Test |
|---|---|---|---|---|
| Registration and login | Auth Service | /api/auth/register, /api/auth/login | User registration and JWT generation | Unit + integration |
| Election lifecycle | Election Service | /api/elections | Election CRUD and activation logic | Unit tests |
| JWT security | Auth Service | Security filter chain | BCrypt + JWT validation | Unit tests |
| Routing | API Gateway | /api/auth/**, /api/elections/**, /api/votes/**, /api/results/** | Spring Cloud Gateway discovery routes | Maven compile + manual verification |
| Service discovery | Eureka Server | Registry dashboard | Discovery client registration | Manual verification |

## Deliverables for This Milestone

- Project structure
- Service decomposition
- Requirements documentation
- Security-oriented auth design
- Gateway routing configuration
- Service discovery configuration
- Shared JWT enforcement in protected downstream services
- Authenticated inter-service result refresh
