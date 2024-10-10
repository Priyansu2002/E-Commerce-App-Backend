# Clone the repository
git clone https://github.com/your-username/E-Commerce-App-Backend.git

# Navigate to the backend directory
cd E-Commerce-App/backend

# Open the application.properties file to configure your database
# Replace the below values with your actual database details:
# Example for MySQL database configuration:
nano src/main/resources/application.properties

# Add or modify the following lines in application.properties:
# spring.datasource.url=jdbc:mysql://localhost:3306/your_database
# spring.datasource.username=your_username
# spring.datasource.password=your_password
# spring.jpa.hibernate.ddl-auto=update



# Install dependencies and run the Spring Boot application
mvn spring-boot:run

# The backend will be running at http://localhost:8080

