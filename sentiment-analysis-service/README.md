# MoodAnalyzer - Sentiment Analysis Service

MoodAnalyzer is the sentiment analysis microservice for the InsightSphere platform. It processes news articles to classify their sentiment as positive, neutral, or negative, enabling deeper insights into the emotional tone of global news.

---

## Features

- Classify sentiment using pre-trained NLP models (e.g., BERT, Vader).
- Consume raw news articles from RabbitMQ (`raw-news-queue`).
- Publish sentiment-enriched articles to RabbitMQ (`sentiment-queue`) for further processing.
- Store sentiment results in MongoDB for analytical queries.

---

## Architecture Overview

MoodAnalyzer integrates with:
- **RabbitMQ**: To subscribe to raw article events and publish sentiment-enriched results.
- **NLP Models**: To classify sentiment using pre-trained transformer models.
- **MongoDB**: To store enriched sentiment data for future analysis.

---

## API Endpoints

### `/process`
Manually trigger sentiment analysis for a specific article.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "articleId": "12345",
    "text": "This is an example news article text."
  }
 ```

Response: HTTP 200 OK with sentiment classification result.
```json
{
  "articleId": "12345",
  "sentiment": "positive"
}

```

/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status

Environment Variables
Variable Name	Description	Example Value
MODEL_PATH	Path to the pre-trained NLP model.		/models/sentiment-model
RABBITMQ_URI	RabbitMQ connection string.			amqp://localhost
RAW_NEWS_QUEUE	Queue name for consuming raw news articles.	raw-news-queue
SENTIMENT_QUEUE	Queue name for publishing sentiment results.	sentiment-queue
MONGO_URI	MongoDB connection string.			mongodb://localhost:27017

Running the Service Locally
Prerequisites
Java 21
RabbitMQ and MongoDB running locally or accessible remotely.
Docker (optional for containerized setup).

Steps
Using Maven
1. Clone the repository:
```bash
git clone https://github.com/your-repo/mood-analyzer.git
cd mood-analyzer

```
2. Run the application:
```bash
mvn spring-boot:run
```

Using Docker
1. Build the Docker image:
```bash
docker build -t mood-analyzer .
```

2. Run the container:
```bash
docker run -d --name mood-analyzer -e MODEL_PATH=/models/sentiment-model -e RABBITMQ_URI=amqp://host -e RAW_NEWS_QUEUE=raw-news-queue -e SENTIMENT_QUEUE=sentiment-queue -e MONGO_URI=mongodb://host:port mood-analyzer
```


Here’s the README.md for the Sentiment Analysis Service (MoodAnalyzer):

markdown
Copiar código
# MoodAnalyzer - Sentiment Analysis Service

MoodAnalyzer is the sentiment analysis microservice for the InsightSphere platform. It processes news articles to classify their sentiment as positive, neutral, or negative, enabling deeper insights into the emotional tone of global news.

---

## Features

- Classify sentiment using pre-trained NLP models (e.g., BERT, Vader).
- Consume raw news articles from RabbitMQ (`raw-news-queue`).
- Publish sentiment-enriched articles to RabbitMQ (`sentiment-queue`) for further processing.
- Store sentiment results in MongoDB for analytical queries.

---

## Architecture Overview

MoodAnalyzer integrates with:
- **RabbitMQ**: To subscribe to raw article events and publish sentiment-enriched results.
- **NLP Models**: To classify sentiment using pre-trained transformer models.
- **MongoDB**: To store enriched sentiment data for future analysis.

---

## API Endpoints

### `/process`
Manually trigger sentiment analysis for a specific article.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "articleId": "12345",
    "text": "This is an example news article text."
  }
Response: HTTP 200 OK with sentiment classification result.
json
Copiar código
{
  "articleId": "12345",
  "sentiment": "positive"
}
/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.
Environment Variables
Variable Name	Description	Example Value
MODEL_PATH	Path to the pre-trained NLP model.	/models/sentiment-model
RABBITMQ_URI	RabbitMQ connection string.	amqp://localhost
RAW_NEWS_QUEUE	Queue name for consuming raw news articles.	raw-news-queue
SENTIMENT_QUEUE	Queue name for publishing sentiment results.	sentiment-queue
MONGO_URI	MongoDB connection string.	mongodb://localhost:27017
Running the Service Locally
Prerequisites
Java 21
RabbitMQ and MongoDB running locally or accessible remotely.
Docker (optional for containerized setup).
Steps
Using Maven
Clone the repository:
```bash
git clone https://github.com/your-repo/mood-analyzer.git
```
cd mood-analyzer

Run the application:
```bash
mvn spring-boot:run
```

Using Docker
Build the Docker image:
```bash
docker build -t mood-analyzer .
```

Run the container:
```bash
docker run -d --name mood-analyzer -e MODEL_PATH=/models/sentiment-model -e RABBITMQ_URI=amqp://host -e RAW_NEWS_QUEUE=raw-news-queue -e SENTIMENT_QUEUE=sentiment-queue -e MONGO_URI=mongodb://host:port mood-analyzer
```

Integration with InsightSphere
MoodAnalyzer works seamlessly within the InsightSphere ecosystem by:

1. Consuming raw article events from RabbitMQ (raw-news-queue).
2. Performing sentiment analysis using advanced NLP models.
3. Publishing enriched sentiment data to RabbitMQ (sentiment-queue).
4. Storing sentiment results in MongoDB for analysis.


