
# 🏆 Sistema de Cadastro de Times

Este projeto é um sistema completo para **cadastro, gerenciamento e análise de times de e-Sports**, permitindo o armazenamento de composições de equipes por data, análise estatística de jogadores, funções e franquias mais comuns.

O sistema é dividido em duas partes principais:
- Backend (Java + Spring Boot + PostgreSQL)
- Frontend (Angular 14)

---

## ✅ Requisitos para Execução

### 🔧 Backend
Para executar o backend, você precisará dos seguintes softwares instalados:

- **Java JDK 8 ou superior**
    - Verifique com: `java -version`
- **Apache Maven 3.6 ou superior**
    - Verifique com: `mvn -version`
- **PostgreSQL 10 ou superior**
    - Banco de dados local rodando com um banco chamado `duxus-desafio`
- **IDE recomendada:** IntelliJ IDEA ou Eclipse (opcional, mas facilita)

### 🌐 Frontend
Para o frontend Angular:

- **Node.js (versão 16.x ou superior)**
    - Verifique com: `node -v`
- **NPM (gerenciador de pacotes, geralmente vem com o Node)**
    - Verifique com: `npm -v`
- **Angular CLI (versão 14 ou superior)**
    - Instale com:
      ```bash
      npm install -g @angular/cli
      ```

---

## 🚀 Tecnologias Utilizadas

### Backend:
- **Java 8**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

### Frontend:
- **Angular 14**
- **TypeScript**
- **Bootstrap / CSS**

---

## 🛠️ Como Executar o Projeto

### 📁 Backend (Spring Boot)

1. **Configure o PostgreSQL**:
    - Crie um banco de dados chamado `duxus-desafio`.
    - Verifique se o PostgreSQL está rodando na porta padrão (5432).
    - Use o seguinte `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/duxus-desafio
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   spring.datasource.driver-class-name=org.postgresql.Driver

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true
   ```

2. **Execute o backend**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   O backend será iniciado em: `http://localhost:8080`

---

### 🌐 Frontend (Angular)

1. **Instale as dependências**:
   ```bash
   npm install
   ```

2. **Execute o servidor de desenvolvimento Angular**:
   ```bash
   ng serve
   ```

3. **Acesse o sistema via navegador**:
   ```
   http://localhost:4200
   ```

---

## ✨ Funcionalidades do Sistema

- **Cadastro de Time**: preencha data e escolha jogadores para compor um time.
- **Consultas Inteligentes**:
    - Buscar time por data
    - Encontrar o jogador mais utilizado entre datas
    - Ver integrantes do time mais comum
    - Identificar a função mais comum
    - Descobrir a franquia mais usada
    - Contar aparições por função ou franquia

---

## 📦 Estrutura do Projeto

### Backend
```
src/
└── main/
    ├── java/
    │   └── br.com.duxusdesafio/
    │       ├── controller/
    │       ├── model/
    │       ├── repository/
    │       └── service/
    └── resources/
        └── application.properties
```

### Frontend
```
src/
└── app/
    ├── components/
    ├── models/
    ├── services/
    └── app.component.ts
```

---

## 🧪 Testes

O projeto contém exemplos de testes unitários (JUnit + Mockito no backend) e pode ser facilmente estendido para testes de integração.

Para rodar os testes no backend:

```bash
mvn test
```

---

## Possíveis Melhorias

### Validações

1. **Validação de campos obrigatórios**: Garantir que todos os campos necessários sejam preenchidos ao cadastrar um jogador ou time.
    - Exemplo: Verificar se o nome do jogador e o time foram preenchidos antes de salvar no banco de dados.

2. **Validação de formatos de data**: Verificar se as datas são inseridas no formato correto (`yyyy-MM-dd`).
    - Exemplo: A data de nascimento dos jogadores deve ser validada para não permitir datas futuras.

3. **Validação de duplicidade**: Impedir que jogadores sejam adicionados mais de uma vez ao mesmo time ou que o time seja duplicado.
    - Exemplo: Antes de adicionar um jogador a um time, verificar se ele já não está no time.

### Uso de Docker

O uso de Docker pode melhorar o processo de deploy e facilitar o uso do sistema em diferentes ambientes. A seguir, algumas sugestões para configurar Docker para o projeto:

1. **Backend (Spring Boot)**:


2. **Frontend (Angular)**:
    - Criar um `Dockerfile` para a aplicação frontend:


3. **Docker Compose**:
    - Criar um `docker-compose.yml` para orquestrar o backend e frontend juntos:

### Testes

- **Testes de Integração**: Garantir que as interações entre o backend e o banco de dados estejam funcionando corretamente.
    - Exemplo: Criar testes para verificar se os dados do banco estão sendo persistidos corretamente ao cadastrar um novo time ou jogador.

### Autenticação e Autorização

Adicionar **autenticação** e **autorização** ao sistema pode melhorar a segurança, permitindo que apenas usuários autenticados possam interagir com certos endpoints. Uma abordagem comum seria usar **JWT (JSON Web Tokens)** para autenticação e autorização de usuários.

1. **JWT Authentication**:
    - Implementar um serviço de autenticação que gere um token JWT no momento do login do usuário.
    - Proteger endpoints sensíveis no backend para que apenas usuários autenticados possam acessá-los.

### Paginação e Filtros

Melhorar a experiência do usuário e a performance da aplicação, permitindo **paginação** e **filtros** nas consultas.

1. **Paginação**: Implementar paginação nas consultas de times e jogadores, especialmente em cenários onde o banco de dados possui muitos registros.
    - Exemplo: Adicionar parâmetros de página e limite nas consultas de times.

2. **Filtros**: Permitir a filtragem de times e jogadores com base em diferentes critérios, como nome, data de criação, e funções dos jogadores.
    - Exemplo: Adicionar campos de busca na interface para filtrar por nome do time ou do jogador.

### Internacionalização

Adicionar **suporte a múltiplos idiomas** na interface do usuário pode tornar a aplicação mais acessível a um público global. A internacionalização pode ser implementada utilizando o módulo de i18n do Angular.

- Adicionar traduções para diferentes idiomas (ex.: inglês, espanhol, português).
- Exemplo: Configurar o Angular para carregar as traduções e alternar entre os idiomas.

---


## 📄 Licença

Este projeto é de livre uso para fins educacionais e de demonstração.

---

## 📬 Contato

Em caso de dúvidas ou sugestões, entre em contato com o mantenedor do projeto.
