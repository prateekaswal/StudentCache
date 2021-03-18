FROM openjdk:8-jdk-alpine

COPY target/LoanApplicationCache_API-1.0.0.jar /tmp/

WORKDIR /tmp/

CMD ["java", "-jar", "LoanApplicationCache_API-1.0.0.jar"]

EXPOSE 8502

