.PHONY: all docker create migration run

docker_image_name = training
postgre_user = test
postgre_pass = test
postegre_db  = metrics

all: docker_create docker migration create run

docker_create:
	docker pull postgres && \
	docker run -d -it --name ${docker_image_name} \
	-p 5432:5432 \
	-e POSTGRES_PASSWORD=${postgre_user} \
	-e POSTGRES_USER=${postgre_pass} \
	-e POSTGRES_DB=${postegre_db} \
	postgres
docker: 
	docker start ${docker_image_name}
create:
	./mvnw clean package
migration:
	mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf
run:
	java -jar target/training-0.0.1-SNAPSHOT.jar