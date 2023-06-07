import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VeiculoUtil {
    public static List<Veiculo> veiculosRegistrados = new ArrayList<>();
    public static List<Locacao> veiculosLocados = new ArrayList<>();
    public static final String caminhoArquivo = "veiculos.obj";

    public static Scanner entrada = new Scanner(System.in);

    public static void menu() {
        System.out.println("----------------- MENU -----------------");
        System.out.println("A. Cadastro de Veículos.");
        System.out.println("B. Alocar Veículo.");
        System.out.println("C. Registro de Entrega de Veículo.");
        System.out.println("D. Consulta de Veículos Cadastrados. (Por Modelo)");
        System.out.println("E. Consulta de Veículos Cadastrados. (Por Cor Predominante)");
        System.out.println("F. Consulta de Veículos Cadastrados. (Por Faixa de Quilometragem.)");
        System.out.println("G. Listagem de Veículos Locados.");
        System.out.println("H. Listagem de Veículos Não Locados.");
        System.out.println("I. Encerrar Aplicação.");
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

    public static boolean veiculoLocado(String placa) {
        for (Locacao locado : veiculosLocados) {
            if (locado.getVeiculo().getPlaca().equals(placa)) {
                System.out.println("Veículo já Locado.");
                return true;
            }
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
        } while (!placaExiste(placa) || veiculoLocado(placa));

        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equals(placa)) {
                Locacao alocarVeiculo = new Locacao(veiculo, nomeCliente);
                veiculosLocados.add(alocarVeiculo);
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
        for (int i = 0; i < veiculosLocados.size(); i++) {
            if (veiculosLocados.get(i).getVeiculo().getPlaca().equals(placa)) {
                veiculoAux = veiculosLocados.get(i).getVeiculo();
                veiculosLocados.remove(i);
            }
        }

        veiculoAux.setQuilometragem(veiculoAux.getQuilometragem() + quimoletrosRodados);
    }

    /*
     * public static void consultaPorAtributo(String atributo) {
     * System.out.println("CONSULTA POR " + atributo.toUpperCase());
     * System.out.print("Informe " + atributo.toLowerCase() + ": ");
     * String valor = entrada.nextLine();
     * System.out.println("Resultados para " + atributo + " -> " + valor + ": ");
     * for (Veiculo veiculo : veiculosRegistrados) {
     * if (atributo.equals("Modelo") && veiculo.getModelo().equals(valor)) {
     * System.out.println(veiculo);
     * } else if (atributo.equals("Cor") && veiculo.getCor().equals(valor)) {
     * System.out.println(veiculo);
     * }
     * }
     * }
     */

    public static void consultaPorModelo() {
        System.out.println("CONSULTA POR MODELO");
        System.out.print("Informe o modelo: ");
        String modelo = entrada.nextLine();
        System.out.println("Resultados para modelo -> " + modelo + ": ");
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getModelo().equalsIgnoreCase(modelo)) {
                System.out.println(veiculo);
            }
        }
    }

    public static void consultaPorCor() {
        System.out.println("CONSULTA POR COR");
        System.out.print("Informe a cor: ");
        String cor = entrada.nextLine();
        System.out.println("Resultados para cor -> " + cor + ": ");
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getCor().equalsIgnoreCase(cor)) {
                System.out.println(veiculo);
            }
        }
    }

    public static void consultaPorQuilometragem() {
        System.out.println("CONSULTA POR QUILOMETRAGEM");
        System.out.print("Informe km mínimo: ");
        float kmMinimo = entrada.nextFloat();
        System.out.print("Informe km máxima: ");
        float kmMaximo = entrada.nextFloat();
        System.out.println("Resultados para quilometragem entre " + kmMinimo + "KM" + " e " + kmMaximo + "KM: ");
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getQuilometragem() >= kmMinimo && veiculo.getQuilometragem() <= kmMaximo) {
                System.out.println(veiculo);
            }
        }
    }

    public static void veiculosLocados() {
        System.out.println(veiculosLocados);
    }

    public static void veiculosNaoLocados() {
        List<Veiculo> veiculosNaoLocados = new ArrayList<>();
        for (Veiculo veiculo : veiculosRegistrados) {
            boolean encontrado = false;
            for (Locacao locacao : veiculosLocados) {
                if (locacao.getVeiculo().equals(veiculo)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                veiculosNaoLocados.add(veiculo);
            }
        }
        System.out.println(veiculosNaoLocados);
    }

    public static void salvarVeiculos() {

        System.out.println("SALVANDO VEÍCULOS ...");

        try {
            // fluxo de escrita em arquivo
            FileOutputStream fluxoArquivo = new FileOutputStream(caminhoArquivo);
            // fluxo de escrita de objetos baseado em fluxo de escrita de arquivo
            ObjectOutputStream fluxoObjetos = new ObjectOutputStream(fluxoArquivo);

            Dados data = new Dados(veiculosRegistrados, veiculosLocados);
            // escrita de lista de funcionários
            fluxoObjetos.writeObject(data);

            // fechamento de fluxos
            fluxoObjetos.close();
            fluxoArquivo.close();

            System.out.println("Veículos salvos!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar veículos: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();

    }

    public static void recuperarVeiculos() {
        File arquivo = new File(caminhoArquivo);

        if (arquivo.exists()) {
            try {
                FileInputStream fluxoArquivo = new FileInputStream(caminhoArquivo);
                ObjectInputStream fluxoObjetos = new ObjectInputStream(fluxoArquivo);

                Dados data = (Dados) fluxoObjetos.readObject();

                veiculosRegistrados = data.getVeiculosRegistrados();
                veiculosLocados = data.getVeiculosLocados();

                fluxoObjetos.close();
                fluxoArquivo.close();

                System.out.println("Veículos recuperados!");
            } catch (IOException e) {
                System.out.println("Erro ao ler veículos: " + e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Erro ao ler veículos: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println();
        } else {
            System.out.println("Não há veículos a serem recuperados!");
        }
    }

    public static void main(String[] args) throws Exception {
        recuperarVeiculos();
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
                    novaAlocacao();
                    break;

                case 'c':
                    entregaVeiculo();
                    break;

                case 'd':
                    consultaPorModelo();
                    break;

                case 'e':
                    consultaPorCor();
                    break;

                case 'f':
                    consultaPorQuilometragem();
                    break;

                case 'g':
                    System.out.println(veiculosLocados);
                    break;

                case 'h':
                    veiculosNaoLocados();
                    break;

                default:
                    break;
            }
        } while (option != 'i');
        salvarVeiculos();
    }
}
