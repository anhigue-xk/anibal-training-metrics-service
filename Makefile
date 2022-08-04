.PHONY: all create migration run

docker_image_name = training

all: docker migration create run
docker: 
	docker start ${docker_image_name}
create:
	./mvnw clean package
migration:
	mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf
run:
	java -jar target/training-0.0.1-SNAPSHOT.jar