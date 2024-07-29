FROM openjdk
EXPOSE 2008
ADD target/Security2x-0.0.1-SNAPSHOT.jar Security2x-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Security2x-0.0.1-SNAPSHOT.jar"]