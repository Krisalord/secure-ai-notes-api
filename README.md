# Secure AI Notes API

A secure, AI-powered notes API built with Kotlin, Ktor & MongoDB — featuring JWT auth, rate limiting, and AI summarization.

## 1 Features

✅ User registration and login  
✅ CRUD Notes management  
✅ AI-powered notes summarization  
✅ Rate limiting on AI endpoints  
✅ Centralized error handling  
✅ Modular, maintainable architecture

## 2 Tech Stack

- **Language & Framework:** Kotlin, Ktor
- **Database:** MongoDB (KMongo)
- **Authentication:** JWT
- **AI Service:** OpenAI API
- **Security & Validation:** Input validation, Rate limiting

## 3 Quick Start

1. Clone & Configure
```bash
  git clone https://github.com/yourusername/secure-ai-notes-api.git
  cd secure-ai-notes-api
```
2. Set your config in application.yaml
```bash
  ktor:
    application:
    modules:
      - io.github.krisalord.ApplicationKt.module
    deployment:
      port: 8080
    mongo:
      uri: "your_mongodb_url"
    openai:
      apiKey: "your_openai_api_key"
```
3. Run
```bash
  /gradlew run
```
API runs on `http://localhost:8080`

## 4 Configuration

The app requires:

- `KTOR_MONGO_URI` — MongoDB connection string
- `OPENAI_API_KEY` — OpenAI API key for AI summarization

You can set these in `application.yaml`

## 5 Example Requests

### Register
Request
```bash
POST /register
{
  "email": "user@example.com",
  "password": "securePass123"
}
```
### Login
Request
```bash
POST /login
{
  "email": "user@example.com",
  "password": "securePass123"
}
```
Response
```bash
{ "token": "JWT_TOKEN_HERE" }
```
### Create Note
Request
```bash
POST /notes
Authorization: Bearer JWT_TOKEN_HERE
{
  "title": "My Note",
  "content": "Important note content"
}
```
### Summarize with AI
Request
```bash
POST /ai/summarize
Authorization: Bearer JWT_TOKEN_HERE
```
Response
```bash
{
  "summary": "Key points of your notes..."
}
```

## 6 Future Improvements

Add unit & integration tests for full test coverage