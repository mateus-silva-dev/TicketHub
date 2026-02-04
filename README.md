# 🎟️ TicketHub (Core Java Edition)

![Java](https://img.shields.io/badge/Java-21-orange)
![Build](https://img.shields.io/badge/Build-Maven-blue)
![License](https://img.shields.io/badge/License-MIT-green)

> Sistema corporativo de gestão de eventos e bilheteria com foco em **Rich Domain Model** e **Arquitetura Limpa**.

## 📖 Sobre o Projeto

O **TicketHub** é um ERP para gestão de eventos, locais e vendas de ingressos. O objetivo deste projeto não é apenas criar um CRUD, mas sim implementar regras de negócio complexas, validações defensivas e integridade de dados utilizando **Java Puro** antes da migração para Frameworks (Spring Boot).

### 🚀 Diferenciais Técnicos
* **Domínio Rico:** As classes não são meros DTOs. Elas possuem regras de validação e comportamentos de negócio (Encapsulamento Real).
* **Imutabilidade:** Uso extensivo de Value Objects e listas imutáveis para prevenir *side-effects*.
* **Fail-Fast Validations:** Objetos inválidos nunca são criados na memória.
* **UUIDs:** Identificadores únicos universais gerados na aplicação, simulando ambientes distribuídos.

## 🏗️ Arquitetura Atual

O projeto segue uma estrutura modular preparatória para Hexagonal/Clean Architecture:

```text
src/main/java/br/com/mateus/tickethub
├── application
│   └── MainTest.java        # Ponto de entrada
│
├── domain                   # Regras de negócio
│   ├── evento
│   ├── ingresso
│   ├── local
│   ├── usuario
│   └── exception
│
├── infrastructure           # Detalhes técnicos
│   ├── persistence          # InMemory repositories
│   ├── client               # APIs externas (ViaCEP)
│   └── shared               # Endereço, notificação, util

```

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Build Tool:** Maven
* **Testes:** JUnit 5 (em breve)
* **CI/CD:** GitHub Actions


## 🚦 Roadmap de Desenvolvimento

* [x] Modelagem do Domínio (Local, Setor, Evento, Usuário)
* [x] Implementação de Repositórios In-Memory
* [ ] Lógica de Vendas e Controle de Estoque
* [ ] Persistência em Arquivos (I/O)
* [ ] Persistência em Banco de Dados (JDBC)
* [ ] Refatoração para Spring Boot (REST API)

## 🤝 Como Executar o Projeto

### Pré-requisitos
* Java 21
* Maven

### Passo a passo

1. Clone o repositório
```bash
git clone https://github.com/mateus-silva-dev/tickethub.git
```
2. Acesse o diretório do projeto:
```bash
cd tickethub
```
3. Compile o projeto:
```bash
mvn clean package
```
4. Execute a aplicação (Exemplo)
```bash
java -jar target/tickethub-1.0-SNAPSHOT.jar
```

## 📌 Status do Projeto
🚧 Em desenvolvimento ativo — foco atual em modelagem de domínio e arquitetura.
