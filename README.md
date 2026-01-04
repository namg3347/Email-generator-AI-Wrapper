📧 Email Generator API

An AI-powered Email Generator API built with Java Spring Boot, designed to generate professional, context-aware email responses.
This project demonstrates REST API design, AI integration, secure backend development, and clean code practices.

What I Built / Skills Demonstrated

Java + Spring Boot → REST API architecture, dependency injection, layered design

AI API Integration → Connected with LLMs (OpenAI/Gemini) for natural language email generation
Custom Email Prompting Engine → Supports tones (formal, casual, friendly, etc.) & additional remarks
Error Handling & Validation → Clean request/response structure
Scalability in Mind → Ready for Chrome extension / Gmail API integration
Version Control & CI/CD Ready → GitHub repo structured for professional deployment

Features

Generate instant professional replies from raw emails
Choose different tones (formal, casual, friendly, persuasive, etc.)
Add custom remarks for personalization
REST API endpoints for easy integration
Future-ready for authentication & SaaS deployment

API Usage
Endpoint: POST /api/v1/email/generate
Request:
{
  "email": "Dear HR, I would like to follow up on my job application...",
  "tone": "formal",
  "remarks": "Keep it polite and concise"
}

Response:
{
  "generatedEmail": "Dear HR,\n\nI hope this message finds you well. I am writing to kindly follow up on my job application..."
}

Getting Started
Prerequisites
Java 17+
Maven 3.8+
AI API key (OpenAI, Gemini, etc.)

Clone the repo
git clone https://github.com/namg3347/Email-generator-AI-Wrapper.git
cd Email-generator-AI-Wrapper

Build & Run
mvn clean install
mvn spring-boot:run


Server runs at: 👉 http://localhost:8080
