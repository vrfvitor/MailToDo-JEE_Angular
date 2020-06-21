#!/bin/sh
mvn clean package && docker build -t br.com.mailtodo/mailtodo .
docker rm -f mailtodo || true && docker run -d -p 8080:8080 -p 4848:4848 --name mailtodo br.com.mailtodo/mailtodo 
