# 🚗 Carros

Projeto Java desenvolvido como um laboratório prático para demonstrar a **evolução do desenvolvimento de aplicações web no ecossistema Java**, partindo do acesso direto ao banco de dados com JDBC até uma arquitetura moderna utilizando **Spring Boot e Angular**.

Mais do que uma API CRUD, o projeto tem como objetivo mostrar **como as responsabilidades, abstrações e tecnologias evoluíram ao longo do tempo**, permitindo comparar diferentes abordagens para resolver problemas semelhantes.

---

## 🎯 Objetivo

O projeto **Carros** acompanha a evolução de uma aplicação de gerenciamento de veículos através de diferentes tecnologias e arquiteturas Java.

A evolução planejada é:

```text
JDBC
  ↓
Servlets / JSP
  ↓
JPA / Hibernate
  ↓
Spring Framework
  ↓
Spring Boot
  ↓
Angular
```

Ao final, a arquitetura será composta por:

```text
┌──────────────┐
│   Angular    │
│  Frontend    │
└──────┬───────┘
       │ HTTP / JSON
       ▼
┌────────────────────┐
│    Spring Boot     │
│     REST API       │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│   JPA / Hibernate  │
│    Persistence     │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│       MySQL        │
└────────────────────┘
```

---

# 🛠️ Tecnologias

### Backend

* Java
* JDBC
* Servlets
* JSP
* JPA
* Hibernate
* Spring Framework
* Spring Boot
* Spring Data JPA
* Spring Data JDBC
* REST API
* Maven

### Frontend

* Angular
* TypeScript
* HTML
* CSS

### Banco de Dados

* MySQL
* Flyway

---

# 🏗️ Arquitetura

Na evolução para uma arquitetura baseada em Spring Boot, o projeto será organizado em camadas, separando as responsabilidades da aplicação:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Camadas

| Camada       | Responsabilidade                            |
| ------------ | ------------------------------------------- |
| `Controller` | Exposição dos endpoints REST                |
| `Service`    | Regras de negócio                           |
| `Repository` | Acesso aos dados                            |
| `Entity`     | Representação das entidades persistidas     |
| `DTO`        | Transferência de dados entre as camadas/API |

Essa separação permite reduzir o acoplamento e facilita a manutenção, evolução e testes da aplicação.

---

# 🗄️ Banco de Dados

O projeto utiliza **MySQL** como banco de dados.

O controle da evolução do schema é realizado utilizando **Flyway**, permitindo que as alterações estruturais sejam versionadas e executadas de maneira previsível.

Banco utilizado:

```text
carros
```

## Migrations

| Versão | Descrição                                |
| ------ | ---------------------------------------- |
| `V1`   | Criação inicial do banco                 |
| `V2`   | Ajuste dos tipos de latitude e longitude |
| `V3`   | Recuperação/correção do encoding UTF-8   |

As migrations ficam versionadas junto ao código-fonte, permitindo reproduzir a estrutura do banco em diferentes ambientes.

---

# 🚀 Como executar

## Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java
* Maven
* MySQL
* Git

Clone o repositório:

```bash
git clone https://github.com/marciozenaide/Carros.git
```

Entre no diretório:

```bash
cd Carros
```

Configure as credenciais e propriedades do banco de dados de acordo com a configuração da aplicação.

Depois execute:

```bash
mvn spring-boot:run
```

---

# 📁 Estrutura

A estrutura da aplicação segue a separação por responsabilidades:

```text
src/
└── main/
    └── java/
        └── br/
            └── com/
                └── carros/
                    ├── controller/
                    ├── service/
                    ├── repository/
                    ├── entity/
                    ├── dto/
                    └── util/
```

Durante a fase JDBC, algumas responsabilidades ficam concentradas em classes de infraestrutura, como `DAO`, `ConnectionFactory` e utilitários.

---

# 📚 Evolução do Projeto

O desenvolvimento do Carros é dividido em fases.

## Fase 1 — JDBC

Objetivo: compreender o acesso ao banco de dados utilizando as APIs de baixo nível do Java.

### Implementado

* [x] Configuração do Git e GitHub
* [x] Estrutura inicial do projeto
* [x] Configuração do MySQL
* [x] Connection Factory
* [x] Controle de migrations com Flyway
* [x] CRUD utilizando JDBC
* [x] `PreparedStatement`
* [x] Mapeamento de `ResultSet` para entidades
* [x] Tratamento de valores `NULL`
* [x] Uso de `Optional`
* [x] Tratamento de exceções de banco
* [x] Logger
* [x] Internacionalização com `ResourceBundle`
* [x] Validação

### Evolução do `CarroDAO`

O DAO foi desenvolvido procurando manter o código JDBC organizado e reduzir duplicação.

Principais características:

* SQLs centralizadas em constantes;
* utilização de `try-with-resources`;
* `Optional<Carro>` para consultas por ID;
* `Collections.emptyList()` para consultas sem resultados;
* método `bindCarro()` para evitar duplicação;
* método `mapResultSet()` reutilizado;
* tratamento de `NULL` para valores `Double`;
* `save()` suportando INSERT e UPDATE;
* `delete()` retornando `boolean`;
* exceções de banco encapsuladas em `BancoDeDadosException`.

