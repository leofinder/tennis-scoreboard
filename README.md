# Tennis Scoreboard

Tennis Scoreboard is a Java-based web application that tracks and manages tennis matches, players, and scores. The application uses Hibernate for ORM, PostgreSQL as the database, and Tomcat for running the WAR file. Docker Compose is used to set up and run the PostgreSQL database.

## Prerequisites

Make sure you have the following installed on your machine:

- Java 17+
- Apache Maven
- Tomcat 10
- Docker

## Installation and Running the Application

### Step 1: Clone the Repository

```bash
git clone https://github.com/leofinder/tennis-scoreboard.git
cd tennis-scoreboard
```

### Step 2: Set Up the Environment

1. **Docker**: You will need Docker to run the PostgreSQL database. The `docker-compose.yml` file is already provided for setting up the PostgreSQL service.
2. **Environment Variables**: The `.env` file is provided and contains necessary environment variables for database connection. Ensure the settings match your local environment or use the provided defaults.

### Step 3: Run PostgreSQL Database

Start the PostgreSQL database using Docker Compose:

```bash
docker-compose up -d
```

This will create and start the PostgreSQL database defined in `docker-compose.yml`.

### Step 4: Build the WAR File

Use Maven to build the project and package it into a WAR file.

```bash
mvn clean package
```

This will generate a `WAR` file in the `target` directory, which can be deployed to Tomcat.

### Step 5: Deploy to Tomcat

Copy the generated `tennis-scoreboard.war` file to your Tomcat 10 `webapps` folder.

```bash
cp target/tennis-scoreboard.war /path/to/tomcat/webapps/
```

Start the Tomcat 10 server:

```bash
/path/to/tomcat/bin/startup.sh
```

Once Tomcat is running, the application should be accessible at `http://localhost:8080/tennis-scoreboard`.

### Step 6: Verify Database Connection

The application uses Liquibase to manage database migrations. Once the application is started, Liquibase will automatically apply any pending migrations to the PostgreSQL database.

### Step 7: Access the Application

After deployment, you can access the web interface and begin managing tennis matches and player data.
