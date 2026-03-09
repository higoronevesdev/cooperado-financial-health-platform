import java.util.Scanner;

public class CadastroService {

    Scanner scanner = new Scanner(System.in);

    public Cooperado cadastrarCooperado() {

        System.out.println("\n===== CADASTRO DO COOPERADO =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Renda Mensal: R$ ");
        double rendaMensal = Double.parseDouble(scanner.nextLine());

        System.out.print("Saldo Atual: R$ ");
        double saldoAtual = Double.parseDouble(scanner.nextLine());

        System.out.print("Gasto Mensal: R$ ");
        double gastoMensal = Double.parseDouble(scanner.nextLine());

        System.out.print("Meta Financeira: R$ ");
        double metaFinanceira = Double.parseDouble(scanner.nextLine());

        System.out.print("Reserva de Emergência: R$ ");
        double reservaEmergencia = Double.parseDouble(scanner.nextLine());

        System.out.print("Possui dívidas? (s/n): ");
        String dividas = scanner.nextLine();
        boolean possuiDividas = dividas.equalsIgnoreCase("s");

        return new Cooperado(nome, idade, rendaMensal, saldoAtual,
                gastoMensal, metaFinanceira,
                reservaEmergencia, possuiDividas);
    }
}