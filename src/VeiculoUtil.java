import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VeiculoUtil {
    private static List<Veiculo> veiculosRegistrados = new ArrayList<>();
    private static List<Locacao> veiculosLocados = new ArrayList<>();
    private static final String caminhoArquivo = "veiculos.obj";

    public static Scanner entrada = new Scanner(System.in);

    public static void menu() {
        System.out.println("\n+----------------------------------------+");
        System.out.println("|       MENU ~ LOCAÇÃO DE VEÍCULOS       |");
        System.out.println("+----------------------------------------+");
        System.out.println("| A. Cadastro de Veículos   JOÂO HENRIQUE             |");
        System.out.println("| B. Alocar Veículo                      |");
        System.out.println("| C. Registro de Entrega de Veículo      |");
        System.out.println("| D. Consulta de Veículos Cadastrados    |");
        System.out.println("| E. Listagem de Veículos Locados        |");
        System.out.println("| F. Listagem de Veículos Não Locados    |");
        System.out.println("| X. Encerrar Aplicação                  |");
        System.out.println("+----------------------------------------+");
        System.out.print("Selecione uma opção: ");
    }


    public static void menuConsulta() {
        System.out.println("+----------------------------------------+");
        System.out.println("|    CONSULTA DE VEÍCULOS CADASTRADOS    |");
        System.out.println("+----------------------------------------+");
        System.out.println("| 1. Por Modelo                          |");
        System.out.println("| 2. Por Cor Predominante                |");
        System.out.println("| 3. Por Faixa de Quilometragem          |");
        System.out.println("+----------------------------------------+");
        int subOption = 0;
        boolean opcaoValida = false;
        do {
            try {
                System.out.println("+----------------------------------------+");
                System.out.println("| Escolha o número relativo a consulta:  |");
                System.out.println("+----------------------------------------+");
                subOption = entrada.nextInt();
                opcaoValida = true;
            } catch (InputMismatchException ime) {
                System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. CONSIDERE ESCOLHER DE 1 A 3.     |");
                System.out.println("+----------------------------------------+");
                entrada.nextLine();
            }
        } while (!opcaoValida);
        entrada.nextLine();
        switch (subOption) {
            case 1:
                consultaPorModelo();
                break;

            case 2:
                consultaPorCor();
                break;

            case 3:
                consultaPorQuilometragem();
                break;
            
            default:
                System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. NUMERO DE OPÇÃO INVALIDA         |");
                System.out.println("+----------------------------------------+");
                break;
        }
    }

    public static boolean placaExiste(String placa) {
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa))
                return true;
        }

        return false;
    }

    public static boolean veiculoLocado(String placa) {
        for (Locacao locado : veiculosLocados) {
            if (locado.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
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
            if (placaExiste(placa)) {
                System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. Placa já cadastrada.             |");
                System.out.println("+----------------------------------------+");
            }
        } while (placaExiste(placa));

        try {
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
            
            System.out.println("Veículo de placa " + placa + " registrado.");
        } catch (InputMismatchException ime) {
            System.out.println("+----------------------------------------+");
            System.err.println("|ERRO. ENTRADA INVALIDA. TENTE NOVAMENTE!|");
            System.out.println("+----------------------------------------+");
            entrada.nextLine();
        }
    }

    public static void novaAlocacao() {
        System.out.print("Insira o nome do cliente: ");
        String nomeCliente = entrada.nextLine();

        String placa;
        System.out.print("Insira a placa do veiculo: ");
        placa = entrada.nextLine();
        if (placaExiste(placa)) {
            if (!veiculoLocado(placa)) {
                for (Veiculo veiculo : veiculosRegistrados) {
                    if (veiculo.getPlaca().equals(placa)) {
                        Locacao alocarVeiculo = new Locacao(veiculo, nomeCliente);
                        veiculosLocados.add(alocarVeiculo);
                    }
                }
                System.out.println("Veículo de placa " + placa + " foi locado.");
            } else {
                System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. Veículo já locado.               |");
                System.out.println("+----------------------------------------+");
            }
        } else {
            System.out.println("+----------------------------------------+");
            System.err.println("| ERRO. Veículo não existe.              |");
            System.out.println("+----------------------------------------+");                

        }
    }

    public static void entregaVeiculo() {
        String placa;
        System.out.print("Insira a placa do veiculo: ");
        placa = entrada.nextLine();

        if (placaExiste(placa)) {
            if (veiculoLocado(placa)) {
                System.out.print("Indique quantos km foram rodados: ");
                long quimoletrosRodados = entrada.nextLong();
                Veiculo veiculoAux = null;
                for (int i = 0; i < veiculosLocados.size(); i++) {
                    if (veiculosLocados.get(i).getVeiculo().getPlaca().equals(placa)) {
                        veiculoAux = veiculosLocados.get(i).getVeiculo();
                        veiculosLocados.remove(i);
                    }
                }
                veiculoAux.setQuilometragem(veiculoAux.getQuilometragem() + quimoletrosRodados);
                System.out.println("Veículo de placa " + placa + " entregue com " + quimoletrosRodados + "km rodados");
            } else {
                System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. Veículo não locado.              |");
                System.out.println("+----------------------------------------+");
            }
        } else {
            System.out.println("+----------------------------------------+");
            System.err.println("| ERRO. Veículo não existe.              |");
            System.out.println("+----------------------------------------+");
        }
    }

    public static void consultaPorModelo() {
        System.out.println("CONSULTA POR MODELO");
        System.out.print("Informe o modelo: ");
        String modelo = entrada.nextLine();
        boolean veiculoEncontrado = false;
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getModelo().equalsIgnoreCase(modelo)) {
                if (!veiculoEncontrado) {
                    System.out.println("+----------------------------------------------------------------------------------+");
                    System.out.printf("| %10s | %-12s | %-5s | %-15s | %-13s | %-10s |\n", "PLACA", "MODELO", "ANO", "COR", "QUILOMETRAGEM", "STATUS");
                    System.out.println("+------------+--------------+-------+-----------------+---------------+------------+");
                }
                if (!veiculoLocado(veiculo.getPlaca())) {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Disponível");
                } else {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Locado");
                }
                veiculoEncontrado = true;
            }
        }
        if (!veiculoEncontrado) {
            System.out.println("+------------------------------------------------+");
            System.err.printf("| ERRO. Não há veículos com o modelo %-12s|\n", modelo);
            System.out.println("+------------------------------------------------+");
        } else {
            System.out.println("+----------------------------------------------------------------------------------+");
        }
    }

    public static void consultaPorCor() {
        System.out.println("CONSULTA POR COR");
        System.out.print("Informe a cor: ");
        String cor = entrada.nextLine();
        boolean veiculoEncontrado = false;
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getCor().equalsIgnoreCase(cor)) {
                if (!veiculoEncontrado) {
                    System.out.println("+----------------------------------------------------------------------------------+");
                    System.out.printf("| %10s | %-12s | %-5s | %-15s | %-13s | %-10s |\n", "PLACA", "MODELO", "ANO", "COR", "QUILOMETRAGEM", "STATUS");
                    System.out.println("+------------+--------------+-------+-----------------+---------------+------------+");
                }
                if (!veiculoLocado(veiculo.getPlaca())) {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Disponível");
                } else {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Locado");
                }
                veiculoEncontrado = true;
            }
        }
        if (!veiculoEncontrado) {
            System.out.println("+-------------------------------------------------+");
            System.err.printf("| ERRO. Não há veículos com a cor %-15s |\n", cor);
            System.out.println("+-------------------------------------------------+");
        } else {
            System.out.println("+----------------------------------------------------------------------------------+");
        }
    }

    public static void consultaPorQuilometragem() {
        System.out.println("CONSULTA POR QUILOMETRAGEM");
        System.out.print("Informe km mínimo: ");
        long kmMinimo = entrada.nextLong();
        System.out.print("Informe km máxima: ");
        long kmMaximo = entrada.nextLong();
        boolean veiculoEncontrado = false;
        for (Veiculo veiculo : veiculosRegistrados) {
            if (veiculo.getQuilometragem() >= kmMinimo && veiculo.getQuilometragem() <= kmMaximo) {
                if (!veiculoEncontrado) {
                    System.out.println("+----------------------------------------------------------------------------------+");
                    System.out.printf("| %10s | %-12s | %-5s | %-15s | %-13s | %-10s |\n", "PLACA", "MODELO", "ANO", "COR", "QUILOMETRAGEM", "STATUS");
                    System.out.println("+------------+--------------+-------+-----------------+---------------+------------+");
                }
                if (!veiculoLocado(veiculo.getPlaca())) {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Disponível");
                } else {
                    System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s | %-10s |\n", veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km", "Locado");
                }
                veiculoEncontrado = true;
            }
        }
        if (!veiculoEncontrado) {
            System.out.println("+------------------------------------------------------+");
            System.err.println("| Erro. Não há veículos com a quilometragem informada. |");
            System.out.println("+------------------------------------------------------+");
        } else {
            System.out.println("+----------------------------------------------------------------------------------+");
        }
    }

    public static void veiculosLocados() {
        if (veiculosLocados.size() > 0) {
            System.out.println("+--------------------------------------------------------------------------------------------+");
            System.out.printf("| %20s | %-10s | %-12s | %-5s | %-15s | %-13s |\n", "NOME DO CLIENTE", "PLACA", "MODELO", "ANO", "COR", "QUILOMETRAGEM");
            System.out.println("+----------------------+------------+--------------+-------+-----------------+---------------+");
            for (Locacao locado : veiculosLocados) {
                locado.printFormatado();
            }
            System.out.println("+--------------------------------------------------------------------------------------------+");
        } else {
            System.out.println("+----------------------------------------+");
                System.err.println("| ERRO. Não há veículos locados.         |");
                System.out.println("+----------------------------------------+");
        }
    }

    public static void veiculosNaoLocados() {
        List<Veiculo> veiculosNaoLocados = new ArrayList<>();
        for (Veiculo veiculo : veiculosRegistrados) {
            if (!veiculoLocado(veiculo.getPlaca())) {
                veiculosNaoLocados.add(veiculo);
            }
        }
        if (veiculosNaoLocados.size() > 0) {
            System.out.println("+---------------------------------------------------------------------+");
            System.out.printf("| %10s | %-12s | %-5s | %-15s | %-13s |\n", "PLACA", "MODELO", "ANO", "COR", "QUILOMETRAGEM");
            System.out.println("+------------+--------------+-------+-----------------+---------------+");
            for (Veiculo veiculo : veiculosNaoLocados) {
                veiculo.printFormatado();
            }
            System.out.println("+---------------------------------------------------------------------+");
        } else {
            System.out.println("+----------------------------------------+");
            System.err.println("|ERRO. Não há veículos não locados.      |");
            System.out.println("+----------------------------------------+");
        }
    }

    public static void salvarVeiculos() {

        System.out.println("SALVANDO VEÍCULOS ...");

        try {
            FileOutputStream fluxoArquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream fluxoObjetos = new ObjectOutputStream(fluxoArquivo);

            Dados data = new Dados(veiculosRegistrados, veiculosLocados);
            fluxoObjetos.writeObject(data);

            fluxoObjetos.close();
            fluxoArquivo.close();

            System.out.println("Veículos salvos!");
        } catch (IOException e) {
            System.out.println("+----------------------------------------+");
            System.out.println("|Erro ao salvar veículos: " + e.getMessage() + "|");
            System.out.println("+----------------------------------------+");
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
        } else {
            System.out.println("+----------------------------------------+");
            System.err.println("ERRO. Não há veículos a serem recuperados!");
            System.out.println("+----------------------------------------+");
        }
    }

    public static void main(String[] args) throws Exception {
        recuperarVeiculos();
        char option;
        do {
            menu();
            option = entrada.next().toLowerCase().charAt(0);
            System.out.println();
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
                    menuConsulta();
                    break;

                case 'e':
                    veiculosLocados();
                    break;

                case 'f':
                    veiculosNaoLocados();
                    break;

                default:
                    break;
            }
        } while (option != 'x');
        salvarVeiculos();
    }
}