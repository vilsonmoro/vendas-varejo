# Etapa de build: usa Maven com Java 17 para compilar
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
# Copia o pom.xml e o diretório src
COPY pom.xml .
COPY src ./src
# Executa a build do Maven e gera o .jar
RUN mvn clean package -DskipTests

# Etapa de execução: usa uma imagem leve com Java 17 para rodar a aplicação
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia o .jar gerado da etapa de build
COPY --from=build /app/target/tcc-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta para a aplicação
EXPOSE ${PORT}

# Inicia o aplicativo com o JAR gerado
ENTRYPOINT ["java", "-jar", "app.jar"]
