import java.util.Scanner;

public class MenuService {

    Scanner scanner = new Scanner(System.in);
    CadastroService cadastroService = new CadastroService();
    SaudeFinanceiraService saudeService = new SaudeFinanceiraService();
    GerenciadorCooperados gerenciador = new GerenciadorCooperados();
    RelatorioAdminService relatorioAdmin = new RelatorioAdminService();

    private final String SENHA_ADMIN = "sicoob123";

    public void exibirMenuPrincipal() {
        exibirBoasVindas();
        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║   PLATAFORMA DE SAÚDE FINANCEIRA      ║");
            System.out.println("║        SICOOB — COOPERATIVA            ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println("  1 → Sou cooperado");
            System.out.println("  2 → Acesso administrativo");
            System.out.println("  0 → Sair");
            System.out.print("\n  Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    menuCooperado();
                    break;
                case 2:
                    menuAdmin();
                    break;
                case 0:
                    exibirDespedida();
                    break;
                default:
                    System.out.println("\n  ⚠ Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirBoasVindas() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║     Bem-vindo ao Sicoob               ║");
        System.out.println("║     Sua cooperativa financeira        ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("\n  Aqui você acompanha sua saúde financeira,");
        System.out.println("  simula metas e acessa crédito cooperativo.");
        System.out.println("\n  Vamos começar?\n");
    }

    private void exibirDespedida() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║   Obrigado por usar o Sicoob!         ║");
        System.out.println("║   Até a próxima.                      ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
    }

    private void menuCooperado() {
        System.out.println("\n───────────────────────────────────────");
        System.out.println("  ÁREA DO COOPERADO");
        System.out.println("───────────────────────────────────────");
        System.out.print("  Digite seu nome: ");
        String nome = scanner.nextLine();

        Cooperado encontrado = buscarCooperado(nome);

        if (encontrado != null) {
            System.out.println("\n  Olá, " + encontrado.nome + "! Bem-vindo de volta.");
            System.out.println("\n  O que você deseja fazer hoje?");
            System.out.println("  1 → Ver minha análise financeira");
            System.out.println("  2 → Solicitar crédito");
            System.out.print("\n  Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {
                System.out.println("\n  Carregando sua análise...");
                System.out.println("───────────────────────────────────────");
                saudeService.exibirRelatorio(encontrado);
                System.out.println("───────────────────────────────────────");
            } else if (opcao == 2) {
                System.out.println("\n  Informe o valor que deseja solicitar.");
                System.out.print("  Valor: R$ ");
                double valor = Double.parseDouble(scanner.nextLine());
                System.out.println("\n  Analisando seu perfil de crédito...");
                System.out.println("───────────────────────────────────────");
                saudeService.analisarCredito(encontrado, valor);
                System.out.println("───────────────────────────────────────");
            } else {
                System.out.println("\n  ⚠ Opção inválida.");
            }
        } else {
            System.out.println("\n  ✗ Cooperado não encontrado.");
            System.out.println("  Procure um atendente para realizar seu cadastro.");
        }
    }

    private void menuAdmin() {
        System.out.println("\n───────────────────────────────────────");
        System.out.println("  ACESSO ADMINISTRATIVO");
        System.out.println("───────────────────────────────────────");
        System.out.print("  Senha: ");
        String senha = scanner.nextLine();

        if (!senha.equals(SENHA_ADMIN)) {
            System.out.println("\n  ✗ Senha incorreta. Acesso negado.");
            return;
        }

        System.out.println("\n  ✓ Acesso autorizado. Bem-vindo, administrador.");

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║        PAINEL ADMINISTRATIVO          ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println("  1 → Cadastrar cooperado");
            System.out.println("  2 → Analisar cooperado");
            System.out.println("  3 → Listar todos os cooperados");
            System.out.println("  4 → Analisar crédito de cooperado");
            System.out.println("  5 → Relatório gerencial da carteira");
            System.out.println("  0 → Voltar ao menu principal");
            System.out.print("\n  Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\n───────────────────────────────────────");
                    Cooperado cooperado = cadastroService.cadastrarCooperado();
                    gerenciador.adicionarCooperado(cooperado);
                    System.out.println("\n  ✓ Cooperado " + cooperado.nome + " cadastrado com sucesso!");
                    System.out.println("───────────────────────────────────────");
                    break;
                case 2:
                    gerenciador.listarCooperados();
                    if (!gerenciador.listaCooperados.isEmpty()) {
                        System.out.print("\n  Nome do cooperado: ");
                        String nome = scanner.nextLine();
                        Cooperado encontrado = buscarCooperado(nome);
                        if (encontrado != null) {
                            System.out.println("\n───────────────────────────────────────");
                            saudeService.exibirRelatorio(encontrado);
                            System.out.println("───────────────────────────────────────");
                        } else {
                            System.out.println("\n  ✗ Cooperado não encontrado.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n───────────────────────────────────────");
                    gerenciador.listarCooperados();
                    System.out.println("───────────────────────────────────────");
                    break;
                case 4:
                    gerenciador.listarCooperados();
                    if (!gerenciador.listaCooperados.isEmpty()) {
                        System.out.print("\n  Nome do cooperado: ");
                        String nome = scanner.nextLine();
                        Cooperado encontrado = buscarCooperado(nome);
                        if (encontrado != null) {
                            System.out.print("  Valor solicitado: R$ ");
                            double valor = Double.parseDouble(scanner.nextLine());
                            System.out.println("\n───────────────────────────────────────");
                            saudeService.analisarCredito(encontrado, valor);
                            System.out.println("───────────────────────────────────────");
                        } else {
                            System.out.println("\n  ✗ Cooperado não encontrado.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n───────────────────────────────────────");
                    relatorioAdmin.exibirRelatorioGeral(gerenciador.listaCooperados);
                    System.out.println("───────────────────────────────────────");
                    break;
                case 0:
                    System.out.println("\n  Saindo do painel administrativo...");
                    break;
                default:
                    System.out.println("\n  ⚠ Opção inválida. Tente novamente.");
            }
        }
    }

    private Cooperado buscarCooperado(String nome) {
        for (Cooperado c : gerenciador.listaCooperados) {
            if (c.nome.equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }
}