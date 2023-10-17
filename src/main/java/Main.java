import classes.Caminhoes;
import classes.Distancia;
import classes.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        //menu.adicionarCidades();
        Scanner scanner = new Scanner(System.in);

        while (true) {  // inicia um loop para exibir o menu e aguardar a entrada do usuário
            // exibe as opções do menu no console
            System.out.println("\nMenu Principal:");
            System.out.println("1. Exibir Cidades, Caminhões e Produtos");
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
                    // Verifica se deve executar o case.
                    if (!shouldBreak) {
                        menu.exibirMenu();  // Chama o método 1 da classe Menu.
                        shouldBreak = true; // Determina que os outros cases não devem ser executados.
                    }
                case 2:
                    if (!shouldBreak) {
                        menu.consultarTrechosEModalidades();
                        shouldBreak = true;
                    }
                case 3:
                    if (!shouldBreak) {
                        menu.cadastrarTransportes();
                        shouldBreak = true;
                    }
                case 4:
                    if (!shouldBreak) {
                        menu.relatorioTransportesCadastrados();
                        shouldBreak = true;
                    }
                case 5:
                    if (!shouldBreak) {
                        System.out.println("Encerrando o programa.");
                        scanner.close();
                        System.exit(0);
                    }
                default:
                    if (!shouldBreak) {
                        System.out.println("Essa opção não existe");
                    }
            }
        }
    }
}