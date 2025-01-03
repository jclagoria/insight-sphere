# SummaryForge - Data Aggregation Service

SummaryForge is the summarization microservice of the InsightSphere platform. It generates concise summaries from news articles, enabling users to quickly grasp key insights without reading full articles.

---

## Features

- **Article Summarization**: Condense articles into concise summaries using advanced Natural Language Processing (NLP) techniques.
- **Event-Driven Processing**: Consume raw article events and publish summarized data to RabbitMQ.
- **Customizable Summarization Length**: Allow users to specify the desired summary length.

---

## Architecture Overview

SummaryForge integrates with:
- **RabbitMQ**: To subscribe to raw article events (`raw-news-queue`) and publish summarized data (`summary-queue`).
- **NLP Models**: To perform extractive or abstractive summarization.
- **MongoDB**: To store original and summarized articles for future queries.

---

## API Endpoints

### `/summarize`
Manually trigger summarization for a specific article.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "articleId": "12345",
    "text": "This is the full text of the article to summarize.",
    "summaryLength": 100
  }
```

Response: HTTP 200 OK with the generated summary.
```json
{
  "articleId": "12345",
  "summary": "This is the summary of the article."
}
```

/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.

Environment Variables
Variable Name	Description					Example Value
RABBITMQ_URI	RabbitMQ connection string.			amqp://localhost
RAW_NEWS_QUEUE	Queue name for consuming raw news articles.	raw-news-queue
SUMMARY_QUEUE	Queue name for publishing summarized articles.	summary-queue
MONGO_URI	MongoDB connection string.			mongodb://localhost:27017
MODEL_PATH	Path to the pre-trained summarization model.	/models/summary-model

Running the Service Locally
Prerequisites
Java 21
RabbitMQ and MongoDB running locally or accessible remotely.
Docker (optional for containerized setup).
