package classes;

import java.util.*;

public class Menu extends Caminhoes {

    Scanner leitor = new Scanner(System.in);

    private Distancia distancias = new Distancia();
    private Caminhoes caminhoes = new Caminhoes();
    private Map<String, Integer> produtosList; //produtosList: Um mapa que será usado para armazenar os produtos e suas quantidades
    private Produtos produtos; //produtos: Uma instância da classe Produtos, que é usada para obter informações sobre produtos.
    private List<Transporte> transportes;
    private static double custoTotalPorModalidadeDeTransporte = 0;
    private static double custoTotal = 0;
    private static double custoMedioPorKm = 0;
    private static int numeroTotalVeiculosDeslocados = 0;
    private List<Produtos> itensParaTransportar;
    private static Map<String, Integer> totalDeCadaTipoDeItemTransportado = new HashMap<String, Integer>();
    private static Map<String, Double> totalDeCustoPorModalidadeDeTransporte = new HashMap<String, Double>();
    private static int totalDeItensTransportados = 0;
    private static int numeroTotalDeVeiculosDeslocados = 0;
    private static HashMap<String, HashMap<String, Double>> custoPorTrecho = new HashMap<String, HashMap<String, Double>>();
    private static HashMap<String, Double> custoTotalPorTrecho = new HashMap<String, Double>();
    private String modalidadeCaminhao;
    private String cidadeOrigem;
    private String cidadeDestino;

    private static Map<String, Double> custoTotalPorProduto = new HashMap<String, Double>();

    public Menu() { //construtor
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos e obtém a lista de nomes de produtos chamando o método getNomesProdutos()
        transportes = new ArrayList<>();
//        this.listarCidades();
        System.out.println(" ");
//        this.listarModaldidadesDeCaminhao();
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

//        listarCidades();
//        listarModaldidadesDeCaminhao();
//        listarNomesProdutos();

//        Scanner scanner = new Scanner(System.in);

//        while (true) { //inicia um loop infinito para exibir o menu e aguardar a entrada do usuário
//
//            //exibe as opções do menu no console
//            System.out.println("\nMenu:");
//            System.out.println("1. Adicionar produto à lista");
//            System.out.println("2. Exibir produtos da lista");
//            System.out.println("3. Excluir produto da lista");
//            System.out.println("4. Exibir lista dos produtos disponíveis");
//            System.out.println("5. Sair");
//
//            int opcao = scanner.nextInt();
//
//            //usa um bloco switch para determinar qual ação executar com base na opção escolhida
//            switch (opcao) {
//                case 1:
//                    adicionarProduto();
//                    break;
//                case 2:
//                    listarProdutos();
//                    break;
//                case 3:
//                    excluirProduto();
//                    break;
//                case 4:
//                    listarNomesProdutos();
//                    break;
//                case 5:
//                    System.out.println("Encerrando o programa.");
//                    scanner.close();
//                    System.exit(0);
//                default:
//                    System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
//            }
//        }
    }

    public void consultarTrechoPorModalidade() {

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
        for (int i = 0; i < distancias.getCidades().length; i++) {
            if (i > 0 && i % 3 == 0) {
                System.out.println(" ");
            }
            System.out.print(distancias.getCidades()[i] + "  -  ");
        }
        System.out.println("\n");
    }

