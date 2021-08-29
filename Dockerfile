FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/make-magic-challenge-0.0.1-SNAPSHOT.jar nmakemagic.jar
ENTRYPOINT ["java","-jar","/nmakemagic.jar"]