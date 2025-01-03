# GeoTracer - Geographic Processing Service

GeoTracer is the geographic processing microservice of the InsightSphere platform. It processes location metadata from news articles to generate heatmaps and regional insights, enabling visualization of news trends across geographic regions.

---

## Features

- Extract geographic metadata from news articles using Natural Language Processing (NLP) or APIs.
- Generate geographic heatmaps to visualize regional news trends.
- Publish enriched geographic data to RabbitMQ (`geo-queue`) for downstream services.
- Integrate with Mapbox for creating interactive visualizations.

---

## Architecture Overview

GeoTracer integrates with:
- **RabbitMQ**: To subscribe to raw article events and publish location-enriched results.
- **GeoPandas**: For geographic data processing.
- **Mapbox**: For generating interactive heatmaps.
- **MongoDB**: To store geographic metadata and processed data for future queries.

---

## API Endpoints

### `/process`
Manually trigger geographic processing for a specific article.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "articleId": "12345",
    "text": "Breaking news in New York City..."
  }
```

Response: HTTP 200 OK with geographic metadata.
```json
{
  "articleId": "12345",
  "location": "New York City, USA",
  "coordinates": [40.7128, -74.0060]
}

```

/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.

Environment Variables

Variable Name	Description					Example Value
MAPBOX_KEY	API key for Mapbox integration.			your-mapbox-api-key
RABBITMQ_URI	RabbitMQ connection string.			amqp://localhost
RAW_NEWS_QUEUE	Queue name for consuming raw news articles.	raw-news-queue
GEO_QUEUE	Queue name for publishing geographic data.	geo-queue
MONGO_URI	MongoDB connection string.			mongodb://localhost:27017


