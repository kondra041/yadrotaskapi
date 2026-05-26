# Yadro Task API

Веб-приложение для загрузки и отображения пользователей из внешнего API с поддержкой пагинации. Разработчик (исполнитель): Кондрюков Д.С.

## 1. Стек технологий

- Backend: Spring Boot 3 / Java 21
- Frontend: React (Vite)
- Database: PostgreSQL 16
- Docker / Docker Compose

## 2. Запуск проекта

#### Клонирование репозитория

```bash
git clone https://github.com/kondra041/yadrotaskapi.git
cd yadrotaskapi
```

#### Сборка и запуск всех сервисов (Docker)

```bash
docker-compose up --build
```

## 3. Доступ к сервисам

#### - Backend
http://localhost:8080
http://localhost:8080/swagger-ui.html (swagger API)

#### - Frontend
http://localhost:3000

#### - PostgreSQL
- **host:** localhost  
- **port:** 5434  
- **user:** postgres  
- **password:** postgres  
- **database:** yadro

## 4. Доступ к сервисам