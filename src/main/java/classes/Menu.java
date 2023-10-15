package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu extends Caminhoes {
    private Map<String, Integer> produtosList; //produtosList: Um mapa que será usado para armazenar os produtos e suas quantidades
    private Produtos produtos; //produtos: Uma instância da classe Produtos, que é usada para obter informações sobre produtos.

    private List<Transporte> transportes;

    private static double custoMedioPorProduto = 0;
    private static double custoTotalPorModalidadeDeTransporte = 0;

    public void listarNomesProdutos() { //esse método é responsável por listar os nomes dos produtos disponíveis
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos
        List<String> nomesProdutos = produtos.getNomesProdutos(); //obtém a lista de nomes de produtos chamando o método getNomesProdutos()

        System.out.println("Nomes dos produtos disponíveis:");

        for (String nomeProduto : nomesProdutos) { //exibem os nomes dos produtos disponíveis no console
            System.out.println(nomeProduto);
        }
    }

    public Menu() { //construtor
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos e obtém a lista de nomes de produtos chamando o método getNomesProdutos()
        transportes = new ArrayList<>();
    }

    public void exibirMenu() { //responsável por exibir o menu principal e interagir com o usuário

        listaCidades();
        listarNomesProdutos();

        Scanner scanner = new Scanner(System.in);

        while (true) { //inicia um loop infinito para exibir o menu e aguardar a entrada do usuário

            //exibe as opções do menu no console
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar produto à lista");
            System.out.println("2. Exibir produtos da lista");
            System.out.println("3. Excluir produto da lista");
            System.out.println("4. Exibir lista dos produtos disponíveis");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

            //usa um bloco switch para determinar qual ação executar com base na opção escolhida
            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    listarNomesProdutos();
                    break;
                case 5:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
            }
        }
    }

    public void adicionarProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto que deseja adicionar: ");
        String nomeProduto = scanner.nextLine();

        if (produtos.getPesoProduto(nomeProduto) != 0.0) {
            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();

            if (quantidade > 0) {
                produtosList.put(nomeProduto, quantidade);
//                double custoTransporte = calcularCustoTransporte(tipoCaminhao, distancia);
//                transportes.add(new Transporte(tipoCaminhao, custoTransporte));
                System.out.println("Produto adicionado à lista: " +nomeProduto+ "\nQuantidade: " +quantidade);
            } else {
                System.out.println("A quantidade deve ser maior que zero.");
            }
        } else {
            System.out.println("O produto escolhido não existe na nossa base de dados, digite um produto existente.");
        }
    }

    public void listarProdutos() {
        if (produtosList.isEmpty()) {
            System.out.println("A lista de produtos está vazia.");
        } else {
            System.out.println("Produtos na lista:");
            for (Map.Entry<String, Integer> entry : produtosList.entrySet()) {
                System.out.println(entry.getKey() + " - Quantidade: " + entry.getValue());
            }
        }
    }

    public void excluirProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto que deseja excluir: ");
        String nomeProduto = scanner.nextLine();

        if (produtosList.containsKey(nomeProduto)) {
            produtosList.remove(nomeProduto);
            System.out.println("Produto removido da lista!");
        } else {
            System.out.println("Produto não encontrado na lista.");
        }
    }

    public void consultarTrechoModalidade() {}
    public void cadastrarTransportes() {}
    public void relatorioTransportesCadastrados() {}

    public void atualizarCustoTotalPorModalidadeDeTransporte(String tipoCaminhao) {
        double custoTotal = 0;
        for (Transporte transporte : transportes) {
            if (transporte.getTipoCaminhao().equals(tipoCaminhao)) {
                custoTotal += transporte.getCusto();
            }
        }
        System.out.println("Custo total para a modalidade " + tipoCaminhao + ": R$" + custoTotal);
    }
}