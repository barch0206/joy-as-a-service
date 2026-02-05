
# Small Joys 🍀
> Share life's little wins. One joy at a time.

A lightweight Spring Boot application designed to provide daily doses of positivity. Built with Java 25, PostgreSQL, and Docker.

## Features
* Randomization (Custom SQL to fetch a random joy)
* Moderation (Admin approval/rejection workflow)
* Persistence (PostgreSQL/Supabase data storage)
* Limiting (Resilience4j API protection)
* Containerization (Docker multi-stage builds)
* Documentation (Swagger/OpenAPI interactive UI)
* Analytics (GA4 user behavior tracking)
* Validation (Spring Boot input scrubbing)
* Pagination (Efficient admin data handling)
* Internal API docs(swagger UI)

## 🛠️ Tech Stack
* Backend: Java 25, Spring Boot, Spring Data JPA, Hibernate.
* Database: PostgreSQL (Hosted on Supabase).
* Frontend: Vanilla JS, HTML5, CSS3.
* Containerization: Docker (Multi-stage build).
* Analytics: Google Analytics 4.

## Getting Started

### Prerequisites
* Docker & Docker Desktop installed.
* Access to a PostgreSQL database.

### Local Run via Docker
1. Clone the repo:
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

---

## 🔌 API Reference

### 1. Fetch a Random Joy

Returns a single, moderated "joy" from the database.

* **Endpoint:** `GET /api/joy`
* **Request:**
```bash
curl -X GET http://localhost:8080/api/joy

```


* **Response (200 OK):**
```json
{
  "content": "Finding a five-dollar bill in an old pair of jeans."
}

```

---

### 2. Submit a New Joy

Allows users to suggest a new small joy. Submissions are flagged as `moderated: false` by default.

* **Endpoint:** `POST /api/submit`
* **Request Body:**
```json
{
  "content": "The smell of fresh rain on hot pavement."
}

```


* **Response (200 Ok):**
---

### 3. Check Moderation Queue

Retrieves a partial list of pending joys for review.

* **Endpoint:** `GET /api/moderate/queue`
* **Response (200 OK):**
```json
[
  { "content": "I had the best morning coffee today!" },
  { "content": "Unfiltered chaos" }
]

```

---

### 4. Moderate a Joy

Updates the status of a submission (Approve/Reject).

* **Endpoint:** `POST /api/moderate/approve` & `POST /api/moderate/reject`
* **Path Variable:** `id` (Long)
* **Request Body:**
```json
{
  "id" : 100
}

```

* **Response (200 OK):**

---

### 🛡️ Rate Limiting

To ensure fair use, the `/api/joy` and `api/moderate` endpoints are limited via **Resilience4j**:

* **Limit:** 5 requests per 10 seconds.
* **Response (429 Too Many Requests):**
```text
Rate limit exceeded. Please wait 10 seconds.

```
---

### 📖 Interactive Documentation

For a live environment to test these requests, start the application and visit:
`http://localhost:8080/swagger-ui/index.html`

## ⚖️ License
Distributed under the MIT License. See `LICENSE` for more information.
```
