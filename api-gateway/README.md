# InsightAPI - API Gateway

InsightAPI is the central API Gateway of the InsightSphere platform. It handles user requests and routes them to the appropriate microservices, enabling seamless interaction with the system. It is built on Spring Cloud Gateway for reactive and non-blocking API management.

---

## Features

- **Centralized Access**: Acts as a single entry point for all user requests.
- **Routing**: Routes requests to the corresponding microservices.
- **Reactive Handling**: Provides asynchronous, non-blocking communication.
- **Security**: Implements rate limiting, authentication, and authorization.
- **Real-Time Updates**: Supports WebSocket or Server-Sent Events (SSE) for live data streams.

---

## API Endpoints

### `/search`
Retrieve news articles by keyword.
- **Method**: `GET`
- **Query Parameters**:
  - `keyword`: Keyword to search for (e.g., `technology`).
  - `fromDate`: Start date for the search (e.g., `2025-01-01`).
  - `toDate`: End date for the search (e.g., `2025-01-02`).
- **Response**: List of articles matching the query.
  ```json
  [
    {
      "title": "Tech Innovations of 2025",
      "author": "John Doe",
      "publishedAt": "2025-01-01",
      "source": "TechWorld",
      "url": "https://techworld.com/article/12345"
    }
  ]
```

/trends
Retrieve time-series trends for a specific keyword.

Method: GET
Query Parameters:
keyword: Keyword to analyze (e.g., technology).
Response: Trend data for the specified keyword.
```json
{
  "keyword": "technology",
  "trends": [
    {"date": "2025-01-01", "mentions": 150},
    {"date": "2025-01-02", "mentions": 200}
  ]
}
```

/geo
Fetch geographic insights for news trends.

Method: GET
Query Parameters:
keyword: Keyword for geographic analysis (e.g., technology).
Response: GeoJSON data for heatmap visualization.
```json
{
  "type": "FeatureCollection",
  "features": [
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [-74.006, 40.7128]
      },
      "properties": {"mentions": 100, "region": "New York"}
    }
  ]
}
```

Environment Variables
Variable Name		Description					Example Value
RABBITMQ_URI		RabbitMQ connection string.			amqp://localhost
ELASTIC_URI		Elasticsearch connection string.		http://localhost:9200
TREND_SERVICE_URI	Base URL of the Trend Analysis Service.		http://trend-mapper:8080
GEO_SERVICE_URI		Base URL of the Geographic Processing Service.	http://geo-tracer:8080
AUTH_SERVICE_URI	Base URL of the Authentication Service.		http://auth-service:8080

Running the Service Locally
Prerequisites
Java 21
Spring Cloud Gateway dependencies configured.
RabbitMQ, Elasticsearch, and microservices running locally or accessible remotely.
Docker (optional for containerized setup).


