# 🌿 Plataforma de Saúde Financeira — Sicoob

> Sistema web desenvolvido em **Java com Spring Boot** para análise de saúde financeira de cooperados, com dashboard gerencial, ranking, score automático e persistência em banco de dados.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Demonstração](#-demonstração)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura](#-arquitetura)
- [Tecnologias](#-tecnologias)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
- [Fluxo da Aplicação](#-fluxo-da-aplicação)
- [Algoritmo de Score](#-algoritmo-de-score)
- [Roadmap](#-roadmap)
- [Sobre o Autor](#-sobre-o-autor)

---

## 💡 Sobre o Projeto

A **Plataforma de Saúde Financeira** é um sistema web completo que simula uma ferramenta real de gestão financeira para cooperativas de crédito. Inspirada no modelo cooperativo do **Sicoob**, a plataforma permite que cooperados façam sua análise financeira e que gestores acompanhem a carteira através de um dashboard executivo.

O projeto vai além de um CRUD simples — ele implementa um **motor de score financeiro**, **gráficos interativos**, **ranking de cooperados** e **análise automática de crédito**, demonstrando na prática como tecnologia pode apoiar a saúde financeira das pessoas.

---

## 🖥️ Demonstração

### Página Inicial
Interface inspirada na identidade visual oficial do Sicoob — header branco, verde `#00703C`, tipografia Source Sans 3.

### Formulário em 3 Passos
Coleta de dados do cooperado com **progress bar interativa**, dividida em:
1. **Dados Pessoais** — nome e idade
2. **Situação Financeira** — renda, saldo, gastos e dívidas
3. **Metas e Reservas** — meta financeira e reserva de emergência

### Resultado da Análise
- Score circular animado (0–100)
- Gráfico de rosca com distribuição da renda
- Barras de progresso para gastos e meta
- Análise de crédito automática
- Recomendações personalizadas

### Dashboard Gerencial
- 4 cards de métricas (total, média, melhor e pior score)
- Gráfico de pizza — distribuição por classificação
- Gráfico de barras — faixas de score
- Cards de contagem por categoria

### Ranking de Cooperados
- Medalhas 🥇🥈🥉 para o top 3
- Badge colorido por classificação
- Barra visual de score por cooperado
- Busca por nome com resultado em destaque

---

## ✅ Funcionalidades

### Para o Cooperado
- [x] Formulário intuitivo em 3 etapas com progress bar
- [x] Score financeiro calculado automaticamente (0–100)
- [x] Classificação da saúde financeira: **ÓTIMA / BOA / ATENÇÃO / CRÍTICA**
- [x] Gráfico de rosca com distribuição de renda, gastos e meta
- [x] Análise de crédito com limite calculado
- [x] Recomendações personalizadas baseadas no perfil

### Para o Administrador
- [x] Acesso protegido por senha
- [x] Ranking de cooperados ordenado por score
- [x] Busca de cooperado por nome
- [x] Dashboard com gráficos interativos (Chart.js)
- [x] Relatório gerencial com distribuição por classificação e faixas de score

### Sistema
- [x] Persistência em banco H2 (dados salvos entre reinicializações)
- [x] Identidade visual Sicoob (cores, fontes, layout)
- [x] Animações e transições CSS
- [x] Responsive design

---

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────┐
│                   BROWSER / THYMELEAF                │
├─────────────────────────────────────────────────────┤
│              PlataformaController.java               │
│         (Recebe requisições, coordena fluxo)         │
├──────────────────────┬──────────────────────────────┤
│  SaudeFinanceiraService │   CooperadoRepository      │
│  (Regras de negócio) │   (Acesso ao banco H2)        │
├──────────────────────┴──────────────────────────────┤
│           Cooperado.java / CooperadoRankingDTO       │
│                    (Entidades / DTOs)                │
├─────────────────────────────────────────────────────┤
│              H2 Database (sicoob-db)                 │
└─────────────────────────────────────────────────────┘
```

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.1 | Framework web |
| Spring Data JPA | — | Persistência |
| Thymeleaf | — | Template engine |
| H2 Database | — | Banco embarcado |
| Chart.js | CDN | Gráficos interativos |
| Maven | — | Gerenciador de dependências |
| CSS3 | — | Identidade visual Sicoob |
| Google Fonts | — | Source Sans 3 |

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/sicoob/saude/plataforma/
│   │   ├── PlataformaApplication.java       # Entry point
│   │   ├── PlataformaController.java        # Rotas e endpoints
│   │   ├── Cooperado.java                   # Entidade JPA
│   │   ├── CooperadoRepository.java         # Interface JPA Repository
│   │   ├── CooperadoRankingDTO.java         # DTO para ranking
│   │   └── SaudeFinanceiraService.java      # Motor de score e análise
│   └── resources/
│       ├── static/
│       │   └── style.css                    # Identidade visual Sicoob
│       ├── templates/
│       │   ├── index.html                   # Página inicial
│       │   ├── cooperado.html               # Formulário 3 passos
│       │   ├── resultado.html               # Resultado com gráfico
│       │   ├── admin.html                   # Login administrativo
│       │   ├── painel-admin.html            # Menu do admin
│       │   ├── admin-cooperados.html        # Ranking de cooperados
│       │   └── admin-relatorio.html         # Dashboard gerencial
│       └── application.yml                  # Configurações Spring
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- IntelliJ IDEA (recomendado)

### Passos

```bash
# Clone o repositório
git clone https://github.com/higoronevesdev/cooperado-financial-health-platform.git

# Entre na pasta
cd cooperado-financial-health-platform

# Execute com Maven
./mvnw spring-boot:run
```

Acesse: **http://localhost:8080**

### Acesso Administrativo
- URL: `http://localhost:8080/admin`
- Senha: `sicoob123`

### Console H2 (banco de dados)
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./sicoob-db`
- Usuário: `sa` | Senha: *(vazio)*

---

## 🔄 Fluxo da Aplicação

```
Cooperado                          Administrador
    │                                    │
    ▼                                    ▼
Acessa /cooperado              Acessa /admin (senha)
    │                                    │
    ▼                                    ▼
Preenche formulário            Painel administrativo
(3 etapas com progress bar)         │         │
    │                          Ranking    Dashboard
    ▼                        cooperados   gerencial
POST /analisar                  (score    (gráficos
    │                          + badges)   Chart.js)
    ▼
Salvo no H2
    │
    ▼
resultado.html
(score + gráfico + recomendações)
```

---

## 📊 Algoritmo de Score

O score é calculado de 0 a 100 com base nos seguintes critérios:

| Critério | Impacto |
|---|---|
| Gastos > 90% da renda | -40 pontos |
| Gastos entre 80–90% | -25 pontos |
| Gastos entre 70–80% | -15 pontos |
| Gastos entre 60–70% | -5 pontos |
| Possui dívidas ativas | -20 pontos |
| Reserva de emergência insuficiente | -15 pontos |
| Capacidade de poupança negativa | -10 pontos |
| Progresso da meta < 10% | -5 pontos |

### Classificação do Score

| Faixa | Classificação |
|---|---|
| 80–100 | EXCELENTE |
| 60–79 | BOM |
| 40–59 | REGULAR |
| 0–39 | CRÍTICO |

### Análise de Crédito Automática

| Condição | Resultado |
|---|---|
| Score ≥ 70 e sem dívidas | APROVADO — Limite: poupança × 3 |
| Score ≥ 50 | ANÁLISE NECESSÁRIA |
| Score < 50 | NEGADO |

---

## 🗺️ Roadmap

- [x] Motor de score financeiro
- [x] Formulário multi-step com progress bar
- [x] Gráfico de rosca no resultado
- [x] Dashboard gerencial com Chart.js
- [x] Ranking de cooperados com medalhas
- [x] Persistência H2
- [ ] API REST (`/api/cooperados`)
- [ ] Score automático em tempo real no formulário
- [ ] Simulador financeiro interativo
- [ ] Página de erro personalizada (404/500)
- [ ] Validações de formulário com feedback visual
- [ ] Responsividade mobile completa
- [ ] Exportação de relatório em PDF
- [ ] Autenticação com Spring Security

---

## 👨‍💻 Sobre o Autor

**Higor Oliveira**
Desenvolvedor em formação com foco em Java, Spring Boot e sistemas financeiros.

Este projeto nasceu como exercício técnico para a área de tecnologia do **Sicoob**, mas cresceu para representar uma ferramenta real de apoio à tomada de decisão financeira dentro do contexto cooperativo.

A construção desta plataforma representa o desejo de evoluir tecnicamente e contribuir com soluções que fortaleçam o ecossistema do cooperativismo financeiro.

---

<div align="center">
  <sub>Desenvolvido com dedicação para o Sicoob · 2026</sub>
</div>
