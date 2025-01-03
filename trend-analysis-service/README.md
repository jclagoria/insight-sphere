# TrendMapper - Trend Analysis Service

TrendMapper is the trend analysis microservice for the InsightSphere platform. It processes news articles to identify time-series trends, enabling users to explore patterns and insights over time for specific topics or keywords.

---

## Features

- Perform time-series analysis on keyword or topic-based news data.
- Identify patterns using ARIMA or seasonal decomposition algorithms.
- Consume sentiment-enriched articles from RabbitMQ (`sentiment-queue`).
- Store computed trends in PostgreSQL for fast retrieval.
- Publish trend insights to RabbitMQ (`trend-queue`) for downstream services.

---

## Architecture Overview

TrendMapper integrates with:
- **RabbitMQ**: To subscribe to sentiment-enriched article events and publish trend data.
- **PostgreSQL**: To store computed trends for quick retrieval and reporting.
- **Time-Series Libraries**: For advanced trend detection and seasonal analysis.

---

## API Endpoints

### `/analyze`
Manually trigger trend analysis for a specific keyword or topic.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "keyword": "example",
    "fromDate": "2025-01-01",
    "toDate": "2025-01-10"
  }
```

Response: HTTP 200 OK with trend insights.
```json
{
  "keyword": "example",
  "trend": [
    {"date": "2025-01-01", "count": 120},
    {"date": "2025-01-02", "count": 150}
  ]
}
```

/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.

Environment Variables
Variable Name		Description					Example Value
POSTGRES_URI		PostgreSQL connection string.			jdbc:postgresql://localhost:5432
RABBITMQ_URI		RabbitMQ connection string.			amqp://localhost
SENTIMENT_QUEUE		Queue name for consuming sentiment data.	sentiment-queue
TREND_QUEUE		Queue name for publishing trend insights.	trend-queue

Running the Service Locally
Prerequisites
Java 21
RabbitMQ and PostgreSQL running locally or accessible remotely.
Docker (optional for containerized setup).

Steps
Using Maven
1. Clone the repository:
```bash
git clone https://github.com/your-repo/trend-mapper.git
cd trend-mapper
```

2. Run the application:
```bash
mvn spring-boot:run
```

Using Docker
1. Build the Docker image:
```bash
docker build -t trend-mapper .
```

2. Run the container:
```bash
docker run -d --name trend-mapper -e POSTGRES_URI=jdbc:postgresql://host:port -e RABBITMQ_URI=amqp://host -e SENTIMENT_QUEUE=sentiment-queue -e TREND_QUEUE=trend-queue trend-mapper
```

Integration with InsightSphere
TrendMapper works seamlessly within the InsightSphere ecosystem by:

1. Consuming sentiment-enriched articles from RabbitMQ (sentiment-queue).
2. Performing advanced time-series analysis using ARIMA or seasonal decomposition.
3. Storing computed trends in PostgreSQL for efficient retrieval.
4. Publishing trend insights to RabbitMQ (trend-queue).

