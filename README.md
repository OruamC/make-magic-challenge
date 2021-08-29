# make-magic-challenge

Challenge for the purpose of create a CRUD application about the universe of Harry Potter. So, one of the goals are consume an external API and validate the house id before allow the possibility of register a new character.

## Table of contents

- [Make Magic Challenge](#make-magic-challenge)
- [Table of contents](#table-of-contents)
- [Development](#development)
- [Usage](#usage)
    - [mvn](#mvn)
    - [Docker](#docker)
- [Footer](#footer)

# Development

this project was developed using:

- Java 11;
- Spring 2.5.4;
- Maven 3.6.3;
- H2 Database;
- Feign Client;
- JPA;
- JUnit/Mockito;
- git;
- docker 20.10.8;

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

# Footer
[(Back to top)](#table-of-contents)

For suggestion or any other subject, please contact me on linkdin and also, leave a start in GitHub.
