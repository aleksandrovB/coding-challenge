# Coding challenge submission

### To get the project running:
1. Clone repo and change into directory.
2. `mvn clean package`
3. `java -jar target\coding-challenge-1.0-SNAPSHOT.jar`
4. Test the application with the following commands:
	- `curl -X POST -H "Content-Type: application/json" -d @demo.json http://localhost:8080/submit/bash | bash`
	- `curl -X POST -H "Content-Type: application/json" -d @demo.json http://localhost:8080/submit/tasks`

### Endpoints available:
1. Send a POST request to `/submit/bash` with your tasks in JSON format, response is plaintext
2. Send a POST request to `/submit/tasks` with your tasks in JSON format, response is the tasks, ordered by their requirements field

