# ESTÁGIO 1: COMPILAÇÃO E TESTES (Ambiente isolado do Maven)
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências (melhora o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte do projeto
COPY src ./src
COPY WebContent ./WebContent

# EXECUTAR TESTES COM H2 E GERAR O WAR
# Se o seu CarroDAOTest falhar com o H2, o Docker aborta o build aqui!
RUN mvn clean package

# ESTÁGIO 2: EXECUÇÃO (Imagem final que vai para o ar)
FROM tomcat:9.0-jdk8-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o .war gerado no estágio anterior direto para o Tomcat
COPY --from=build /app/target/carros.war /usr/local/tomcat/webapps/carros.war

EXPOSE 8080
CMD ["catalina.sh", "run"]