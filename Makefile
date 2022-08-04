all:
	dist migration run
dist:
	./mvnw clean package
migration:
	mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf
run:
	java -jar target/training-metrics-service-0.0.1.jar