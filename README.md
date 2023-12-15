#Dass21

Bem-vindo ao projeto Dass21 API, uma aplicação Java/Spring para mensurar diferentes níveis de depressão, ansiedade e estresse.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- [Mavem](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)


## Configuração do Ambiente

1. Clone o repositório:

   ```bash
   git clone https://github.com/cleberamancio/dass21.git
2. Navegue até o diretório do projeto:

   ```bash
   cd dass21
   
3. Construa o projeto com o Maven:

   ```bash
   mvn clean install
4. Definir um arquivo .env na raiz do projeto com as credenciais do Mysql no Docker:
   ```bash
   MYSQL_HOST=localhost
   MYSQL_PORT=3306
   MYSQL_DATABASE=dass21_db
   MYSQL_USERNAME=root
   MYSQL_PASSWORD=Abcd1234@

6. Crie uma pasta mysql na raiz do projeto, pois o Docker com o mysql utiliza como volume para armazenamento:
   ```bash
   mkdir mysql
   
7. Execute o Docker Compose na raiz do projeto para iniciar o banco de dados:
   ```bash
   docker-compose up -d
   
8. Execute o Docker Compose para iniciar o banco de dados:
   ```bash
   mvn spring-boot:run

6. Após iniciar o servidor local, é possivel acessar um dos recursos da API com uma ferramenta de consulta, como Insomnia ou Postman, utilizando o vebo HTTP Get e o recurso abaixo:
   ```bash
   http://localhost:8080/api/pesquisa
   
Recursos da API
   - **Exibir pesquisa** - *Exibe a lista com as informações de todos os participantes.*
   - **Salvar resposta na pesquisa** - *Salva a resposta de um Participante na pesquisa.*
   - **Edição de Ordem de Serviço** - *Edite uma ordem de serviço existente.*
   - **Exibe a pesquisa de um participante em específico.**
   - **Edita um participante e sua resposta na pesquisa.**
   - **Criar novo Participante** - *Cria um novo participante na pesquisa.*

Documentação da plataforma
   ```bash
   http://localhost:8080/swagger-ui/index.html
   http://[Seu Host]/swagger-ui/index.html