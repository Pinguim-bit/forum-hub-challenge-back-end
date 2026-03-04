# ForumHub API 🚀

API REST completa desenvolvida como parte do **Challenge Back End da Alura**, simulando o funcionamento de um fórum de dúvidas.

---

## 💡 Sobre

O ForumHub permite que usuários autenticados criem, visualizem, atualizem e removam tópicos de discussão. Todo o fluxo foi construído do zero, desde a modelagem do banco de dados até a segurança da API com JWT.

---

## ✅ O que foi implementado

- [x] CRUD completo de tópicos
- [x] Validações de regras de negócio (títulos e mensagens duplicados, campos obrigatórios)
- [x] Paginação e ordenação dos tópicos por data de criação
- [x] Autenticação com Spring Security + JWT
- [x] Criptografia de senhas com BCrypt
- [x] Migrations com Flyway
- [x] Documentação interativa com Swagger (SpringDoc OpenAPI)

---

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot**
- **Spring Security** — autenticação e autorização
- **Spring Data JPA** — persistência de dados
- **MySQL** — banco de dados relacional
- **Flyway** — controle de migrations
- **JWT (java-jwt)** — geração e validação de tokens
- **SpringDoc OpenAPI** — documentação via Swagger
- **Intellij IDEA** - IDE de desenvolvimento

---

## 🔐 Autenticação

A API é protegida por **JWT**. Apenas o cadastro e o login são públicos — todos os demais endpoints exigem autenticação.

**1. Crie uma conta:**
```http
POST /create-account
*em formato JSON
{
    "nome":"Seu nome",
    "email":"Seu email",
    "senha":"Sua senha"
}
```

**2. Faça login e receba o token:**
```http
POST /login
*em formato JSON
{
    "email":"seu email",
    "senha":"sua senha"
}

```

**3. Use o token nas requisições:**
```
Authorization: Bearer <token>
```

**Importante**
```
Ao criar uma conta será automaticamente criado um perfil com nome e id.
Você deverá informar o id do PERFIL ao mandar um POST no endpoint para criar um tópico. 
```

---

## 📡 Endpoints

### Autenticação
| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| POST | `/create-account` | Criar conta | ❌ |
| POST | `/login` | Autenticar e receber token | ❌ |

### Tópicos
| Método | Rota | Descrição | Auth |
|--------|------|-----------|------|
| GET | `/topicos` | Listar tópicos (paginado, ordenado por data) | ✅ |
| POST | `/topicos` | Criar novo tópico | ✅ |
| GET | `/topicos/{id}` | Buscar tópico por ID | ✅ |
| PUT | `/topicos/{id}` | Atualizar tópico | ✅ |
| DELETE | `/topicos/{id}` | Deletar tópico | ✅ |

**Crie um post (Necessãrio cadastrar uma conta e um curso antes)**
```
POST /topicos
*em formato JSON
{
    "titulo":"Meu Tituto",
    "mensagem":"Olá Mundo",
    "autor_id": id do perfil,
    "curso_id": id do curso
}

*você pode listar todos os usuários ou um usuário específico
para obter o id_perfil (instruções mais abaixo)
```
**Atualize um post**
```
PUT /topicos
*em formato JSON
{
    "titulo":"novo título",
    "mensagem":"nova mensagem"
}
```

### Cursos
| Método | Rota | Descrição                                    | Auth |
|--------|------|----------------------------------------------|------|
| GET | `/cursos` | Listar cursos cadastrados (obtem o id junto) | ✅ |
| POST | `/cursos` | Criar novo curso                             | ✅ |

**Crie cursos no banco de dados (é necesário para criar tópicos)**
```http
POST /cursos
*em formato JSON
{
    "nome":"nome do curso",
    "categoria":"categoria do curso(String)"
}
```

### Usuários
| Método | Rota | Descrição                                                                     | Auth |
|--------|------|-------------------------------------------------------------------------------|------|
| GET    | `/usuarios` | Lista todos os usuários com o id_perfil incluso                               | ✅ |
| GET    | `/usuarios/{id}` | obtêm informações sobre um usuário via id do usuário (diferente do id_perfil) | ✅ |
| PUT    | `/usuarios/{id}` | Atualiza o perfil do usuário via id do usuário (diferente do id_perfil)       | ✅ |
| DELETE | `/usuarios/{id}` | Deleta o usuário via id do usuário (perfil é deletado automaticamente)        | ✅ |

**Atualize informações sobre o usuário**
```http
PUT /usuários
*em formato JSON
{
    "nome":"novo nome",
    "email":"novo email",
    "senha":"nova senha"
}
```

---

## 🚀 Como executar localmente

**Pré-requisitos:** Java 17, MySQL

**1. Clone o repositório:**

**2. Configure o banco no `application.properties`:**
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/forum_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

api.security.token.secret=seu_secret_jwt
```

**3. Execute:**
```
De preferência execute o projeto no Intellij IDEA
```

> O Flyway criará as tabelas automaticamente na primeira execução.

---

## 👨‍💻 Autor: Ryan Marcello

Desenvolvido como conclusão do **Challenge Back End — Alura**.

`Novidades em breve (Talvez)`