---

## Fase 2 — Servlets / JSP

Objetivo: compreender como aplicações web Java eram estruturadas antes da adoção de frameworks modernos.

Conceitos explorados:

* HTTP
* Servlets
* Request / Response
* Sessions
* JSP
* MVC
* Controllers
* Formulários
* Validação
* Integração entre camada web e DAO

---

## Fase 3 — JPA / Hibernate

Objetivo: comparar o acesso manual utilizando JDBC com uma abordagem baseada em ORM.

Conceitos explorados:

* JPA
* Hibernate
* `EntityManager`
* Entidades
* Mapeamentos
* Relacionamentos
* Persistência automática
* ORM

A ideia é demonstrar o contraste entre:

```text
JDBC

SQL
 ↓
PreparedStatement
 ↓
ResultSet
 ↓
Mapeamento manual
```

e:

```text
JPA / Hibernate

Entity
 ↓
EntityManager
 ↓
Hibernate
 ↓
Database
```

---

## Fase 4 — Spring Framework

Objetivo: introduzir os conceitos fundamentais do ecossistema Spring.

Conceitos explorados:

* Inversão de Controle (IoC)
* Dependency Injection (DI)
* Beans
* `@Component`
* `@Service`
* `@Repository`
* `@Controller`
* Spring MVC

---

## Fase 5 — Spring Boot

Objetivo: transformar a aplicação em uma API REST moderna utilizando Spring Boot.

Principais recursos:

* Spring Boot
* Spring Data JPA
* REST API
* JSON
* Dependency Injection
* Auto Configuration
* Profiles
* Tratamento global de exceções
* Validação
* DTOs
* Repository
* Service
* Controller

Arquitetura:

```text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
JPA / Hibernate
     ↓
MySQL
```

---

## Fase 6 — Angular

Objetivo: desenvolver uma interface web para consumir a API REST.

Tecnologias:

* Angular
* TypeScript
* HTML
* CSS
* HTTP Client

Arquitetura final:

```text
┌─────────────────┐
│     Angular     │
│    Frontend     │
└────────┬────────┘
         │
         │ REST / JSON
         │
         ▼
┌─────────────────┐
│   Spring Boot   │
│      API        │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  JPA / Hibernate│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│      MySQL      │
└─────────────────┘
```

---

# 📊 Evolução tecnológica

| Fase | Tecnologia      | Principal objetivo                    |
| ---- | --------------- | ------------------------------------- |
| 1    | JDBC            | Acesso direto ao banco                |
| 2    | Servlets / JSP  | Desenvolvimento web Java              |
| 3    | JPA / Hibernate | Abstração do acesso a dados           |
| 4    | Spring          | IoC e Dependency Injection            |
| 5    | Spring Boot     | APIs REST e configuração simplificada |
| 6    | Angular         | Aplicação frontend moderna            |

---

# 💡 O que este projeto demonstra?

O Carros procura responder, de forma prática, uma pergunta:

> **Como uma aplicação Java evoluiu de um código que controla diretamente conexões e SQL para uma arquitetura moderna baseada em APIs, frameworks e abstrações?**

Ao longo do projeto é possível observar a evolução de responsabilidades como:

```text
Conexão com banco
       ↓
SQL manual
       ↓
DAO
       ↓
ORM
       ↓
Repository
       ↓
Service
       ↓
REST Controller
       ↓
Frontend Angular
```

Assim, o projeto funciona como um **laboratório de aprendizado e comparação de arquiteturas**, e não apenas como uma aplicação CRUD.

---

# 🔎 Principais conceitos estudados

* JDBC
* SQL
* `Connection`
* `PreparedStatement`
* `ResultSet`
* DAO
* Servlets
* JSP
* MVC
* JPA
* Hibernate
* ORM
* Spring Framework
* IoC
* Dependency Injection
* Spring Boot
* Spring Data
* REST
* JSON
* DTO
* Validação
* Tratamento de exceções
* Flyway
* Angular
* TypeScript
* Maven

---

# 📝 Status do projeto

O projeto está em **evolução contínua**.

O objetivo não é apenas adicionar novas tecnologias, mas **implementar a mesma aplicação utilizando diferentes abordagens**, permitindo comparar suas vantagens, limitações, complexidade e nível de abstração.

```text
JDBC
  │
  ├── Controle manual
  ├── SQL explícito
  └── Baixo nível
       │
       ▼
Servlets / JSP
       │
       ▼
JPA / Hibernate
       │
       ▼
Spring Framework
       │
       ▼
Spring Boot
       │
       ▼
Angular
```

---

# 👨‍💻 Autor

**Marcio**

Projeto desenvolvido para estudo, experimentação e aprofundamento em desenvolvimento de aplicações Java e tecnologias relacionadas.

---
