# AlertEngine - Notification Service

AlertEngine is the notification microservice of the InsightSphere platform. It manages real-time alerts and notifications for monitored keywords or topics, enabling users to stay updated with the latest developments.

---

## Features

- **Real-Time Alerts**: Notify users about new articles or trends matching their monitored keywords or topics.
- **Multi-Channel Notifications**: Send alerts via WebSocket, email, or push notifications.
- **Customizable Triggers**: Allow users to define notification criteria (e.g., keywords, regions, sentiment).

---

## Architecture Overview

AlertEngine integrates with:
- **RabbitMQ**: To subscribe to processed events (e.g., sentiment, trends, geo-data).
- **WebSocket**: For real-time notifications to connected users.
- **Email Services**: To send email alerts (e.g., SendGrid or SMTP server).
- **MongoDB**: To store user preferences and notification history.

---

## API Endpoints

### `/subscribe`
Subscribe to notifications for a specific keyword or topic.
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "userId": "user123",
    "keyword": "technology",
    "channel": "websocket"
  }
```

Response: HTTP 200 OK with subscription details.
```json
{
  "subscriptionId": "sub123",
  "status": "active"
}
```

/unsubscribe
Unsubscribe from a specific notification subscription.

Method: DELETE
Request Body
```json
{
  "subscriptionId": "sub123"
}
```

Response: HTTP 200 OK with status.


/status
Check the health status of the service.

Method: GET
Response: HTTP 200 OK with service status.


Environment Variables
Variable Name	Description			Example Value
RABBITMQ_URI	RabbitMQ connection string.	amqp://localhost
MONGO_URI	MongoDB connection string.	mongodb://localhost:27017
EMAIL_API_KEY	API key for the email service.	your-email-api-key
WEBSOCKET_PORT	Port for WebSocket connections.	8081

Running the Service Locally
Prerequisites
Java 21
RabbitMQ and MongoDB running locally or accessible remotely.
Docker (optional for containerized setup).
