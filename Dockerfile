# Используем официальный образ с Java 17
FROM eclipse-temurin:17-jdk-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Даем права на выполнение mvnw и собираем проект
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Открываем порт, который использует приложение
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "target/question-answer-proxy-1.0.0.jar"]