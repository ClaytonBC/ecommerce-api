# 🛒 E-commerce API - Backend Java Spring Boot

API REST desenvolvida para simular o backend de um sistema de e-commerce, utilizando Java e Spring Boot.  
O projeto foi construído com foco em boas práticas de desenvolvimento backend, arquitetura em camadas, autenticação segura e integração com banco de dados relacional.

---

# 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA / Hibernate
- PostgreSQL
- Docker
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Maven
- Git / GitHub

---

# 📌 Funcionalidades

## 👤 Usuários
- Cadastro de usuários
- Login com autenticação JWT
- Controle de acesso por perfil (USER / ADMIN)

## 📦 Produtos
- Cadastro de produtos
- Listagem de produtos
- Atualização de produtos
- Remoção de produtos

## 🛒 Pedidos
- Criação de pedidos
- Consulta de pedidos
- Relacionamento entre usuário e produtos

## 🔐 Segurança
- Autenticação com Spring Security
- Geração e validação de Token JWT
- Rotas protegidas por autorização

## 📄 Documentação
- Swagger UI para testes dos endpoints

---

# 🏗️ Arquitetura do Projeto

Projeto estruturado em camadas:

- Controller
- Service
- Repository
- Entity
- DTO
- Security

---

# 🐘 Banco de Dados

Banco utilizado:

- PostgreSQL

ORM:

- JPA / Hibernate

---

# 🐳 Docker

A aplicação pode ser executada via Docker.

## Executar containers:

```bash
docker-compose up --build
