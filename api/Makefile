.PHONY: all migration run docker_run database_create database_run docker_network

docker_image_name = training-api
docker_network_name = training-network
container_db_name = training-db
container_api_name = training-api
postgre_user = test
postgre_pass = test
postegre_db  = metrics

all: database migration run

database_create:
	docker pull postgres && \
	docker run -d -it --name ${container_db_name} \
	--network=${docker_network_name} \
	-p 5432:5432 \
	-e POSTGRES_PASSWORD=${postgre_user} \
	-e POSTGRES_USER=${postgre_pass} \
	-e POSTGRES_DB=${postegre_db} \
	postgres
database_run:
	docker start ${container_db_name}
docker_network:
	docker network create --driver=bridge ${docker_network_name}
docker_run:
	docker run -d -it \
	--network=${docker_network_name} \
	-p 8080:8080 \
	--name ${container_api_name} \
	${docker_image_name}
docker_build:
	docker build --rm -t ${docker_image_name} .
build:
	mvn clean package
migration:
	mvn clean flyway:migrate -Dflyway.configFiles=src/main/resources/db/config/myFlywayConfig.conf
#run:
#	java -jar target/training-0.0.1-SNAPSHOT.jar
run:
	mvn spring-boot:run