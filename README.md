# orcamento-api

API REST de execução orçamentária que demonstra três padrões de projeto (Singleton, Strategy e Facade) em uma regra de negócio real: o cálculo de quanto reservar de um empenho muda conforme a fonte do recurso.

Projeto do desafio de Padrões de Projeto do bootcamp de Java e IA (DIO / Itaú).

## Sobre o projeto

Um empenho é a reserva de recurso para uma despesa futura. Nem toda despesa reserva o mesmo valor que foi pedido: recurso de origem internacional (por exemplo, de agências da ONU, como em operações humanitárias) precisa de uma margem de câmbio, porque entra em moeda estrangeira. Recurso nacional reserva o valor integral.

Em vez de resolver isso com um `if/else` que cresce a cada nova fonte, o projeto usa o padrão Strategy: cada regra é uma classe própria, e o sistema escolhe a certa em tempo de execução.

## Padrões de projeto aplicados

### Strategy
Uma mesma tarefa (calcular quanto reservar) com implementações diferentes, uma por fonte de recurso. Todas seguem o mesmo contrato, a interface `RegraReservaStrategy`.
- `ReservaRecursoNacional`: reserva o valor integral.
- `ReservaRecursoInternacional`: reserva o valor mais 5% de margem de câmbio.

Adicionar uma nova fonte no futuro é criar uma nova classe, sem tocar no resto do código.

### Singleton
As regras, o selector, a fachada, o controller e o repositório são componentes gerenciados pelo Spring (`@Component`, `@Service`, `@RestController`, `@Repository`). O framework cria uma única instância de cada um e a reutiliza por toda a aplicação. É o padrão Singleton na prática, resolvido pela injeção de dependências.

### Facade
A classe `OrcamentoFacade` expõe um método único, `registrarEmpenho`, que esconde os passos internos: escolher a regra pela fonte, calcular o valor reservado, carimbar a data e salvar. Quem chama não precisa conhecer selector, strategy nem repositório.

## Fluxo de uma requisição

```
POST /empenhos (JSON)
        |
        v
EmpenhoController  ->  OrcamentoFacade  ->  SeletorRegraReserva  ->  RegraReservaStrategy
                            |                                         (Nacional | Internacional)
                            v
                     EmpenhoRepository  ->  Banco H2
```

## Tecnologias

Java 21, Spring Boot 3.3, Spring Web, Spring Data JPA, banco H2 em memória, Maven.

## Como rodar

Pré-requisitos: Java 21 ou superior e Maven.

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. O console do banco H2 fica em `http://localhost:8080/h2-console` (URL JDBC `jdbc:h2:mem:orcamento`, usuário `sa`, sem senha).

## Como testar

Registrar um empenho internacional (pede 100000, reserva 105000.00, os 5% de margem):

```bash
curl -X POST http://localhost:8080/empenhos \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Kits de abrigo","valorSolicitado":100000,"fonteRecurso":"INTERNACIONAL","naturezaDespesa":"Material de consumo","unidade":"Boa Vista"}'
```

Registrar um empenho nacional (pede 100000, reserva 100000, integral):

```bash
curl -X POST http://localhost:8080/empenhos \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Diaria de servidor","valorSolicitado":100000,"fonteRecurso":"NACIONAL","naturezaDespesa":"Diarias","unidade":"Brasilia"}'
```

Listar os empenhos registrados:

```bash
curl http://localhost:8080/empenhos
```

## Endpoints

| Método | Rota         | O que faz                                  |
|--------|--------------|--------------------------------------------|
| POST   | `/empenhos`  | Registra um empenho e calcula a reserva    |
| GET    | `/empenhos`  | Lista os empenhos registrados              |

## Estrutura

```
src/main/java/com/krueger/orcamento/
├── OrcamentoApiApplication.java   Ponto de entrada
├── model/                         Empenho e FonteRecurso
├── repository/                    EmpenhoRepository (Spring Data)
├── strategy/                      Interface, as duas regras e o selector
├── facade/                        OrcamentoFacade (a fachada)
└── controller/                    EmpenhoController (API REST)
```

## Autor

Carlos Eduardo Krueger
[linkedin.com/in/carloseduardokrueger](https://www.linkedin.com/in/carloseduardokrueger)
