# Sistema de Gestão de Portfólio de Projetos

Este é um sistema de gerenciamento de portfólio de projetos desenvolvido com Spring Boot, oferecendo uma API REST para gestão de projetos e membros de equipe.

## 🚀 Funcionalidades

### Gestão de Projetos
- CRUD completo de projetos
- Controle de status do projeto
- Classificação automática de risco
- Alocação de membros
- Relatórios e métricas

### Controle de Membros
- API mockada para gestão de membros
- Validação de alocações
- Controle de funções

## 📋 Regras de Negócio

### Projetos
- **Campos Obrigatórios:**
  - Nome
  - Data de início
  - Previsão de término
  - Data real de término
  - Orçamento total
  - Descrição
  - Gerente responsável
  - Status atual

### Classificação de Risco
- **Baixo Risco:**
  - Orçamento até R$ 100.000
  - Prazo ≤ 3 meses
- **Médio Risco:**
  - Orçamento entre R$ 100.001 e R$ 500.000
  - Prazo entre 3 a 6 meses
- **Alto Risco:**
  - Orçamento acima de R$ 500.000
  - Prazo superior a 6 meses

### Status do Projeto
Sequência obrigatória:
1. Em análise
2. Análise realizada
3. Análise aprovada
4. Iniciado
5. Planejado
6. Em andamento
7. Encerrado
- Status "Cancelado" pode ser aplicado a qualquer momento

### Restrições
- Projetos em status iniciado, em andamento ou encerrado não podem ser excluídos
- Mínimo de 1 e máximo de 10 membros por projeto
- Apenas membros com função "funcionário" podem ser alocados
- Membros não podem estar em mais de 3 projetos simultaneamente (exceto encerrados/cancelados)

## 🛠️ Tecnologias

- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security
- ModelMapper

Windows
## 📦 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/sistema-portfolio.git
```
2. Entre no projeto portfolio:
```
cd portfolio
```

3. Configure o banco de dados PostgreSQL em `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

4. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

## 🔍 Exemplos de Requisições

### Endpoints de Projetos

#### 1. Listar Projetos
```http
GET /projeto/projetos
```

#### 2. Criar Projeto
```http
POST /projeto/salvar
{
  "nome": "Projeto Exemplo",
  "descricao": "Descrição do projeto",
  "dataInicio": "2025-09-01",
  "dataFimPrevisto": "2025-12-01",
  "orcamento": 95000,
  "status": "ANALISE",
  "membrosIds": [2, 3]
}
```

#### 3. Atualizar Projeto
```http
PUT /projeto/atualizar/{id}
{
  "nome": "Projeto Atualizado",
  "descricao": "Nova descrição",
  "dataInicio": "2025-09-01",
  "dataFimPrevisto": "2025-12-01",
  "orcamento": 95000,
  "status": "ANALISE",
  "gerenteId": 1,
  "membrosIds": [2, 3]
}
```

#### 4. Atualizar Status do Projeto
```http
PUT /projeto/status/{id}
{
  "novoStatus": "ANALISE_REALIZADA"
}
```

#### 5. Deletar Projeto
```http
DELETE /projeto/deletar/{id}
```

### Endpoints de Membros

#### 1. Listar Membros
```http
GET /membro/membros
```

#### 2. Criar Membro
```http
POST /membro/salvar
{
  "nome": "João da Silva",
  "funcao": "funcionário"
}
```

## 🔒 Segurança

- Autenticação básica implementada com Spring Security
- Usuário padrão: Username/P@ssw0rd
- Endpoints protegidos por autenticação

## 📝 Licença

Este projeto está sob a licença MIT.
