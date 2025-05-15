# **GreenCompare**

**GreenCompare** is a Java project aimed at comparing plants and providing care guides using a MySQL database and a multi-threaded client-server system.

---

## **Features**

- Search for a single plant with detailed care information.
- Compare two plants side-by-side care attributes.
- Multi-threaded Socket server supporting multiple clients.
- HTTP server supporting queries via HTTP protocol.
- MySQL database for storing plant data.
- CSV importer to populate the database.
- Simple command-line interface.
- Unit tests included for functionality verification.

---

## **Requirements**

- Java 11 or higher
- Running MySQL server
- JDK with HTTP Client API support

---

## **Setup Instructions**

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/GreenCompare.git
cd GreenCompare
```
## 2. Setup MySQL Database
Create the database:

```sql
CREATE DATABASE plant_database;
```
Place your CSV file in a known location, for example:

```swift
C:/Users/abdul/OneDrive/Desktop/GreenCompare/plant_dataset.csv
```
Edit the CSV file path in CSVToMySQLImporter.java:

```java
String csvFile = "C:/Users/abdul/OneDrive/Desktop/GreenCompare/plant_dataset.csv";
```
Compile and run the CSV importer:

```bash
javac -d out src/org/example/CSVToMySQLImporter.java
java -cp out org.example.CSVToMySQLImporter
```

## 3. Configure Database Credentials
Update MySQL connection details in PlantDatabase.java and CSVToMySQLImporter.java:

```java
String dbUrl = "jdbc:mysql://localhost:3306/plant_database";
String user = "root";
String password = "root";
```

## 4. Build and Run the Server
Compile all source files:

```bash
javac -d out src/org/example/*.java
```
Run the Socket server:

```bash
java -cp out org.example.PlantServer
```
Or run the HTTP server:

```bash
java -cp out org.example.PlantHttpServer
```
## 5. Run Clients
For Socket client:

```bash
java -cp out org.example.PlantClient
```
For HTTP client:

```bash
java -cp out org.example.PlantHttpClient
```
## 6. Use the CLI Interface
Run the main CLI application:

```bash
java -cp out org.example.Main
```

### Usage
Single Plant Search: Enter the plant name to get its detailed care guide.

Plant Comparison: Enter two plant names to see a side-by-side comparison of their care attributes.

Exit: Quit the application.

### Testing
Unit tests are included to verify database queries and client-server communication.

Tests were created with AI assistance to ensure coverage and correctness.

Run tests using your preferred Java testing framework (JUnit 5 recommended).

### Notes
Ensure the MySQL server is running and accessible before running the application.

Modify database credentials according to your local setup.

The project follows a clean MVC structure with synchronized database access for thread safety.

Unit tests were generated with AI tools to accelerate development.
