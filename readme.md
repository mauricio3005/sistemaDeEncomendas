# Contexto
Recentemente, no meu condomínio houve uma enorme briga entre os moradores/funcionários envolvendo o sistema de encomendas.
Nesse caso, o sistema era muito antiquado, com a necessidade do uso de papel para controlar as despesas que entravam e saiam, causando um
tempo de demora extremamente longo. 
Hoje, o sistema já foi mudado para um digital, e como esperado a melhora foi de 100%, porém, quero dar meus 2 centavos de como eu poderia
fazer esse sistema da minha maneira

# Criação
Tenho como visão fazer a montagem desse projeto de uma maneira simples, porém funcional. Simbolizando o controle de acesso por parte do usuário,
fazendo o cadastro da despesa, e mantendo um controle geral utilizando concorrência, através dos locks e a manutenção dos dados com transactional

# Funcionalidades

## Perfis de usuário
- **Porteiro**: cadastra a chegada de encomendas e confirma a retirada pelo morador.
- **Morador**: consulta suas próprias encomendas (pendentes e já retiradas).

## Autenticação e autorização
- Login por usuário, com papel (PORTEIRO ou MORADOR) associado.
- Cada perfil só acessa as ações e dados permitidos ao seu papel (porteiro não vê ações de outro condomínio/unidade fora do escopo, morador só vê as próprias encomendas).

## Cadastro de encomendas
- Porteiro registra a chegada de uma encomenda vinculada a um morador/unidade, com dados como remetente, descrição e data/hora de chegada.
- Validação para evitar cadastro duplicado da mesma encomenda em registros concorrentes (dois cadastros simultâneos para o mesmo evento de chegada).

## Notificação ao morador
- Ao registrar a chegada de uma encomenda, o sistema envia um email automático ao morador destinatário avisando que há uma encomenda disponível.

## Consulta de encomendas
- Morador visualiza a lista de suas encomendas pendentes de retirada.
- Morador visualiza o histórico de encomendas já retiradas (data de chegada e de retirada).

## Retirada de encomenda
- Porteiro identifica o morador presencialmente e confirma a retirada da encomenda no sistema.
- Controle de concorrência para impedir que a mesma encomenda seja marcada como retirada mais de uma vez (ex.: duas confirmações simultâneas).

## Controle de concorrência
- Uso de locks (pessimista ou otimista, via versionamento) nas operações de cadastro e retirada de encomendas, para evitar entrada ou retirada duplicada quando há concorrência.
- Uso de `@Transactional` para garantir que cada operação (cadastro, retirada) seja atômica e consistente.

## Histórico/auditoria
- Registro de quem cadastrou a encomenda e quando.
- Registro de quem confirmou a retirada e quando.

