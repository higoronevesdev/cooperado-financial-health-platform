import java.util.ArrayList;

public class RelatorioAdminService {

    SaudeFinanceiraService saudeService = new SaudeFinanceiraService();

    public void exibirRelatorioGeral(ArrayList<Cooperado> lista) {

        if (lista.isEmpty()) {
            System.out.println("\nNenhum cooperado cadastrado.");
            return;
        }

        int totalCooperados = lista.size();
        int totalOtima = 0;
        int totalBoa = 0;
        int totalAtencao = 0;
        int totalCritica = 0;
        int somaScores = 0;
        Cooperado melhorScore = lista.get(0);
        Cooperado piorScore = lista.get(0);

        for (Cooperado c : lista) {
            String saude = saudeService.classificarSaude(c);
            int score = saudeService.calcularScore(c);

            somaScores += score;

            if (score > saudeService.calcularScore(melhorScore)) {
                melhorScore = c;
            }
            if (score < saudeService.calcularScore(piorScore)) {
                piorScore = c;
            }

            if (saude.contains("ÓTIMA")) totalOtima++;
            else if (saude.contains("BOA")) totalBoa++;
            else if (saude.contains("ATENÇÃO")) totalAtencao++;
            else if (saude.contains("CRÍTICA")) totalCritica++;
        }

        double mediaScore = (double) somaScores / totalCooperados;

        System.out.println("\n===== RELATÓRIO GERENCIAL SICOOB =====");
        System.out.println("Total de cooperados:      " + totalCooperados);
        System.out.println("Média de score:           " + String.format("%.1f", mediaScore) + " / 100");

        System.out.println("\n--- Distribuição por Saúde Financeira ---");
        System.out.println("Saúde ÓTIMA:    " + totalOtima + " cooperados");
        System.out.println("Saúde BOA:      " + totalBoa + " cooperados");
        System.out.println("Saúde ATENÇÃO:  " + totalAtencao + " cooperados");
        System.out.println("Saúde CRÍTICA:  " + totalCritica + " cooperados");

        System.out.println("\n--- Destaques da Carteira ---");
        System.out.println("Melhor score:  " + melhorScore.nome +
                " — " + saudeService.calcularScore(melhorScore) + " pts");
        System.out.println("Pior score:    " + piorScore.nome +
                " — " + saudeService.calcularScore(piorScore) + " pts");

        System.out.println("\n--- Cooperados em Situação Crítica ---");
        boolean algumCritico = false;
        for (Cooperado c : lista) {
            if (saudeService.classificarSaude(c).contains("CRÍTICA")) {
                System.out.println("• " + c.nome + " — Score: " +
                        saudeService.calcularScore(c) + " | Renda: R$ " + c.rendaMensal);
                algumCritico = true;
            }
        }
        if (!algumCritico) {
            System.out.println("Nenhum cooperado em situação crítica.");
        }
    }
}