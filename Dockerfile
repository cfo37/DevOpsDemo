# docker build -t mosazhaw/devopsdemo .
# docker run -p 9001:8080 --name dockerize-devopsdemo -d mosazhaw/devopsdemo
FROM eclipse-temurin:25.0.1_8-jdk-noble

# Curl, Python und Node.js (inkl. npm) installieren
RUN apt-get update && apt-get install -y curl python3 \
    && curl -fsSL https://deb.nodesource.com/setup_24.x | bash - \
    && apt-get install -y nodejs \
    && rm -rf /var/lib/apt/lists/*

# Verifizieren, dass node + npm wirklich da sind
RUN node --version && npm --version

WORKDIR /usr/src/app
COPY . .

RUN cd frontend && npm install
RUN mkdir -p backend/src/main/resources/static
RUN mv frontend/* backend/src/main/resources/static
RUN cd backend && sed -i 's/\r$//' gradlew && chmod +x gradlew
RUN cd backend && ./gradlew build

EXPOSE 8080
CMD ["java", "-jar", "/usr/src/app/backend/build/libs/demo-0.0.1-SNAPSHOT.jar"]