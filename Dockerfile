FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY build/libs/Macaela-2-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080