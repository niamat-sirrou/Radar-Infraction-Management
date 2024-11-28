# Radar Infraction Management

This project is a **Radar Infraction Management System** built with **Spring Boot** to handle and manage traffic infractions detected by radar systems. The application allows for efficient management of infractions, processing them for review, and tracking all necessary details.

## Features

- **Infraction Management**: Capture and manage details of infractions, including speed, location, and time.
- **Radar Integration**: Integrates with radar systems for automatic infraction data capture.
- **User Roles**: Provides role-based access control (Admin, User) to manage infractions and review them.
- **Notifications**: Sends notifications for infractions to the concerned authorities.
- **Data Storage**: Stores infraction details in a relational database.

## Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MySQL / PostgreSQL
- **Frontend**: Thymeleaf (if applicable) or REST APIs for frontend integration
- **Security**: Spring Security for user authentication and authorization

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 11+**: Ensure Java is installed on your machine.
- **Maven**: For building the project.
- **MySQL / PostgreSQL**: For the database setup.
- **Git**: For version control.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/niamat-sirrou/Radar-Infraction-Management.git
    cd Radar-Infraction-Management
    ```

2. Set up the database:
    - Create a database in MySQL or PostgreSQL for the application.
    - Update the `application.properties` or `application.yml` file with your database credentials.

3. Build and run the application:
    - With Maven:
        ```bash
        mvn clean install
        mvn spring-boot:run
        ```
    - The application should now be running at `http://localhost:8080`.

4. Access the Application:
    - Open your browser and navigate to `http://localhost:8080` to interact with the app.

## API Documentation (if applicable)

- **POST /api/infractions**: Create a new infraction.
- **GET /api/infractions**: Retrieve all infractions.
- **GET /api/infractions/{id}**: Get details of a specific infraction.
- **DELETE /api/infractions/{id}**: Delete an infraction.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes and commit (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Spring Boot for simplifying the development process.
- MySQL/PostgreSQL for the database support.
- Any other relevant libraries or tools.

---

For any issues or feedback, feel free to open an issue on the GitHub repository.
