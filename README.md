# NLW Connect - DevStage

A Spring Boot application for creating events, referrals, and tracking user referrals. This project was developed during the NLW Connect event by Rocketseat.

![NLW Connect DevStage Logo](/assets/logo.png)

## ✨ Features

- Create and make referrals to events
- Event subscription system
- Referral tracking mechanism
- Ranking system for user referrals

## 🎨 UI/UX Design

This project's interface follows Rocketseat's design system as showcased in the [NLW Connect Figma Design](https://www.figma.com/community/file/1471119935944492720).

![NLW Connect Design Preview](https://s3-figma-hubfile-images-production.figma.com/hub/file/carousel/img/31f0a119ba7dd6f803d330aa5b6ec368f4c071c7/06e891597171af2aec1222e84e1b4d0427687a3b)


## 🚀 Tech Stack

<div align="left">
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/java.png" alt="Java" title="Java 21"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring_boot.png" alt="Spring Boot" title="Spring Boot 3.4.3"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/lombok.png" alt="Lombok" title="Lombok"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/maven.png" alt="Maven" title="Maven"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/mysql.png" alt="MySQL" title="MySQL 8.4"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/docker.png" alt="Docker" title="Docker"/></img>
	<img><img width="64" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/postman.png" alt="Postman" title="Postman"/></img>
</div>

![Tech Stack](https://skillicons.dev/icons?i=java,spring,maven,mysql,docker,idea&perline=3)

## 📋 Project Structure

```
events/
├── src/
│   ├── main/
│   │   ├── java/br/com/nlw/events/
│   │   │   ├── controller/
│   │   │   │   ├── EventController.java
│   │   │   │   └── SubscriptionController.java
│   │   │   ├── dto/
│   │   │   │   ├── EventDTO.java
│   │   │   │   ├── SubscriptionDTO.java
│   │   │   │   └── UserDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── EventEntity.java
│   │   │   │   ├── SubscriptionEntity.java
│   │   │   │   └── UserEntity.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── repository/
│   │   │   │   ├── EventRepository.java
│   │   │   │   ├── SubscriptionRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/
│   │   │   │   ├── EventService.java
│   │   │   │   ├── SubscriptionService.java
│   │   │   │   └── UserService.java
│   │   │   └── EventsApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/br/com/nlw/events/
│           ├── controller/
│           ├── service/
│           └── repository/
├── database_script/
│   ├── db_events.sql
│   ├── queries.sql
│   └── subscribers_mock.json
├── .mvn/
├── docker-compose.yml
├── .gitignore
├── LICENSE
├── README.md
└── pom.xml
```

## 🛠️ Installation and Setup

### Prerequisites

- Java 21+
- Maven
- Docker and Docker Compose

### Running the Application

1. **Clone the repository**

```bash
git clone https://github.com/zlucasftw/rocketseat-nlw-connect-devstage-java.git
cd rocketseat-nlw-connect-devstage-java
```

2. **Set up environment variables**

Create a `.env` file in the root directory with the following variables:

```properties
DB_USERNAME=root
DB_PASSWORD=root
DB_HOST=localhost:3336
DB_SOURCE=db_events
```

3. **Start MySQL with Docker**

```bash
docker-compose up -d
```

4. **Set up the database**

```bash
mysql -h 127.0.0.1 -P 3336 -u root -p < database_script/db_events.sql
```

5. **Build and run the application**

```bash
./mvnw spring-boot:run
```

The application will be available at [`http://localhost:8080`](http://localhost:8080).

## 🔌 API Endpoints

### Events API

- `GET /events` - List all events
- `GET /events/{prettyName}` - Get event by its pretty name
- `POST /events` - Create a new event

### Subscriptions API

- `POST /subscription/{prettyName}` - Subscribe user to an event
- `POST /subscription/{prettyName}/{userId}` - Subscribe user to an event with referral
- `GET /subscription/{prettyName}/ranking` - Get referral rankings for an event
- `GET /subscription/{prettyName}/ranking/{userId}` - Get specific user ranking for an event

## 📊 Database Schema

The database consists of three main tables:

- `tbl_event` - Stores event information
- `tbl_user` - Stores user information
- `tbl_subscription` - Links users to events and tracks referrals

## 🧪 Usage Example

### Creating an Event

```bash
curl -X POST http://localhost:8080/events \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Dev Stage",
    "location": "Online",
    "price": 0.00,
    "startDate": "2023-11-10",
    "endDate": "2023-11-10",
    "startTime": "09:00",
    "endTime": "17:00"
  }'
```

### Subscribing to an Event

```bash
curl -X POST http://localhost:8080/subscription/dev-stage \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com"
  }'
```

## 📝 TO-DO

- [ ] Improve API's global exception reponse/handling
- [ ] Add OpenAPI and Swagger for API documentation
- [ ] Add email notifications for event reminders
- [ ] Create admin dashboard for event management
- [ ] Implement event capacity limits
- [ ] Set up CI/CD pipeline
- [ ] Create comprehensive test coverage
- [ ] Add WebSocket support for real-time updates

## 🚧 Improvements

### Security Enhancements

- Implement role-based access control
- Add rate limiting for API endpoints
- Set up HTTPS with proper certificates

### Performance Optimizations

- Add Redis caching for frequently accessed data
- Implement database query optimizations
- Set up connection pooling configurations

### Frontend Client

- Create responsive frontend with React

### Feature Extensions

- Support for recurring events
- Multi-language support
- Event analytics dashboard
- Social media sharing integration

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0) - see the LICENSE file for details. The AGPL-3.0 requires that modified versions of this software also be made available under the same license, including when the software is running as a service.

## 🙏 Acknowledgements

- [Rocketseat](https://rocketseat.com.br) for the NLW Connect event and the amazing [UI/UX design](https://www.figma.com/community/file/1471119935944492720/nlw-connect-devstage) created by Rocketseat's designer Millena Kupsinskü.
