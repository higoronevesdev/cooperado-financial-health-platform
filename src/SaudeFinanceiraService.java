public class SaudeFinanceiraService {

    public double calcularCapacidadePoupanca(Cooperado c) {
        return c.rendaMensal - c.gastoMensal;
    }

    public double calcularPercentualMeta(Cooperado c) {
        return (c.saldoAtual / c.metaFinanceira) * 100;
    }

    public double calcularPercentualGasto(Cooperado c) {
        return (c.gastoMensal / c.rendaMensal) * 100;
    }

    public double calcularMesesParaMeta(Cooperado c) {
        double poupanca = calcularCapacidadePoupanca(c);
        return (c.metaFinanceira - c.saldoAtual) / poupanca;
    }

    public boolean reservaAdequada(Cooperado c) {
        return c.reservaEmergencia >= (c.gastoMensal * 3);
    }

    public String classificarSaude(Cooperado c) {
        double percentualGasto = calcularPercentualGasto(c);
        boolean reservaOk = reservaAdequada(c);

        if (!c.possuiDividas && percentualGasto <= 70 && reservaOk) {
            return "ÓTIMA ✓";
        } else if (!c.possuiDividas && percentualGasto <= 80) {
            return "BOA";
        } else if (percentualGasto <= 90) {
            return "ATENÇÃO ⚠";
        } else {
            return "CRÍTICA ✗";
        }
    }

    public int calcularScore(Cooperado c) {
        int score = 100;

        if (c.possuiDividas) {
            score -= 20;
        }

        double percentualGasto = calcularPercentualGasto(c);
        if (percentualGasto > 90) {
            score -= 30;
        } else if (percentualGasto > 80) {
            score -= 20;
        } else if (percentualGasto > 70) {
            score -= 10;
        }

        if (reservaAdequada(c)) {
            score += 10;
        }

        double percentualMeta = calcularPercentualMeta(c);
        if (percentualMeta >= 50) {
            score += 10;
        }

        return score;
    }

    public String classificarScore(int score) {
        if (score >= 80) {
            return "EXCELENTE";
        } else if (score >= 60) {
            return "BOM";
        } else if (score >= 40) {
            return "REGULAR";
        } else {
            return "BAIXO";
        }
    }

    public double calcularLimiteCredito(Cooperado c) {
        int score = calcularScore(c);
        double fator;

        if (score >= 80) {
            fator = 1.5;
        } else if (score >= 60) {
            fator = 0.8;
        } else {
            fator = 0.0;
        }

        return c.rendaMensal * fator;
    }

    public void analisarCredito(Cooperado c, double valorSolicitado) {
        int score = calcularScore(c);
        double limiteAprovado = calcularLimiteCredito(c);
        String statusCredito;
        String motivo;

        if (score >= 80) {
            statusCredito = "APROVADO ✓";
            motivo = "Score excelente — crédito liberado automaticamente.";
        } else if (score >= 60) {
            statusCredito = "ANÁLISE MANUAL ⚠";
            motivo = "Score intermediário — requer avaliação do gerente.";
        } else {
            statusCredito = "NEGADO ✗";
            motivo = "Score baixo — risco elevado para concessão de crédito.";
        }

        System.out.println("\n===== ANÁLISE DE CRÉDITO =====");
        System.out.println("Cooperado:         " + c.nome);
        System.out.println("Score:             " + score + " / 100");
        System.out.println("Valor solicitado:  R$ " + String.format("%.2f", valorSolicitado));
        System.out.println("Limite aprovado:   R$ " + String.format("%.2f", limiteAprovado));
        System.out.println("Status:            " + statusCredito);
        System.out.println("Motivo:            " + motivo);

        if (valorSolicitado > limiteAprovado) {
            System.out.println("\nAtenção: valor solicitado acima do limite aprovado.");
            System.out.println("Valor máximo disponível: R$ " + String.format("%.2f", limiteAprovado));
        }
    }

    public void exibirRelatorio(Cooperado c) {
        double poupanca = calcularCapacidadePoupanca(c);
        double percentualMeta = calcularPercentualMeta(c);
        double percentualGasto = calcularPercentualGasto(c);
        double mesesParaMeta = calcularMesesParaMeta(c);
        boolean reservaOk = reservaAdequada(c);
        String classificacao = classificarSaude(c);
        int score = calcularScore(c);
        String classificacaoScore = classificarScore(score);

        System.out.println("\n===== RELATÓRIO DO COOPERADO =====");
        System.out.println("Nome:               " + c.nome);
        System.out.println("Idade:              " + c.idade + " anos");
        System.out.println("Renda Mensal:       R$ " + c.rendaMensal);
        System.out.println("Saldo Atual:        R$ " + c.saldoAtual);
        System.out.println("Gasto Mensal:       R$ " + c.gastoMensal);
        System.out.println("Reserva Emergência: R$ " + c.reservaEmergencia);
        System.out.println("Meta Financeira:    R$ " + c.metaFinanceira);
        System.out.println("Possui dívidas:     " + (c.possuiDividas ? "Sim" : "Não"));

        System.out.println("\n===== DIAGNÓSTICO FINANCEIRO =====");
        System.out.println("Capacidade de Poupança:   R$ " + String.format("%.2f", poupanca));
        System.out.println("Comprometimento da Renda: " + String.format("%.1f", percentualGasto) + "%");
        System.out.println("Meta atingida:            " + String.format("%.1f", percentualMeta) + "%");
        System.out.println("Meses para a meta:        " + String.format("%.0f", mesesParaMeta) + " meses");
        System.out.println("Reserva de emergência OK: " + (reservaOk ? "Sim" : "Não"));
        System.out.println("Saúde Financeira:         " + classificacao);
        System.out.println("Score Financeiro:         " + score + " / 100");
        System.out.println("Classificação do Score:   " + classificacaoScore);

        System.out.println("\n===== RECOMENDAÇÕES SICOOB =====");
        gerarRecomendacoes(c, reservaOk, percentualGasto, percentualMeta);
    }

    public void gerarRecomendacoes(Cooperado c, boolean reservaOk,
                                   double percentualGasto, double percentualMeta) {
        if (c.possuiDividas) {
            System.out.println("• Priorize quitar suas dívidas com o crédito cooperativo Sicoob.");
        }
        if (!reservaOk) {
            System.out.println("• Sua reserva está abaixo de 3 meses de gastos. Considere a Poupança Sicoob.");
        }
        if (percentualGasto > 80) {
            System.out.println("• Seus gastos comprometem mais de 80% da renda. Revise suas despesas.");
        }
        if (percentualMeta < 50) {
            System.out.println("• Você está abaixo de 50% da meta. Considere o Investimento Sicoob.");
        }
        if (!c.possuiDividas && reservaOk && percentualGasto <= 70) {
            System.out.println("• Parabéns! Explore os fundos de investimento Sicoob.");
        }
    }
}