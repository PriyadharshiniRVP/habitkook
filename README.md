Habitkook 💕
AI‑powered habit tracker with a gentle, supportive coach.

🧠 What is Habitkook?
Habitkook is a simple web app where you log daily habits and get short AI insights and grounding tips.
It’s built as a full‑stack project with a Java backend, MySQL, and a local AI model (via Ollama) for privacy‑friendly insights.

✨ Features
Log habits with name + mood (1–10)

Store logs in a MySQL database

AI “coach” endpoint that:

reads your habit history

generates 1 pattern insight, 1 tiny next action, and 1 short grounding tip

Minimal pink UI in plain HTML with inline styles (no external CSS)

All AI runs locally on your machine (no cloud API keys)

🛠 Tech Stack
Backend: Spring Boot 3 (Web, Spring Data JPA)

Database: MySQL

AI: Spring AI + Ollama + phi3:mini local model

Frontend: Static index.html served by Spring Boot (HTML + inline styles, no build tooling)
