import classes.Caminhoes;
import classes.Distancia;
import classes.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
         Menu menu = new Menu();
//         menu.cadastrarTransportes();
         //menu.adicionarCidades();
         Scanner scanner = new Scanner(System.in);

         while (true) {  // inicia um loop para exibir o menu e aguardar a entrada do usuário
             // exibe as opções do menu no console
             System.out.println("\nMenu Principal:");
             System.out.println("1. Exibir menu de produtos");
             System.out.println("2. Consultar Trecho Modalidade");
             System.out.println("3. Cadastrar Transportes");
             System.out.println("4. Relatório Transportes Cadastrados");
             System.out.println("5. FINALIZAR PROGRAMA");


             int opcao = scanner.nextInt();
             scanner.nextLine(); // Consumir a linha restante (devido ao Enter pressionado após a entrada numérica)

             // Variável que determina se devemos sair do switch.
             boolean shouldBreak = false;

             switch (opcao) {
                 case 1:
                     if (!shouldBreak) {
                         menu.menuProdutos();
                         shouldBreak = true;
                     }
                     break; // Adicione o break para sair do switch após a execução do caso.
                 case 2:
                     if (!shouldBreak) {
                         menu.consultarTrechosEModalidades();
                         shouldBreak = true;
                     }
                     break;
                 case 3:
                     if (!shouldBreak) {
                         menu.cadastrarTransportes();
                         shouldBreak = true;
                     }
                     break;
                 case 4:
                     if (!shouldBreak) {
                         menu.relatorioTransportesCadastrados();
                         shouldBreak = true;
                     }
                     break;
                 case 5:
                     System.out.println("Encerrando o programa.");
                     scanner.close();
                     System.exit(0);
                     break;
                 default:
                     System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
             }
        }
    }
}