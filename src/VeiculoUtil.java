import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VeiculoUtil {
    public static List<Veiculo> veiculosRegistrados = new ArrayList<>();
    public static List<Alocacao> veiculosAlocados = new ArrayList<>();

    public static Scanner entrada = new Scanner(System.in);

    public static boolean placaExiste(String placa) {
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa))
                return true;
        }

        return false;
    }

    public static boolean veiculoAlocado(Veiculo veiculo) {
        for (Alocacao alocado : veiculosAlocados) {
            if (alocado.getVeiculo() == veiculo)
                return true;
        }

        return false;
    }

    public static void novoVeiculo() {
        String placa;

        do {
            System.out.println("Insira a placa");
            placa = entrada.nextLine();

        } while (placaExiste(placa));

        System.out.println("Insira o modelo");
        String modelo = entrada.nextLine();

        System.out.println("Insira o ano");
        int ano = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Insira a cor");
        String cor = entrada.nextLine();

        System.out.println("Insira a quilometragem");
        long quilometragem = entrada.nextLong();

        Veiculo veiculo = new Veiculo(placa, modelo, ano, cor, quilometragem);
        veiculosRegistrados.add(veiculo);
    }

    public static void novaAlocacao() {
        System.out.println("Insira o nome do cliente");
        String nomeCliente = entrada.nextLine();

        String placa;
        do {
            System.out.println("insira a placa do veiculo");
            placa = entrada.nextLine();
            
        } while (!placaExiste(placa));

        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa)) {
                Alocacao alocarVeiculo = new Alocacao(veiculo, nomeCliente);
                veiculosAlocados.add(alocarVeiculo);
            }  
        }

    }

    public static void main(String[] args) throws Exception {
        char option;

        do {
            System.out.println("A. Cadastro de Veículos.");
            System.out.println("B. Alocação de Veículos.");
            System.out.println("C. Consulta de Veículos.");
            System.out.println("D. ");
            System.out.println("E. ");
            option = entrada.next().toLowerCase().charAt(0);

            entrada.nextLine();
            
            switch (option) {
                case 'a':
                    novoVeiculo();
                    break;

                case 'b':

                    break;

                case 'c':

                    break;

                case 'd':

                    break;

                default:
                    break;
            }
        } while (option != 'e');
    }
}
