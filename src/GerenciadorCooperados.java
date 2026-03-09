import java.util.ArrayList;

public class GerenciadorCooperados {

    ArrayList<Cooperado> listaCooperados = new ArrayList<>();

    public void adicionarCooperado(Cooperado cooperado) {
        listaCooperados.add(cooperado);
    }

    public void listarCooperados() {
        if (listaCooperados.isEmpty()) {
            System.out.println("\nNenhum cooperado cadastrado.");
            return;
        }

        System.out.println("\n===== COOPERADOS CADASTRADOS =====");
        for (Cooperado c : listaCooperados) {
            System.out.println("- " + c.nome + " | Idade: " + c.idade + " | Renda: R$ " + c.rendaMensal);
        }
    }
}