    private void listarModaldidadesDeCaminhao() {
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
//    public void adicionarItensParaTransportar(int quantidade){
//        //
//        double peso = this.produtos.getPesoProduto("") * quantidade;
//        if(peso <= caminhoes.getCapacidadeMaximaTonelada("")){
//            for (int i = 0; i < quantidade; i++){
//                itensParaTransportar.add(produtos);
//            }
//            System.out.println("Produto(s) adicionado(s): " + quantidade + " : " + this.produtos.getNomesProdutos());
//        } else {
//            System.out.println("Capacidade máxima alcançada.");
//        }
//    }

    public void atualizarTotalDeItensTransportados(String produto, int quantidadeDeItens) {

        // verifica esse item já foi inserido na lista de item específico transportado
        if (totalDeCadaTipoDeItemTransportado.containsKey(produto)) {

            // percorre a lista de item específico transportado
            for (Map.Entry<String, Integer> item : totalDeCadaTipoDeItemTransportado.entrySet()) {

                // comparar a chave da lista com o nome do produto
                if (item.getKey().equalsIgnoreCase(produto)) {
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

    private void atualizarNumeroTotalDeVeiculosDeslocados(int quantidadeDeVeiculos) {
        numeroTotalDeVeiculosDeslocados += quantidadeDeVeiculos;
    }

    private void atualizarCustoPorTrecho(String cidade1, String cidade2, String tipoCaminhao, double custo) {
        String trecho;
        if (cidade1.compareTo(cidade2) < 0) {
            trecho = cidade1 + "-" + cidade2;
        } else {
            trecho = cidade2 + "-" + cidade1;
        }


        if (custoPorTrecho.containsKey(tipoCaminhao)) {
            HashMap<String, Double> trechos = custoPorTrecho.get(tipoCaminhao);


            if (trechos.containsKey(trecho)) {
                trechos.put(trecho, trechos.get(trecho) + custo);
                atualizarCustoTotalPorTrecho(trecho, custo);
            } else {
                trechos.put(trecho, custo);
                atualizarCustoTotalPorTrecho(trecho, custo);
            }
        } else {
            HashMap<String, Double> trechoValor = new HashMap<>();
            trechoValor.put(trecho, custo);
            custoPorTrecho.put(tipoCaminhao, trechoValor);
            atualizarCustoTotalPorTrecho(trecho, custo);
        }
    }

    private void atualizarCustoTotalPorTrecho(String trecho, double custo) {

        if (custoTotalPorTrecho.containsKey(trecho)) {
            custoTotalPorTrecho.put(trecho, custoTotalPorTrecho.get(trecho) + custo);
        } else {
            custoTotalPorTrecho.put(trecho, custo);
        }
    }

    public void consultarTrechoModalidade() {
    }

    public void cadastrarTransportes() {
    }

    public void relatorioTransportesCadastrados() {
    }

//    public void atualizarCustoTotalPorModalidadeDeTransporte(String tipoCaminhao) {
//        double custoTotal = 0;
//        for (Transporte transporte : transportes) {
//            if (transporte.getTipoCaminhao().equals(tipoCaminhao)) {
//                custoTotal += transporte.getCusto();
//            }
//        }
//        System.out.println("Custo total para a modalidade " + tipoCaminhao + ": R$" + custoTotal);
//    }

    public void atualizarCustoTotalPorModalidadeDeTransporte(String tipoCaminhao, int quantidade) {
        if (totalDeCustoPorModalidadeDeTransporte.containsKey(tipoCaminhao)) {
            for (Map.Entry<String, Double> tipo : totalDeCustoPorModalidadeDeTransporte.entrySet()) {
                if (tipo.getKey().equalsIgnoreCase(tipoCaminhao)) {
                    double novoValor = tipo.getValue() + (quantidade * caminhoes.getPrecoPorKm(tipoCaminhao));
                    totalDeCustoPorModalidadeDeTransporte.put(tipoCaminhao, novoValor);
                }
            }
        } else {
            double valor = quantidade * caminhoes.getPrecoPorKm(tipoCaminhao);
            totalDeCustoPorModalidadeDeTransporte.put(tipoCaminhao, valor);
        }
    }

    //método para calcular o valor da viagem de uma cidade para a outra
    public void calculaValorDaViagem(String cidadeOrigem, String cidadeDestino, String tipoCaminhao) {
        Distancia distancia = new Distancia(); //instância a classe Distancia para calcular a distância entre cidades

        int distanciaKm = distancia.calcularDistanciaEntreCidades(cidadeOrigem, cidadeDestino);
        if (distanciaKm <= 0) { //verifica se distância > 0
            System.out.println("As cidades " + cidadeOrigem + " e " + cidadeDestino + " estão na mesma localização ou a distância não foi encontrada na nossa base de dados.");
            return;
        }

        double precoPorKm = caminhoes.getPrecoPorKm(tipoCaminhao);
        double custoTransporte = distanciaKm * precoPorKm;

        System.out.println("A distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + distanciaKm + " km");
        System.out.println("Modalidade de caminhão escolhido: " + tipoCaminhao);
        System.out.println("Custo da viagem: R$" + custoTransporte);
    }

    public void atualizarCustoTotalPorModalidadeDeTransporte(String tipoCaminhao) {
        double custoTotal = 0;
        for (Transporte transporte : transportes) {
            if (transporte.getTipoCaminhao().equals(tipoCaminhao)) {
                custoTotal += transporte.getCusto();
            }
        }
        System.out.println("Custo total para a modalidade " + tipoCaminhao + ": R$" + custoTotal);
    }

    public void solicitarCidadesETransportes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a cidade de origem: "); //pede cidade de origem
        try {
            this.cidadeOrigem = scanner.nextLine();
            if (this.cidadeOrigem.isEmpty()) {
                throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
            }
            if (!Arrays.asList(distancias.getCidades()).contains(this.cidadeOrigem.toUpperCase())) {
                throw new IllegalArgumentException("A cidade escolhida não existe no nosso banco de dados!");
            }
            System.out.print("Digite a cidade de destino: "); //pede cidade de destino
            this.cidadeDestino = scanner.nextLine();
            if (this.cidadeDestino.isEmpty()) {
                throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
            }
            if (!Arrays.asList(distancias.getCidades()).contains(this.cidadeDestino.toUpperCase())) {
                throw new IllegalArgumentException("A cidade escolhida não existe no nosso banco de dados!");
            }
            System.out.print("Escolha o tipo de caminhão (pequeno, medio, grande): "); //pede o tipo de caminhão
            this.modalidadeCaminhao = scanner.nextLine();
            if (this.modalidadeCaminhao.isEmpty()) {
                throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
            }
            if (!Arrays.asList(caminhoes.getTipoCaminhoes()).contains(this.modalidadeCaminhao.toLowerCase())) {
                throw new IllegalArgumentException("O tipo de caminhão escolhido não existe na nossa base de dados!");
            }
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
        }

    }

    // essa lista armazena o custo total por produto, todas as vezes que ele foi cadastrado
    private void atualizarCustoTotalPorProduto(String produto, double pesoProduto, double custoTotal, double pesoTotalProduto) {
        double porcentagem = (100 * pesoProduto) / pesoTotalProduto;
        double custoProduto = (porcentagem * custoTotal) / 100;
        // verifica se o produto ja está na lista
        if (custoTotalPorProduto.containsKey(produto)) {
            // se ele ta na lista vai aumentar o valor do produto
            // faz o valor total dos produtos
            custoTotalPorProduto.put(produto, custoTotalPorProduto.get(produto) + custoProduto);
            System.out.println(produto + " - " + custoTotalPorProduto.get(produto));

        } else {
            // se nao ta na lista
            // ele adiciona o novo produto na lista com o novo valor total
            custoTotalPorProduto.put(produto, custoProduto);
            System.out.println(produto + " - " + custoTotalPorProduto.get(produto));
        }
    }
    private HashMap<String, Double> calculaValorMedioProduto(){
        HashMap<String, Double> valorMedioProduto = new HashMap<String, Double>();
        for (Map.Entry<String, Integer> produto: totalDeCadaTipoDeItemTransportado.entrySet()){
            // primeiro pega o valor total do produto e depois divide pela quantidade de produto que já foi cadastrado
            double valorMedio = custoTotalPorProduto.get(produto.getKey()) / produto.getValue();
            valorMedioProduto.put(produto.getKey(), valorMedio);
        }
        // retorna um hashmap com todos os valores médios dos produtos
        return valorMedioProduto;
    }

    public void consultarTrechosEModalidades() {
        System.out.println("\nTrechos disponíveis para transporte: ");
        listarCidades();

        solicitarCidadesETransportes();

        calculaValorDaViagem(this.cidadeOrigem.toUpperCase(), this.cidadeDestino.toUpperCase(), this.modalidadeCaminhao.toLowerCase());
    }
}