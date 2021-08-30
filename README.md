# make-magic-challenge

Challenge for the purpose of create a CRUD application about the universe of Harry Potter. So, one of the goals are consume an external API and validate the house id before allow the possibility of register a new character.

## Table of contents

- [Make Magic Challenge](#make-magic-challenge)
- [Table of contents](#table-of-contents)
- [Development](#development)
- [Usage](#usage)
    - [mvn](#mvn)
    - [Docker](#docker)
    - [Postman](#postman)
- [Documentation](#documentation)
    - [Houses](#houses)
- [Footer](#footer)

# Development

This project was developed using:

- [Java 11](https://openjdk.java.net/projects/jdk/11/);
- [Spring 2.5.4](https://spring.io/blog/2021/08/19/spring-boot-2-5-4-available-now);
- [Maven 3.6.3](https://maven.apache.org/download.cgi);
- [H2 Database](https://www.h2database.com/html/main.html);
- [Feign Client](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/);
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa);
- [JUnit/Mockito](https://junit.org/junit5/);
- [git](https://git-scm.com/);
- [docker 20.10.8](https://docs.docker.com/);
- [Postman](https://www.postman.com/);

# Usage

To test the application, clone the repo in your machine and in the project folder run the code bellow on the bash.

```bash
git clone https://github.com/OruamC/make-magic-challenge.git

cd make-magic-challenge

mvn clean install
```

After that, you can run the application by mvn or docker

## MVN

```bash
#Start Application
mvn spring-boot:run
```
## Docker

```bash
docker build -t makemagic:v1 .

docker run -p 8080:8080 --name makemagic makemagic:v1
```
## Postman

You can import the postman collection file inside the "postman-collection" folder into your program to test the application.

To do that, open your Postman program, click on import and drag or drop the file. Then click on the import button.

# Documentation

If you want to see more informations about how this API works, access the following link (http://localhost:8080/swagger-ui/index.html) after starting the application.

Also, there are an example of request body to create a new character.

## Houses

To create a new character, you must send in the request body, on the field "house", the id of one of the four houses existing in the school.

| Name            | ID           |
| -------------   |:-------------:|
| Gryffindor      | 1760529f-6d51-4cb1-bcb1-25087fce5bde |
| Hufflepuff      | 542b28e2-9904-4008-b038-034ab312ad7e |
| Ravenclaw       | 56cabe3a-9bce-4b83-ba63-dcd156e9be45 |
| Slytherin       | df01bd60-e3ed-478c-b760-cdbd9afe51fc |

# Footer
[(Back to top)](#table-of-contents)

For suggestion or any other subject, please contact me on linkdin and also, leave a start in GitHub.
