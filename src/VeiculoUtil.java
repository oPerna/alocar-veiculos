import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class VeiculoUtil {
    public static List<Veiculo> LocacaoDeVeiculos = new ArrayList<>();
    public static Scanner entrada = new Scanner(System.in);

    public static boolean placaExiste(String placa) {
        for (Veiculo veiculo : LocacaoDeVeiculos) {
            if (veiculo.getPlaca().equals(placa)) 
                return true;   
        }

        return false;
    }

    public static void novoVeiculo() {
        
        System.out.println("Insira a placa");
        String placa = entrada.nextLine();

        System.out.println("Insira o modelo");
        String modelo = entrada.nextLine();
        
        System.out.println("Insira o ano");
        int ano = entrada.nextInt();
        
        System.out.println("Insira a cor");
        String cor = entrada.nextLine();

        System.out.println("Insira a quilometragem");
        long quilometragem = entrada.nextLong();
    }
    
    public static void main(String[] args) throws Exception {
       char option;

       do {
           System.out.println("A. Cadastro de Veículos.");
           System.out.println("B. Consulta de Veículos");
           System.out.println("C. ");
           System.out.println("D. ");
           System.out.println("E. ");
           option = entrada.next().toLowerCase().charAt(0);

           switch (option) {
               case 'a':
                   
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
