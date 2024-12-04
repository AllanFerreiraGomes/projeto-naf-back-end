📘 Projeto NAF - AgendaFácil

📋 Descrição do Projeto
O AgendaFacil NAF é uma plataforma web desenvolvida para facilitar o agendamento de atendimentos contábeis e fiscais realizados pelo NAF (Núcleo de Apoio Contábil e Fiscal)
, em parceria com o Unifeso. O sistema oferece funcionalidades modernas para otimizar a gestão de horários, melhorar a experiência dos usuários e simplificar a administração dos serviços.

🎯 Objetivos:

Permitir o agendamento online de serviços contábeis e fiscais.\

Diferenciar usuários por perfis: Aluno, Professor, População Local e Administração.\

Enviar notificações por e-mail para confirmação de agendamentos.\

Recolher e gerenciar feedbacks dos usuários.\

Garantir segurança e desempenho no acesso e manipulação dos dados.\

🛠️ Tecnologias Utilizadas


Backend: Java com Spring Boot

Banco de Dados: PostgreSQL

Gerenciamento de Dependências: Maven

Envio de E-mails: Spring Mail com integração ao Gmail

Autenticação e Segurança: Spring Security

Ferramentas de Desenvolvimento:

Insomnia (para testes de API)

Git e GitHub (para controle de versão)


⚙️ Funcionalidades


📅 Agendamento

Criar agendamentos com validações:

Dias úteis (segunda a sexta).

Horários disponíveis (08h às 16h).

Sem agendamentos duplicados.

Cancelar agendamentos, com restrições de permissão (somente quem criou ou administradores).

Notificações por e-mail para confirmação de agendamento.

👥 Gestão de Usuários


Cadastro de usuários com diferentes perfis:

Aluno e Professor: realizam atendimentos.

População Local: cria agendamentos.

Administração: gerencia usuários, horários e relatórios.

Exclusão de usuários (restrita a administradores).

⭐ Feedback


Coleta de avaliações e sugestões dos usuários.

Associado ao agendamento e ao usuário que realizou o atendimento.

📂 Estrutura do Projeto


plaintext
Copiar código
src/
├── main/
│   ├── java/com/example/projeto_naf_back/\
│   │   ├── controller/         # Controladores REST\
│   │   ├── dto/                # Objetos de transferência de dados (DTOs)\
│   │   ├── exceptions/        # Classes de exceção personalizada\
│   │   ├── model/             # Entidades (Usuário, Agendamento, Feedback)\
│   │   ├── repository/        # Interfaces de acesso ao banco de dados\
│   │   ├── service/           # Regras de negócio e lógica do sistema\
│   ├── resources/\
│       ├── application.properties  # Configurações de aplicação (DB, SMTP)\
\

🚀 Como Rodar o Projeto\

Pré-requisitos\
\

Java 17 ou superior instalado.

PostgreSQL configurado.

Maven instalado (ou use o wrapper do projeto: mvnw).

Uma conta de e-mail Gmail habilitada para envio (com senha de aplicativo configurada).

Passos
Clone o repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/projeto-naf.git

cd projeto-naf

Configure o banco de dados no arquivo application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/naf_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
Configure o envio de e-mails no application.properties:

bash
Copiar código
./mvnw spring-boot:run
Acesse a aplicação:

http://localhost:8080

📬 Endpoints Principais

Usuários
GET /usuarios - Lista todos os usuários.\
POST /usuarios - Cria um novo usuário.\
DELETE /usuarios/{id} - Exclui um usuário (admin apenas).\

Agendamentos
GET /agendamentos - Lista todos os agendamentos.\
POST /agendamentos - Cria um agendamento.\
DELETE /agendamentos/{id} - Cancela um agendamento (restrições aplicadas).\
Feedback

POST /feedback - Adiciona um feedback.\
📝 Validações Implementadas\
Agendamentos\

Horários fixos (segunda a sexta, 08h às 16h).\
Intervalos de 1 hora entre agendamentos.\
Não permitir agendamentos duplicados.\
Usuários\

Perfis diferenciados com permissões específicas.\
Apenas administradores podem excluir usuários.\
