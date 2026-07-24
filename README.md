# Carros API

API REST desenvolvida em Spring Boot para gerenciamento de carros.

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- Maven

## Banco de Dados

Banco: `carros`

Principais migrations:

- V1 - Criação inicial
- V2 - Ajuste dos tipos de latitude e longitude
- V3 - Recuperação do encoding UTF-8

## Executando

```bash
mvn spring-boot:run
```

## Estrutura

- controller
- service
- repository
- entity
- dto

## Evolução do Projeto

- ✅ Configuração do Git e GitHub
- ✅ Estrutura inicial do projeto
- ✅ Banco MySQL
- ✅ Migrations com Flyway
- ✅ Correção de problemas de encoding UTF-8
- 🚧 Implementação do CRUD
- ⏳ Migração para Spring Data JPA
- ⏳ Documentação com Swagger

## Fase 1 - CRUD JDBC

### Evolução do CarroDAO

- Centralização das instruções SQL em constantes;
- Implementação de CRUD utilizando PreparedStatement;
- Tratamento de valores nulos para latitude e longitude;
- Melhoria do mapeamento de ResultSet para entidade Carro;
- Criação de método genérico para conversão de listas;
- Uso de Optional no retorno por ID;
- Melhoria no tratamento de exceções de banco.

## Autor

Marcio
