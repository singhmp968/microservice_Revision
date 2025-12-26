#Start with base image for containing javaruntime
FROM eclipse-temurin:17-jdk-jammy

#Who is maintaning docker image
LABEL maintainer="eazybyte.com"

# telling docker that there is is jar in target/accounts-0.0.1-SNAPSHOT.jar and copy same in dockeraccounts-0.0.1-SNAPSHOT.jar i.e copy local to docker
COPY target/accounts-0.0.1-SNAPSHOT.jar /accounts-0.0.1-SNAPSHOT.jar

#for starting  same command taht we used to start our java application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]