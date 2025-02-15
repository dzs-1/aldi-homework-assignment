FROM mcr.microsoft.com/playwright/java:v1.50.0-noble

WORKDIR /app

COPY . .

RUN mvn dependency:resolve
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chromium,firefox"

CMD ["mvn", "test"]