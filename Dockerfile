FROM openjdk:8-alpine

COPY target/uberjar/voice-recognition-identity.jar /voice-recognition-identity/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/voice-recognition-identity/app.jar"]
