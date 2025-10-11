# spring-boot-test-study


Projeto exemplo desenvolvido para estudo e prática de **testes de software** com **Spring Boot**, utilizando **JUnit 5**, **Mockito** e **Spring Boot Test**.  
O sistema implementa uma API simples de gerenciamento de tarefas (To-Do List) para demonstrar boas práticas em testes unitários e de integração.

---
##  Objetivo do Projeto

O objetivo deste projeto é servir como **material de estudo** para desenvolvedores que desejam aprender a estruturar e testar aplicações Spring Boot.  
Aqui são abordados conceitos como:
- Separação de camadas (Controller, Service, Repository)
- Testes unitários com Mockito
- Testes de integração com H2 Database
- Testes de API com MockMvc
- Boas práticas em organização de código de teste

---

##  Funcionalidades da API

| Método | Endpoint | Descrição |
|:------:|-----------|-----------|
| `POST` | `/tarefas` | Cadastra uma nova tarefa |
| `GET` | `/tarefas` | Lista todas as tarefas |
| `GET` | `/tarefas/{id}` | Busca uma tarefa pelo ID |
| `PUT` | `/tarefas/{id}` | Atualiza uma tarefa existente |
| `DELETE` | `/tarefas/{id}` | Remove uma tarefa do sistema |

---

##  Estrutura de Testes

| Tipo de Teste | Classe | Descrição |
|:---------------|:--------|:-----------|
| **Unitário** | `TarefaServiceTest` | Testa regras de negócio isoladamente com Mockito |
| **Integração (JPA)** | `TarefaRepositoryTest` | Testa o repositório com banco em memória (H2) |
| **Integração (Web)** | `TarefaControllerTest` | Testa os endpoints com MockMvc |

---

##  Estrutura do Projeto

