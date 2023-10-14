package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Map<String, Integer> produtosList;
    private Produtos produtos;

    // static é que só pertence a própria classe
    private static double custoTotal = 0;
    private static double custoMedioPorKm = 0;
    private static int numeroTotalVeiculosDeslocados = 0;

    public void listarNomesProdutos() {
        produtosList = new HashMap<>();
        produtos = new Produtos();
        List<String> nomesProdutos = produtos.getNomesProdutos();

        System.out.println("Nomes dos produtos disponíveis:");

        for (String nomeProduto : nomesProdutos) {
            System.out.println(nomeProduto);
        }
    }

    public Menu() {
        produtosList = new HashMap<>();
        produtos = new Produtos();
    }

    public void exibirMenu() {
        List<String> nomesProdutos = produtos.getNomesProdutos();

        System.out.println("Nomes dos produtos disponíveis:");

        for (String nomeProduto : nomesProdutos) {
            System.out.println(nomeProduto);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar produto à lista");
            System.out.println("2. Exibir produtos da lista");
            System.out.println("3. Excluir produto da lista");
            System.out.println("4. Exibir lista dos produtos disponíveis");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

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
                System.out.println("Produto adicionado à lista: " + nomeProduto + "\nQuantidade: " + quantidade);
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

    public void atualizarCustoTotal(double valor) {
        // soma o custo total com o valor
        custoTotal += valor;
        System.out.println(custoTotal);
    }

    public void atualizarCustoMedioKm() {
        custoMedioPorKm = custoTotal / numeroTotalVeiculosDeslocados;
        System.out.println(custoMedioPorKm);
    }
}
