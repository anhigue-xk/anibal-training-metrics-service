.PHONY: all docker create migration run

docker_image_name = training
postgre_user = test
postgre_pass = test
postegre_db  = metrics

all: database docker migration run

database:
	docker pull postgres && \
	docker run -d -it --name ${docker_image_name} \
	-p 5432:5432 \
	-e POSTGRES_PASSWORD=${postgre_user} \
	-e POSTGRES_USER=${postgre_pass} \
	-e POSTGRES_DB=${postegre_db} \
	postgres
docker: 
	docker start ${docker_image_name}
build:
	mvn clean package
migration:
	mvn clean flyway:migrate -Dflyway.configFiles=src/main/resources/db/config/myFlywayConfig.conf
#run:
#	java -jar target/training-0.0.1-SNAPSHOT.jar
run:
	mvn spring-boot:run