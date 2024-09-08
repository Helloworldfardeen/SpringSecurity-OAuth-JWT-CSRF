# SpringSecurity-OAuth-JWT-CSRF
Overview
This project demonstrates the integration of Spring Security with OAuth (Google and GitHub), JWT for token-based authentication, and CSRF protection. The application secures web requests by authenticating users through third-party providers and ensuring secure token-based access to protected resources.

Features
Google OAuth: User authentication via Google.
GitHub OAuth: User authentication via GitHub.
JWT Authentication: Secure token-based authentication for accessing APIs.
CSRF Protection: Cross-site request forgery protection enabled by Spring Security.
Tech Stack
Java 17
Spring Boot
Spring Security
JWT (JSON Web Tokens)
OAuth 2.0
Maven
Process
The work is ongoing and involves:

Google OAuth: Integration is completed and functional.
GitHub OAuth: Under development but the foundation is laid.
JWT: Implemented for secure access post OAuth login.
CSRF: Enabled for protection against cross-site request forgery.
How to Run
Clone the repository:

bash
Copy code
git clone  https://github.com/Helloworldfardeen/SpringSecurity-OAuth-JWT-CSRF.git
cd SpringSecurity-OAuth-JWT-CSRF
Configure your Google and GitHub OAuth credentials in the application.properties.

Run the application:

bash
Copy code
mvn spring-boot:run
Access the app at http://localhost:8080
