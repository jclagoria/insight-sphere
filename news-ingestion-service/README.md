# PulseCollector - News Ingestion Service

PulseCollector is the ingestion service for the InsightSphere platform, responsible for fetching news articles from NewsAPI and preparing them for downstream processing. It plays a vital role in collecting and distributing raw news data to other microservices in the system.

---

## Features

- Fetch news articles using keyword-based or topic-based queries.
- Store raw article data in MongoDB for persistence.
- Index metadata into Elasticsearch for fast keyword-based search.
- Publish raw news events to RabbitMQ for processing by other services.

---

## Architecture Overview

PulseCollector integrates with:
- **NewsAPI**: To fetch live articles based on user-defined parameters.
- **MongoDB**: To store raw JSON responses.
- **Elasticsearch**: To index article metadata for search functionality.
- **RabbitMQ**: To publish raw articles as events for downstream services like sentiment analysis and trend mapping.

---

## API Endpoints

### `/fetch`
Manually trigger article ingestion based on query parameters.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "keywords": ["example"],
    "fromDate": "2025-01-01",
    "toDate": "2025-01-02"
  }


Response: HTTP 200 OK with ingestion status.
/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.

Environment Variables
Variable Name	Description	Example Value
NEWS_API_KEY	API key for accessing NewsAPI.	your-newsapi-key
MONGO_URI	MongoDB connection string.	mongodb://localhost:27017
ELASTIC_URI	Elasticsearch connection string.	http://localhost:9200
RABBITMQ_URI	RabbitMQ connection string.	amqp://localhost
FETCH_INTERVAL	Time interval for automated fetching (ms).	3600000 (1 hour)

Running the Service Locally
Prerequisites
Java 21
MongoDB, Elasticsearch, and RabbitMQ running locally or accessible remotely.
Docker (optional for containerized setup).

### Steps
#### Using Maven
1. Clone the repository:
```bash
git clone https://github.com/your-repo/pulse-collector.git
cd pulse-collector

```
2. Run the application:
```bash
mvn spring-boot:run
```

#### Using Docker
1. Build the Docker image:
```bash
docker build -t pulse-collector .
```
2. Run the container:
```bash
docker run -d --name pulse-collector -e NEWS_API_KEY=your-key -e MONGO_URI=mongodb://host:port -e ELASTIC_URI=http://host:port -e RABBITMQ_URI=amqp://host pulse-collector

```

### Integration with InsightSphere
PulseCollector works seamlessly within the InsightSphere ecosystem by:

1. Fetching and storing raw data for immediate or scheduled ingestion.
2. Indexing metadata for the InsightAPI's search functionality.
3. Publishing raw news data to RabbitMQ queues (raw-news-queue). 

Monitoring and Logs
Logs: Logs are stored locally or can be viewed in the container logs:
```bash
docker logs pulse-collector
```

Monitoring: Use Prometheus to monitor metrics and Grafana for visualization.

Contributing
Fork the repository.
Create a new branch: git checkout -b feature-name.
Commit your changes: git commit -m 'Add feature-name'.
Push to the branch: git push origin feature-name.
Submit a pull request.

License
This project is licensed under the MIT License.


