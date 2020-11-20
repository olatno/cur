#!/bin/bash

# start Spring Boot app
java -Xms${JAVA_MEMORY} -Xmx${JAVA_MEMORY} -jar /app/app.jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}