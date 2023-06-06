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
    
    public static void main(String[] args) throws Exception {
       Scanner entrada = new Scanner(System.in);
       char optionSelected;
       do {
           System.out.println("A. Cadastro de Veículos.");
           System.out.println("B. Consulta de Veículos");
           System.out.println("C. ");
           System.out.println("D. ");
           System.out.println("E. ");
           optionSelected = entrada.next().charAt(0);

           switch (optionSelected) {
               case 'A':
               case 'a':
                   
                   break;
               
               case 'B':
               case 'b':

                   break;

               case 'C':
               case 'c':
       
                   break;

               case 'D':
               case 'd':
       
                   break;

               default:
                   break;
           }
       } while (optionSelected != 'e');
    }
}
