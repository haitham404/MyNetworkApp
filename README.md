# MyNetworkApp

A Java-based social networking application built with JavaFX that provides user authentication, profile management, and social networking features.

## Features

- User Authentication (Signup and Login)
- Profile Management
- Home Page Feed
- User Search Functionality
- Database Integration
- Modern JavaFX UI

## Prerequisites

- Java 21 or higher
- Maven
- MySQL Database

## Technology Stack

- Java 21
- JavaFX 21
- Maven
- MySQL
- JUnit 5 for testing

## Project Structure

```
src/main/java/com/example/mynetworkapp/
├── Main.java                 # Application entry point
├── DatabaseConnection.java   # Database connection management
├── User.java                 # User model class
├── UserDAO.java             # Data Access Object for User operations
├── UserSession.java         # Session management
├── login.java               # Login interface
├── Signup.java              # User registration interface
├── HomePage.java            # Main application interface
├── Profile.java             # User profile management
└── AfterSearch.java         # Search results interface
```

## Getting Started

1. Clone the repository:
   ```bash
   git clone [repository-url]
   ```

2. Configure the database:
   - Create a MySQL database
   - Update database connection settings in `DatabaseConnection.java`

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn javafx:run
   ```

## Building and Running

The project uses Maven for dependency management and building. The main class is configured in the `pom.xml` file.

To run the application:
```bash
mvn clean javafx:run
```

## Testing

The project includes JUnit 5 tests. Run tests using:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- JavaFX team for the UI framework
- Maven team for the build system
- MySQL team for the database system 