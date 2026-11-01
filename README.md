# orcamento-api

> A Spring Boot REST API that shows three design patterns (Singleton, Strategy, Facade) solving a real rule: how much of a budget commitment to reserve depends on where the money comes from.

Built for the Design Patterns challenge of the DIO Itaú "Java with AI" Bootcamp.

## Overview
In public budgeting, a commitment (empenho) reserves money for a future expense. The amount actually reserved is not always what was requested. Funds from international sources, for example UN agencies in humanitarian operations, need a foreign exchange margin, while national funds are reserved in full. Instead of a growing chain of if/else, this project uses the Strategy pattern so each rule is its own class and the right one is picked at runtime.

## Features
- Register a budget commitment and reserve the correct amount based on the funding source.
- Two interchangeable rules: national (full amount) and international (amount plus a 5% exchange margin).
- List all registered commitments.
- In-memory H2 database, no external setup required.

## My improvement
The challenge allowed reproducing the reference lab or building something new. I built a new domain from scratch instead of cloning the sample "customer and address" API. The reserve calculation is a real Strategy (one rule per funding source), a selector lets Spring inject every rule as a list and choose the right one, and a Facade hides the whole flow (select the rule, calculate, timestamp, persist) behind a single method. The domain comes from my own background in budget operations.

## Design patterns
- **Singleton:** the rules, the selector, the facade, the controller and the repository are Spring-managed beans, created once and reused across the application.
- **Strategy:** `RegraReservaStrategy` has one implementation per funding source (`ReservaRecursoNacional`, `ReservaRecursoInternacional`), chosen at runtime by `SeletorRegraReserva`.
- **Facade:** `OrcamentoFacade` exposes a single `registrarEmpenho` method that hides selection, calculation, timestamp and persistence.

## Tech stack
Java 21, Spring Boot 3.3, Spring Web, Spring Data JPA, H2 (in-memory), Maven.

## How to run
```bash
mvn spring-boot:run
```
The app starts on http://localhost:8080. H2 console at http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:orcamento`, user `sa`, no password). Requires Java 21 or newer and Maven.

## How to test the main flow
International commitment (requests 100000, reserves 105000.00, the 5% margin):
```bash
curl -X POST http://localhost:8080/empenhos \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Shelter kits","valorSolicitado":100000,"fonteRecurso":"INTERNACIONAL","naturezaDespesa":"Supplies","unidade":"Boa Vista"}'
```
National commitment (requests 100000, reserves 100000, in full):
```bash
curl -X POST http://localhost:8080/empenhos \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Per diem","valorSolicitado":100000,"fonteRecurso":"NACIONAL","naturezaDespesa":"Per diem","unidade":"Brasilia"}'
```
List all commitments:
```bash
curl http://localhost:8080/empenhos
```

| Method | Route        | What it does                                  |
|--------|--------------|-----------------------------------------------|
| POST   | `/empenhos`  | Registers a commitment and reserves the value |
| GET    | `/empenhos`  | Lists the registered commitments              |

## What I learned
How the Strategy pattern replaces branching with small, testable classes; how Spring turns dependency injection into the Singleton pattern for free; and how a Facade keeps the controller simple by hiding the moving parts behind one call.

---
**Carlos Eduardo Krueger** · [github.com/KruegerEngenheiro](https://github.com/KruegerEngenheiro) · [linkedin.com/in/carloseduardokrueger](https://linkedin.com/in/carloseduardokrueger) · Built during the DIO Itaú "Java with AI" Bootcamp.
