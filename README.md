```markdown
# Small Joys 🌿
> Share life's little wins. One joy at a time.

A lightweight Spring Boot application designed to provide daily doses of positivity. Built with Java 25, PostgreSQL, and Docker.

## ✨ Features
* **Random Joy Discovery:** Fetch a community-submitted "joy" from the database.
* **Modern UI:** Clean, responsive design with a "grass-green" theme.
* **Interactive API:** Built-in Swagger UI for testing endpoints.
* **Moderation Workspace:** Administrative tools for reviewing submissions.

## 🛠️ Tech Stack
* **Backend:** Java 25, Spring Boot, Spring Data JPA, Hibernate.
* **Database:** PostgreSQL (Hosted on Supabase).
* **Frontend:** Vanilla JS, HTML5, CSS3.
* **Containerization:** Docker (Multi-stage build).
* **Analytics:** Google Analytics 4.

## 🚀 Getting Started

### Prerequisites
* Docker & Docker Desktop installed.
* Access to a PostgreSQL database.

### Local Run via Docker
1. **Clone the repo:**
   ```bash
   git clone https://github.com/barch0206/joy-as-a-service.git
   ```

2. **Build the image:**
   ```bash
   docker build -t joy-service .
   ```

3. **Run the container:**
   Replace the placeholders with your actual database credentials.
   ```bash
   docker run -p 8080:8080 \
     -e DB_URL="postgresql://your-db-host.com:6543/postgres" \
     -e DB_USERNAME="your_username" \
     -e DB_PASSWORD="your_password" \
     joy-service
   ```

4. **Access the app:**
   * Web UI: http://localhost:8080
   * API Docs: http://localhost:8080/swagger-ui/index.html

## 📁 Project Structure
* `/src/main/java` - Spring Boot backend logic (Controllers, Models, Repositories).
* `/src/main/resources/static` - Frontend files (HTML, CSS, JS).
* `Dockerfile` - Multi-stage build configuration.
* `render.yaml` - Blueprint for cloud deployment.

## ⚖️ License
Distributed under the MIT License. See `LICENSE` for more information.
```
