# 🎓 Student Fee Management System - Backend API

A RESTful backend application for managing student fee collection and payment processing in educational institutions. Built with Spring Boot and H2 in-memory database for rapid development and testing.

## ✨ Features

- 🔐 **RESTful APIs** - Complete CRUD operations for all entities
- 👨‍🎓 **Student Management** - Manage student records and enrollment
- 💰 **Fee Structure** - Define and manage fee components
- 💳 **Payment Processing** - Record and track fee payments
- 🧾 **Receipt Generation** - Automatic payment receipt creation
- 📊 **Reports & Analytics** - Financial reports and payment tracking
- 🗄️ **H2 Database** - In-memory database with console access
- 🔒 **Security** - Input validation and error handling

## 🛠️ Technology Stack

- **Java** 11 or higher
- **Spring Boot** 2.7+ / 3.x
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Spring Web** - RESTful APIs
- **Hibernate** - ORM framework
- **Maven** - Dependency management
- **Lombok** (Optional) - Reduce boilerplate code

## 📋 Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Any REST client (Postman, cURL, or browser)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/pavan7499/Student-Fee-Management.git
cd Student-Fee-Management
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

### 4. Access H2 Console
Open your browser and go to:
```
http://localhost:8080/h2-console
```

**H2 Console Login:**
- JDBC URL: `jdbc:h2:mem:studentfeedb`
- Username: `sa`
- Password: *(leave empty)*

## ⚙️ Configuration

### application.properties
```properties
# Server Configuration
server.port=8080

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:studentfeedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## 📁 Project Structure

```
src/
└── main/
    ├── java/
    │   └── com/studentfee/
    │       ├── StudentFeeManagementApplication.java
    │       ├── controller/          # REST Controllers
    │       │   ├── StudentController.java
    │       │   ├── FeeStructureController.java
    │       │   ├── PaymentController.java
    │       │   └── ReportController.java
    │       ├── model/               # Entity Classes
    │       │   ├── Student.java
    │       │   ├── Course.java
    │       │   ├── FeeStructure.java
    │       │   ├── Payment.java
    │       │   └── User.java
    │       ├── repository/          # JPA Repositories
    │       │   ├── StudentRepository.java
    │       │   ├── FeeStructureRepository.java
    │       │   ├── PaymentRepository.java
    │       │   └── CourseRepository.java
    │       ├── service/             # Business Logic
    │       │   ├── StudentService.java
    │       │   ├── FeeService.java
    │       │   ├── PaymentService.java
    │       │   └── ReportService.java
    │       ├── dto/                 # Data Transfer Objects
    │       │   ├── StudentDTO.java
    │       │   ├── PaymentDTO.java
    │       │   └── ReportDTO.java
    │       └── exception/           # Custom Exceptions
    │           ├── ResourceNotFoundException.java
    │           └── GlobalExceptionHandler.java
    └── resources/
        ├── application.properties
        ├── data.sql                 # Sample data (optional)
        └── schema.sql               # Database schema (optional)
```

## 🔌 API Endpoints

### Student Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |
| GET | `/api/students/search?name={name}` | Search students by name |

### Fee Structure Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/fees` | Get all fee structures |
| GET | `/api/fees/{id}` | Get fee by ID |
| POST | `/api/fees` | Create fee structure |
| PUT | `/api/fees/{id}` | Update fee structure |
| DELETE | `/api/fees/{id}` | Delete fee structure |
| GET | `/api/fees/course/{courseId}` | Get fees by course |

### Payment Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/payments` | Get all payments |
| GET | `/api/payments/{id}` | Get payment by ID |
| POST | `/api/payments` | Record new payment |
| GET | `/api/payments/student/{studentId}` | Get student's payment history |
| GET | `/api/payments/receipt/{id}` | Generate payment receipt |

