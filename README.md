# 🚗 Carros

O **Carros** é um projeto Java desenvolvido como um **laboratório prático de evolução tecnológica e arquitetural**.

A mesma aplicação é utilizada como base para estudar, construir, evoluir e comparar diferentes abordagens de desenvolvimento de aplicações Java.

O projeto acompanha duas linhas principais de evolução:

- ☕ evolução da plataforma Java;
- 🌐 evolução das arquiteturas e tecnologias utilizadas em aplicações web.

A evolução é registrada no **Git**, permitindo acompanhar não apenas o resultado final, mas também as decisões, refatorações, correções e mudanças tecnológicas realizadas durante o desenvolvimento.

---

# 🎯 Objetivos

## ☕ Evolução da plataforma Java

O projeto será utilizado para acompanhar a evolução da plataforma Java:

```text
Java 8
  ↓
Java 9
  ↓
Java 11
  ↓
Java 17
  ↓
Java 21
  ↓
Java 25

A cada etapa serão estudados recursos introduzidos na linguagem e na plataforma, novas APIs, mudanças de sintaxe, melhorias e impactos sobre um código existente.

🌐 Evolução arquitetural

Paralelamente, a aplicação evolui progressivamente de uma implementação de baixo nível para uma arquitetura moderna:

JDBC
  ↓
Servlets / JSP
  ↓
JAX-RS
  ↓
JPA / Hibernate
  ↓
Spring Framework
  ↓
Spring Boot
  ↓
Angular

O objetivo é compreender não apenas como utilizar cada tecnologia, mas também quais problemas cada nova abstração procura resolver.

📌 Estado atual

O projeto já passou da implementação inicial do CRUD JDBC e possui uma evolução significativa registrada no Git.

Evolução	Status	Resultado
Estrutura inicial JDBC	
✅ Concluída	Base da aplicação CRUD JDBC	
✅ Concluído	Persistência manual utilizando JDBC Aplicação Web	
✅ Concluída	Servlets, HTTP e renderização Validação e internacionalização	
✅ Concluídas	Validação, ResourceBundle e mensagens Testes automatizados	
✅ Implementados	Testes unitários e de integração H2	
✅ Implementado	Banco para contexto de testes Maven	
✅ Implementado	Build e gerenciamento de dependências JAX-RS	
🚧 Em desenvolvimento	API REST Docker	
🚧 Inicial	Containerização JPA / Hibernate	
📋 Planejado	ORM Spring Framework	
📋 Planejado	IoC e Dependency Injection Spring Boot	
📋 Planejado	API REST moderna Angular	
📋 Planejado	Frontend separado

O status acima representa a evolução registrada no histórico do projeto. Algumas melhorias são transversais à arquitetura, como Maven, testes, H2 e Docker.

🌳 Histórico de evolução

O histórico do Git faz parte da proposta do projeto.

A evolução começou com a estrutura inicial da fase JDBC:

3ab7561
Inicia fase 1 - estrutura JDBC

A partir dessa base, o projeto evoluiu progressivamente.

Linha de evolução
JDBC
 │
 ├── CRUD
 │
 ├── Testes automatizados
 │
 ├── Service Layer
 │
 ├── Servlets
 │
 ├── Validação
 │
 ├── Internacionalização
 │
 ├── Filtros HTTP
 │
 ├── Headers de segurança
 │
 └── Testes unitários e de integração
        │
        ▼
      JAX-RS
        │
        ├── CarroResource
        ├── RestApplication
        ├── Jackson
        └── API REST

Paralelamente, o projeto recebeu evoluções de infraestrutura e ferramentas:

Maven
  ↓
H2
  ↓
Testes automatizados
  ↓
Docker
🌿 Branches do projeto

As branches representam diferentes etapas de desenvolvimento.

Branch	Objetivo
fase-1-crud-jdbc	Implementação do CRUD utilizando JDBC
build/migrate-to-maven	Migração do projeto para Maven
test/initialize-h2-schema	Preparação do schema H2 para testes
test/improve-automated-tests	Evolução dos testes automatizados
feature/rest-carro-api	Implementação da API REST utilizando JAX-RS

As branches não representam necessariamente fases arquiteturais.

Algumas representam evoluções transversais, como build, testes e infraestrutura.

🧱 Arquitetura
Arquitetura JDBC

A implementação inicial utiliza acesso manual ao banco:

Application
     │
     ▼
CarroService
     │
     ▼
CarroDAO
     │
     ▼
JDBC
     │
     ▼
MySQL

O CarroDAO concentra as operações de persistência e utiliza recursos da API JDBC diretamente.

Entre as principais características estão:

SQLs centralizadas;
Connection;
PreparedStatement;
ResultSet;
try-with-resources;
Optional<Carro>;
Collections.emptyList();
bindCarro();
mapResultSet();
tratamento de valores NULL;
save() para INSERT e UPDATE;
delete() retornando boolean;
encapsulamento de exceções de banco.

🌐 Aplicação Web

A aplicação evoluiu posteriormente para um modelo web baseado em Servlets.

HTTP Request
     │
     ▼
CarroServlet
     │
     ▼
CarroService
     │
     ▼
CarroDAO
     │
     ▼
JDBC
     │
     ▼
MySQL

Durante essa etapa foram estudados:

HTTP;
Request / Response;
Servlets;
JSP;
MVC;
formulários;
validação;
sessões;
renderização HTML;
integração entre camada web e persistência.

Também foram introduzidos componentes e melhorias como:

CarroHtmlRenderer;
CharacterEncodingFilter;
headers de segurança HTTP;
ResourceBundle;
MessageKeys;
melhorias na validação.

🔌 API REST com JAX-RS

A branch feature/rest-carro-api representa a evolução da aplicação para uma API REST utilizando JAX-RS.

Principais componentes desenvolvidos:

CarroResource;
RestApplication;
configuração do JAX-RS;
Jackson;
serialização e desserialização JSON;
endpoints HTTP;
métodos HTTP;
códigos de status.

Arquitetura atual:

HTTP Request
     │
     ▼
CarroResource
     │
     ▼
CarroService
     │
     ▼
CarroDAO
     │
     ▼
JDBC
     │
     ▼
MySQL

Essa etapa permite estudar uma API REST antes da introdução do Spring Boot.

🧪 Testes automatizados

Os testes fazem parte da evolução do projeto desde as primeiras etapas.

A branch:

test/improve-automated-tests

introduziu e aprimorou diferentes tipos de testes.

Entre eles:

testes unitários do CarroService;
testes de integração do CarroDAO;
testes do CarroValidator;
configuração de banco H2 para testes;
melhorias na organização dos testes;
correções relacionadas à codificação.

A intenção é que a evolução arquitetural não aconteça sem preocupação com a qualidade e a verificabilidade do código.

🛠️ Build e infraestrutura Maven

O projeto foi migrado para Maven para centralizar:

dependências;
compilação;
testes;
empacotamento;
configuração do projeto.
Docker

O projeto também possui uma configuração inicial de Docker, utilizada como parte da evolução da infraestrutura.

🗄️ Banco de Dados

O banco principal utilizado pelo projeto é o MySQL.

Banco:

carros

Durante os testes também é utilizado H2, permitindo executar testes de persistência em um banco dedicado ao ambiente de testes.

Migrations

A evolução do banco também faz parte da história do projeto.

As alterações do schema são registradas através de migrations versionadas junto ao código-fonte.

Versão	Migration	Objetivo
V1	Migration inicial	Criação inicial da estrutura do banco
V2	V2__corrige_encoding_carro.sql	Correção do encoding dos dados de carro
V3	V3__recupera_encoding_carro.sql	Recuperação do encoding dos dados de carro

As migrations permitem acompanhar a evolução do schema e registrar problemas encontrados e suas respectivas correções.

🗺️ Roadmap

O roadmap representa o caminho planejado do projeto.

Ele é diferente do histórico do Git, que representa aquilo que já foi implementado.

Arquitetura
JDBC
  ↓
Servlets / JSP
  ↓
JAX-RS
  ↓
JPA / Hibernate
  ↓
Spring Framework
  ↓
Spring Boot
  ↓
Angular
Java
Java 8
  ↓
Java 9
  ↓
Java 11
  ↓
Java 17
  ↓
Java 21
  ↓
Java 25

🗃️ Próxima evolução — JPA / Hibernate

A próxima grande evolução arquitetural será a comparação entre JDBC e ORM.

JDBC
SQL
 ↓
PreparedStatement
 ↓
ResultSet
 ↓
Mapeamento manual
JPA / Hibernate
Entity
 ↓
EntityManager
 ↓
Hibernate
 ↓
Database

Serão estudados:

JPA;
Hibernate;
ORM;
entidades;
mapeamentos;
relacionamentos;
EntityManager;
transações;
ciclo de vida das entidades;
diferenças entre JDBC e ORM.

🌱 Spring Framework

Após a etapa de JPA/Hibernate, serão introduzidos os fundamentos do Spring.

Principais conceitos:

IoC;
Dependency Injection;
Beans;
@Component;
@Service;
@Repository;
@Controller;
Spring MVC.

A intenção é compreender os fundamentos do framework antes de utilizar as abstrações oferecidas pelo Spring Boot.

🚀 Spring Boot

A aplicação será posteriormente evoluída para uma API REST baseada em Spring Boot.

Arquitetura prevista:

HTTP
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

Serão estudados:

Spring Boot;
Spring Data JPA;
REST;
JSON;
Dependency Injection;
Auto Configuration;
Profiles;
DTOs;
validação;
tratamento global de exceções.

🅰️ Angular

Na etapa final, será desenvolvido um frontend separado utilizando Angular.

Arquitetura prevista:

┌─────────────────────┐
│       Angular       │
│      Frontend       │
└──────────┬──────────┘
           │
           │ HTTP / JSON
           ▼
┌─────────────────────┐
│     Spring Boot     │
│      REST API       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   JPA / Hibernate   │
│     Persistence     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│        MySQL        │
└─────────────────────┘

Tecnologias previstas:

Angular;
TypeScript;
HTML;
CSS;
HTTP Client.

☕ Evolução do Java

Uma das características centrais do projeto é utilizar a mesma aplicação para acompanhar a evolução da plataforma Java.

Versão	Foco
Java 8	Base inicial da aplicação
Java 9	Evolução da plataforma e módulos
Java 11	Novas APIs e melhorias
Java 17	Recursos modernos da linguagem
Java 21	Recursos modernos da plataforma
Java 25	Recursos recentes da plataforma

A evolução será registrada através de commits, branches e tags quando apropriado.

O objetivo é poder comparar como o mesmo projeto pode evoluir junto com diferentes gerações da plataforma Java.

📊 Evolução tecnológica
                    CARROS
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
   EVOLUÇÃO JAVA             EVOLUÇÃO WEB
          │                         │
          ▼                         ▼
       Java 8                    JDBC
          ↓                         ↓
       Java 9                  Servlets
          ↓                         ↓
      Java 11                   JAX-RS
          ↓                         ↓
      Java 17               JPA / Hibernate
          ↓                         ↓
      Java 21                   Spring
          ↓                         ↓
      Java 25                Spring Boot
                                    ↓
                                  Angular

Além dessas duas linhas principais, o projeto evolui transversalmente através de:

Maven
  ↓
Testes
  ↓
H2
  ↓
Logging
  ↓
Validação
  ↓
Segurança HTTP
  ↓
Docker

▶️ Como executar

Pré-requisitos

Para trabalhar com o projeto localmente:

Java;
Maven;
MySQL;
Git.

A versão do Java deve acompanhar a etapa atual do projeto.

1. Clonar o projeto
git clone https://github.com/marciozenaide/Carros.git
cd Carros
2. Configurar o banco

Crie o banco:

CREATE DATABASE carros;

Configure as credenciais utilizadas pela aplicação.

Exemplo:

db.url=jdbc:mysql://localhost:3306/carros
db.username=seu_usuario
db.password=sua_senha

Não versione senhas ou outras credenciais reais no repositório.

3. Compilar
mvn clean package
4. Executar os testes
mvn test
5. Executar a aplicação

Nas etapas baseadas em Servlets/JSP e JAX-RS, o projeto é empacotado como WAR e deve ser executado em um servidor compatível com a tecnologia utilizada.

A execução através de:

mvn spring-boot:run

será utilizada somente quando o projeto chegar à etapa Spring Boot.

O comando mvn spring-boot:run pertence à futura etapa Spring Boot e não representa a forma de execução da implementação JAX-RS atual.

📚 Principais conceitos estudados
Java
Java 8;
Java 9;
Java 11;
Java 17;
Java 21;
Java 25;
evolução da linguagem;
evolução das APIs;
modularização;
compatibilidade entre versões.
Persistência
JDBC;
SQL;
DAO;
JPA;
Hibernate;
ORM;
Repository;
transações.
Web
HTTP;
Servlets;
JSP;
MVC;
JAX-RS;
REST;
JSON;
Status Codes;
filtros;
segurança HTTP.
Arquitetura
separação de responsabilidades;
Service Layer;
Repository;
DTO;
IoC;
Dependency Injection;
baixo acoplamento;
testabilidade.
Qualidade
testes unitários;
testes de integração;
validação;
logging;
tratamento de exceções;
internacionalização.
Ferramentas
Maven;
MySQL;
H2;
Docker;
Git;
GitHub.

💡 O que este projeto demonstra?

O Carros procura responder, de forma prática:

Como a plataforma Java evoluiu ao longo das diferentes versões?

E também:

Como uma aplicação Java evoluiu de um código que controla diretamente conexões e SQL para uma arquitetura moderna baseada em APIs, ORM, frameworks e frontend separado?

Ao longo do projeto, as responsabilidades evoluem aproximadamente desta forma:

Connection
    ↓
SQL manual
    ↓
DAO
    ↓
Service
    ↓
Servlet
    ↓
JAX-RS Resource
    ↓
JPA / Hibernate
    ↓
Repository
    ↓
Spring
    ↓
Spring Boot
    ↓
Angular

O projeto funciona, portanto, como um laboratório de aprendizado, experimentação, comparação e evolução de tecnologias e arquiteturas Java.

🧭 Filosofia do projeto

O projeto é desenvolvido de forma incremental.

Cada evolução deve procurar responder a uma pergunta técnica:

Como funciona o acesso ao banco sem ORM?
Quais responsabilidades pertencem ao DAO?
Como uma aplicação Java atende uma requisição HTTP?
O que muda quando saímos de HTML renderizado no servidor para REST?
Quais problemas o ORM resolve?
O que o Spring abstrai?
O que o Spring Boot simplifica?
Como separar frontend e backend?
O que muda entre diferentes versões do Java?
Quais são os benefícios e custos de cada abstração?

A intenção não é simplesmente chegar à arquitetura final.

A própria evolução da aplicação é o objeto de estudo.

🚧 Status

Em desenvolvimento.

O projeto já possui:

base JDBC;
CRUD completo;
camada de serviço;
aplicação web com Servlets;
validação;
internacionalização;
logging;
testes unitários;
testes de integração;
H2 para testes;
Maven;
API REST em evolução com JAX-RS;
configuração inicial de Docker;
migrations de banco versionadas.

Próximas etapas:

JAX-RS
  ↓
JPA / Hibernate
  ↓
Spring Framework
  ↓
Spring Boot
  ↓
Angular

Paralelamente:

Java 8
  ↓
Java 9
  ↓
Java 11
  ↓
Java 17
  ↓
Java 21
  ↓
Java 25

O histórico de commits, branches e tags é parte integrante da documentação do projeto.

👨‍💻 Autor

Marcio Zenaide

Projeto desenvolvido para estudo, experimentação e aprofundamento em:

desenvolvimento Java;
APIs REST;
persistência de dados;
arquitetura de software;
testes automatizados;
frameworks;
infraestrutura;
desenvolvimento frontend.