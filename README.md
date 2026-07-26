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

- Revisão do DAO, o estado dele hoje está muito bom:

✅ SQLs centralizadas em constantes.
✅ try-with-resources em todos os acessos.
✅ Optional<Carro> para busca por id.
✅ Collections.emptyList() para pesquisas vazias.
✅ Método bindCarro() evitando duplicação.
✅ Método mapResultSet() reutilizado.
✅ Método getNullableDouble() para tratar NULL.
✅ save() funcionando para insert e update.
✅ delete() retornando boolean.
✅ Exceções encapsuladas em BancoDeDadosException

Pontos positivos
✅ Classe final.
✅ Construtor privado.
✅ Carregamento do db.properties apenas uma vez.
✅ Registro do driver apenas uma vez.
✅ Uso de constantes para as chaves das propriedades.
✅ getConnection() simples e limpo.
✅ Boa separação de responsabilidades.

Agora eu seguiria esta sequência:

✅ ConnectionFactory (concluído)
✅ Logger
✅ Internacionalização (ResourceBundle)
✅ Validação (CarroValidator)
✅ Servlets
✅ JSP
✅ Finalizar a fase JDBC
🚀 Criar a branch da Fase 2 (Spring Boot)

Níveis que eu usaria

Método	Quando usar

severe()	Erros
warning()	Situações inesperadas
info()		CRUD (salvar, excluir, atualizar)
fine()		SQL, abertura de conexão
finer()		Depuração detalhada

br.com.carros.util
├── ConnectionFactory.java
├── LogFactory.java
├── Messages.java        (internacionalização)
└── ValidationUtils.java (na etapa seguinte)


## Autor

Marcio
