# GraphQL Dojo

API GraphQL em Spring Boot construída para responder na prática três perguntas que aparecem quando
se troca REST por GraphQL:

1. Como paginar, se GraphQL não tem `?page=1`?
2. Como proteger um endpoint único, se não existem rotas por recurso para autorizar?
3. Como evitar que um campo aninhado dispare uma consulta por item?

O domínio é propositalmente simples — usuários e seus filmes favoritos — para que o foco fique nas
decisões, e não nas regras de negócio.

## Decisões técnicas

### Paginação declarada no schema

GraphQL não paginou nada por você. Aqui a paginação virou parte do contrato, com uma `interface`
que qualquer listagem pode implementar:

```graphql
interface Pageable {
  hasNext: Boolean!
  hasPrevious: Boolean!
  actualPage: Int!
  size: Int!
  offSet: Int!
  totalPage: Int!
}

type UserPageable implements Pageable {
  ...
  content: [User]!
}
```

O cliente descobre pelo próprio schema como navegar entre as páginas, e adicionar uma nova
listagem paginada é implementar a interface em vez de reinventar os campos.

### Segurança em um endpoint só

Como toda requisição chega em `POST /graphql`, autorizar por rota deixa de funcionar. O
`SecurityConfig` resolve isso em dois níveis:

- **Resource server** valida o JWT contra o JWK set do **Keycloak**, sem sessão e sem consultar o servidor de identidade a cada requisição.
- **OAuth2 Login** com **Auth0** cobre o fluxo interativo do navegador, usado para abrir o GraphiQL autenticado.

O `/graphiql` fica liberado para exploração, enquanto `/graphql` exige autenticação.

### Camada de dados

Entidades JPA com H2 em memória, e um `MovieRepositoryImpl` para as consultas que não cabem em
query method derivado. Um `DataLoader` (`CommandLineRunner`) popula a base no start, então o
projeto sobe pronto para ser consultado — sem seed manual antes da primeira query.

## Stack

Java 17 · Spring Boot · Spring for GraphQL · Spring Data JPA · Spring Security (OAuth2 Resource
Server + OAuth2 Client) · H2 · Keycloak · Lombok · Docker Compose

## Rodando localmente

```bash
docker compose up -d      # sobe o Keycloak em localhost:8080
./mvnw spring-boot:run    # API em localhost:8084
```

Abra o **GraphiQL** em `http://localhost:8084/graphiql` para explorar o schema.

O Keycloak sobe com admin `admin`/`admin`. Crie o realm usado pela aplicação e configure as
variáveis abaixo antes de subir a API:

| Variável | Para quê |
| --- | --- |
| `JWT_ISSUER_URI` | Issuer do realm no Keycloak |
| `JWT_JWK_SET_URI` | Endpoint de chaves públicas do realm |
| `AUTH0_CLIENT_ID`, `AUTH0_CLIENT_SECRET` | Aplicação Auth0 usada no login pelo navegador |
| `AUTH0_ISSUER_URI` | Domínio do tenant Auth0 |

## Consultas de exemplo

```graphql
query {
  getUsers(page: 0, size: 10) {
    actualPage
    totalPage
    hasNext
    content {
      id
      name
      movies { name releaseYear }
    }
  }
}

mutation {
  addUser(userInput: { name: "Ana", email: "ana@example.com", age: 28 }) {
    id
    name
  }
}
```

---

Projeto de estudo. O objetivo é a decisão técnica documentada acima, não um produto pronto para
produção.