### Reports

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/reports/dashboard` | Get dashboard statistics |
| GET | `/api/reports/monthly` | Get monthly collection |
| GET | `/api/reports/defaulters` | Get list of defaulters |
| GET | `/api/reports/student/{id}` | Get student fee report |

## 📝 Sample API Requests

### Create Student
```bash
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "rollNumber": "2024001",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "courseId": 1,
  "batchYear": 2024,
  "admissionDate": "2024-01-15"
}
```

### Record Payment
```bash
POST http://localhost:8080/api/payments
Content-Type: application/json

{
  "studentId": 1,
  "feeId": 1,
  "amountPaid": 50000.00,
  "paymentDate": "2024-12-02",
  "paymentMode": "Online",
  "transactionId": "TXN123456789"
}
```

### Get All Students
```bash
GET http://localhost:8080/api/students
```

## 🗄️ Database Schema

### Student Entity
```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer batchYear;
    private LocalDate admissionDate;
    private String status; // Active, Inactive, Graduated
}
```

### Fee Structure Entity
```java
@Entity
@Table(name = "fee_structure")
public class FeeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer semester;
    private String feeType; // Tuition, Library, Sports, etc.
    private BigDecimal amount;
    private String academicYear;
    private LocalDate dueDate;
}
```

### Payment Entity
```java
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "fee_id")
    private FeeStructure feeStructure;
    
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private String paymentMode; // Cash, Online, Cheque, Card
    private String transactionId;
    private String receiptNumber;
    private String status; // Pending, Completed, Failed
}
```

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

### Test with cURL
```bash
# Get all students
curl http://localhost:8080/api/students

# Create a student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"rollNumber":"2024001","firstName":"John","lastName":"Doe","email":"john@example.com"}'
```

## 🔒 Error Handling

The API returns standardized error responses:

```json
{
  "timestamp": "2024-12-02T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with id: 123",
  "path": "/api/students/123"
}
```

**Common HTTP Status Codes:**
- `200 OK` - Success
- `201 Created` - Resource created
- `400 Bad Request` - Invalid input
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## 🛠️ Development

### Add Sample Data on Startup
Create `src/main/resources/data.sql`:

```sql
-- Insert sample courses
INSERT INTO courses (course_name, course_code, duration_years, total_fee) 
VALUES ('Computer Science', 'CS101', 4, 400000.00);

-- Insert sample students
INSERT INTO students (roll_number, first_name, last_name, email, phone, course_id, batch_year, admission_date, status) 
VALUES ('2024001', 'John', 'Doe', 'john@example.com', '9876543210', 1, 2024, '2024-01-15', 'Active');

-- Insert sample fee structure
INSERT INTO fee_structure (course_id, semester, fee_type, amount, academic_year, due_date) 
VALUES (1, 1, 'Tuition', 50000.00, '2024-25', '2024-08-15');
```

### Enable H2 Persistence (Optional)
To persist data across restarts, change in `application.properties`:
```properties
# File-based H2 database
spring.datasource.url=jdbc:h2:file:./data/studentfeedb
```

## 📚 Dependencies (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- H2 Database -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok (Optional) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🚀 Deployment

### Build JAR
```bash
mvn clean package
```

### Run JAR
```bash
java -jar target/student-fee-management-0.0.1-SNAPSHOT.jar
```

### Docker (Optional)
```dockerfile
FROM openjdk:11-jre-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🔄 Future Enhancements

- [ ] Add Spring Security for authentication
- [ ] Implement JWT-based authorization
- [ ] Add Swagger/OpenAPI documentation
- [ ] Migrate to MySQL/PostgreSQL for production
- [ ] Add payment gateway integration
- [ ] Implement email notifications
- [ ] Add caching with Redis
- [ ] Create Docker Compose setup

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Pavan**
- GitHub: [@pavan7499](https://github.com/pavan7499)
- Project: [Student-Fee-Management](https://github.com/pavan7499/Student-Fee-Management)

## 📞 Support

For questions or issues:
- Open an issue on GitHub
- Email:pavansuryawanshi014@gmail.com

---

⭐ **Star this repo if you find it helpful!** ⭐
