package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private Distancia distancias = new Distancia();
    private Map<String, Integer> produtosList; //produtosList: Um mapa que será usado para armazenar os produtos e suas quantidades
    private Produtos produtos; //produtos: Uma instância da classe Produtos, que é usada para obter informações sobre produtos.

    private static double custoTotal = 0;
    private static double custoMedioPorKm = 0;
    private static int numeroTotalVeiculosDeslocados = 0;

    private static Map<String, Integer> totalDeCadaTipoDeItemTransportado = new HashMap<String, Integer>();
    private static int totalDeItensTransportados = 0;

    private static int numeroTotalDeVeiculosDeslocados = 0;

    public Menu() { //construtor
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos e obtém a lista de nomes de produtos chamando o método getNomesProdutos()

        this.listarCidades();
        System.out.println(" ");
        this.listarModaldidadesDeCaminhao();
    }

    public void listarNomesProdutos() { //esse método é responsável por listar os nomes dos produtos disponíveis
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos
        List<String> nomesProdutos = produtos.getNomesProdutos(); //obtém a lista de nomes de produtos chamando o método getNomesProdutos()

        System.out.println("Nomes dos produtos disponíveis:");

        for (String nomeProduto : nomesProdutos) { //exibem os nomes dos produtos disponíveis no console
            System.out.println(nomeProduto);
        }
    }

    public void exibirMenu() { //responsável por exibir o menu principal e interagir com o usuário

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

    private void listarCidades() {
        // Listando as cidades quando a classe Menu é instânciada
        System.out.println("Cidades:");
        for (int i = 0; i < distancias.getCidades().length; i++){
            if(i > 0 && i % 3 == 0) {
                System.out.println(" ");
            }
            System.out.print(distancias.getCidades()[i] + "  -  ");
        }
    }

    private void listarModaldidadesDeCaminhao(){
        // Listando as modalidades de caminhão quando a classe Menu é instânciada
        System.out.println("Modalidades de caminhão:\n" +
                "Pequeno [1 tonelada] -  Médio [4 toneladas] -  Grande [10 toneladas]");

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

    public void atualizarTotalDeItensTransportados(String produto, int quantidadeDeItens){

        // verifica esse item já foi inserido na lista de item específico transportado
        if(totalDeCadaTipoDeItemTransportado.containsKey(produto)){

            // percorre a lista de item específico transportado
            for (Map.Entry<String, Integer> item: totalDeCadaTipoDeItemTransportado.entrySet()){

                // comparar a chave da lista com o nome do produto
                if (item.getKey().equalsIgnoreCase(produto)){
                    int novaQuantidade = item.getValue() + quantidadeDeItens;

                    // atualiza a quantidade do item específico transportado
                    totalDeCadaTipoDeItemTransportado.put(produto, novaQuantidade);
                }
            }
        } else {
            // se o item não está na lista

            // adiciona o item na lista de Item Transportado e a quantidade
            totalDeCadaTipoDeItemTransportado.put(produto, quantidadeDeItens);
        }

        // atualiza a lista total de itens transportados
        totalDeItensTransportados += quantidadeDeItens;
    }

    private void atualizarNumeroTotalDeVeiculosDeslocados(int quantidadeDeVeiculos){
        numeroTotalDeVeiculosDeslocados += quantidadeDeVeiculos;
    }


    //==================================================================================================================


    public void consultarTrechoModalidade() {}
    public void cadastrarTransportes() {}
    public void relatorioTransportesCadastrados() {}


    //==================================================================================================================



    //método para calcular o valor da viagem de uma cidade para a outra
    public void calculaValorDaViagem(String cidadeOrigem, String cidadeDestino, String tipoCaminhao) {
        Distancia distancia = new Distancia(); //instância a classe Distancia para calcular a distância entre cidades

        int distanciaKm = distancia.calcularDistanciaEntreCidades(cidadeOrigem, cidadeDestino);
        if (distanciaKm <= 0) { //verifica se distância > 0
            System.out.println("As cidades " +cidadeOrigem+ " e " +cidadeDestino+ " estão na mesma localização ou a distância não foi encontrada na nossa base de dados.");
            return;
        }

        Caminhoes caminhoes = new Caminhoes(); //instância a classe Caminhoes para saber o preço por km da modalidade de caminhão selecionada
        double precoPorKm = caminhoes.getPrecoPorKm(tipoCaminhao);
        double custoTransporte = distanciaKm * precoPorKm;

        System.out.println("A distância entre " +cidadeOrigem+ " e " +cidadeDestino+ ": " +distanciaKm+ " km");
        System.out.println("Modalidade de caminhão escolhido: " +tipoCaminhao);
        System.out.println("Custo da viagem: R$" +custoTransporte);
    }
}

