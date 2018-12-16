FROM clojure:openjdk-8-lein-alpine
RUN mkdir -p /usr/src/voice-recognition-identity
WORKDIR /usr/src/voice-recognition-identity
COPY project.clj /usr/src/voice-recognition-identity/
RUN lein version
RUN lein test
COPY . /usr/src/voice-recognition-identity
RUN lein uberjar
CMD ["java", "-jar", "target/uberjar/voice-recognition-identity.jar"]