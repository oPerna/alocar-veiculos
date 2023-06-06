import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VeiculoUtil {
    public static List<Veiculo> veiculosRegistrados = new ArrayList<>();
    public static List<Alocacao> veiculosAlocados = new ArrayList<>();

    public static Scanner entrada = new Scanner(System.in);

    public static void menu() {
        System.out.println("----------------- MENU -----------------");
        System.out.println("A. Cadastro de Veículos.");
        System.out.println("B. Consulta de Veículos");
        System.out.println("C. ");
        System.out.println("D. ");
        System.out.println("E. ");
        System.out.println("----------------------------------------");
        System.out.print("Escolha uma opção: ");
    }

    public static boolean placaExiste(String placa) {
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa))
                return true;
        }

        return false;
    }

    public static boolean veiculoAlocado(String placa) {
        Veiculo veiculoAux = null;
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa)) {
                veiculoAux = veiculo;
            }
        }
        for (Alocacao alocado : veiculosAlocados) {
            if (alocado.getVeiculo() == veiculoAux)
                return true;
        }
        return false;
    }

    public static void novoVeiculo() {
        String placa;

        do {
            System.out.print("Insira a placa: ");
            placa = entrada.nextLine();

        } while (placaExiste(placa));

        System.out.print("Insira o modelo: ");
        String modelo = entrada.nextLine();

        System.out.print("Insira o ano: ");
        int ano = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Insira a cor: ");
        String cor = entrada.nextLine();

        System.out.print("Insira a quilometragem: ");
        long quilometragem = entrada.nextLong();

        Veiculo veiculo = new Veiculo(placa, modelo, ano, cor, quilometragem);
        veiculosRegistrados.add(veiculo);
    }

    public static void novaAlocacao() {
        System.out.print("Insira o nome do cliente: ");
        String nomeCliente = entrada.nextLine();

        String placa;
        do {
            System.out.print("insira a placa do veiculo: ");
            placa = entrada.nextLine();
        } while (!placaExiste(placa) && !veiculoAlocado(placa));

        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa)) {
                Alocacao alocarVeiculo = new Alocacao(veiculo, nomeCliente);
                veiculosAlocados.add(alocarVeiculo);
            }  
        }
    }

    public static void entregaVeiculo() {
        String placa;
        do {
            System.out.print("Insira a placa do veiculo: ");
            placa = entrada.nextLine();
        } while (!placaExiste(placa));
        
        System.out.println("Indique quantos km foram rodados: ");
        long quimoletrosRodados = entrada.nextInt();

        Veiculo veiculoAux = null;
        for (Alocacao alocado : veiculosAlocados) {
            if (alocado.getVeiculo().getPlaca().equals(placa)) {
                veiculoAux = alocado.getVeiculo();
            }
        }
        
        veiculoAux.setQuilometragem(veiculoAux.getQuilometragem() + quimoletrosRodados);
    }

    public static void main(String[] args) throws Exception {
        char option;

        do {
            menu();
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
