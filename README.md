# InsightSphere

InsightSphere is a powerful platform for real-time news analytics, enabling users to search for articles, analyze trends, monitor sentiment, and visualize geographic patterns across global news.

## Features

- **Keyword-based News Search**: Quickly find articles matching your query.
- **Sentiment Analysis**: Classify articles as positive, neutral, or negative.
- **Time-Series Trends**: Explore news trends over time for specific topics.
- **Geographic Heatmaps**: Visualize news distribution and hotspots by region.
- **Real-Time Notifications**: Stay updated on monitored keywords/topics.
- **Summarization**: Receive concise summaries of news articles.

## Architecture Overview

InsightSphere is built using a modular microservices architecture with a reactive programming paradigm. Key technologies include:
- **Frontend**: React/Angular with D3.js/Plotly for visualizations.
- **Backend**: Java 21, Spring Boot Reactive.
- **Databases**: MongoDB, PostgreSQL, Elasticsearch.
- **Messaging**: RabbitMQ for event-driven communication.
- **Deployment**: Docker, Kubernetes.

## Services

1. **PulseCollector**: Fetches raw articles from NewsAPI.
2. **MoodAnalyzer**: Performs sentiment analysis on articles.
3. **TrendMapper**: Analyzes time-series data for trends.
4. **GeoTracer**: Extracts geographic data and creates heatmaps.
5. **InsightAPI**: Centralized gateway for user interactions.
6. **AlertEngine**: Manages real-time notifications.
7. **SummaryForge**: Generates summaries for news articles.

