public class Cooperado {

    String nome;
    int idade;
    double rendaMensal;
    double saldoAtual;
    double gastoMensal;
    double metaFinanceira;
    double reservaEmergencia;
    boolean possuiDividas;

    public Cooperado(String nome, int idade, double rendaMensal, double saldoAtual,
                     double gastoMensal, double metaFinanceira,
                     double reservaEmergencia, boolean possuiDividas) {
        this.nome = nome;
        this.idade = idade;
        this.rendaMensal = rendaMensal;
        this.saldoAtual = saldoAtual;
        this.gastoMensal = gastoMensal;
        this.metaFinanceira = metaFinanceira;
        this.reservaEmergencia = reservaEmergencia;
        this.possuiDividas = possuiDividas;
    }
}