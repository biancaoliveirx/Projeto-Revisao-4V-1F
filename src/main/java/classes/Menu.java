package classes;

import java.util.*;

import static java.lang.String.format;

public class Menu{

    Scanner leitor;

    private Distancia distancias = new Distancia();
    private Caminhoes caminhoes = new Caminhoes();
    private Map<String, Integer> produtosList; //produtosList: Um mapa que será usado para armazenar os produtos e suas quantidades
    private Map<String, Double> pesoPorQuantidadeProduto;

    private HashMap<String, HashMap<String, Integer>> produtosDescarregados = new HashMap<String, HashMap<String, Integer>>();

    private ArrayList<String> cidadesEscolhidas;
    private Produtos produtos; //produtos: Uma instância da classe Produtos, que é usada para obter informações sobre produtos.

    private static double custoTotalPorModalidadeDeTransporte = 0;
    private static double custoTotal = 0;
    private static double custoMedioPorKm = 0;
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

    private HashMap<String, Double> precoTrecho = new HashMap<String, Double>();
    private static double totalKm = 0;

    public Menu() { //construtor
        produtosList = new HashMap<>(); //inicializa produtosList como um novo mapa vazio
        produtos = new Produtos(); //cria uma instância da classe Produtos e obtém a lista de nomes de produtos chamando o método getNomesProdutos()
        System.out.println(" ");
    }

    public void exibirMenu(){
        listarCidades();
        listarModaldidadesDeCaminhao();
        listarNomesProdutos();
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

    private boolean menuProdutos() {
        leitor = new Scanner(System.in);
        boolean finalizaPrograma = false;

        while (!finalizaPrograma) { //inicia um loop infinito para exibir o menu e aguardar a entrada do usuário

            boolean quebra = false;
            //exibe as opções do menu no console
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar produto à lista");
            System.out.println("2. Exibir produtos da lista");
            System.out.println("3. Excluir produto da lista");
            System.out.println("4. Exibir lista dos produtos disponíveis");
            System.out.println("5. Sair");

            int opcao = this.leitor.nextInt();

            //usa um bloco switch para determinar qual ação executar com base na opção escolhida
            switch (opcao) {
                case 1:
                    if (!quebra) {
                        adicionarProduto();
                        quebra = true;
                    }
                case 2:
                    if (!quebra) {
                        listarProdutos();
                        quebra = true;
                    }
                case 3:
                    if (!quebra) {
                        excluirProduto();
                        quebra = true;
                    }
                case 4:
                    if (!quebra) {
                        listarNomesProdutos();
                        quebra = true;
                    }
                case 5:
                    if (!quebra) {
                        quebra = true;
                        finalizaPrograma = true;
                        return true;
                    }
                default:
                    if (!quebra) {
                        System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
                    }
            }
        }
        return true;
    }

    public void adicionarProduto() {
        leitor = new Scanner(System.in);
        System.out.print("Digite o nome do produto que deseja adicionar: ");
        String nomeProduto = leitor.nextLine().toUpperCase();

        if (produtos.getPesoProduto(nomeProduto) != 0.0) {
            System.out.print("Digite a quantidade desejada: ");
            int quantidade = leitor.nextInt();

            if (quantidade > 0) {
                if (produtosList.containsKey(nomeProduto)) {
                    produtosList.put(nomeProduto, produtosList.get(nomeProduto) + quantidade);
                    atualizarTotalDeItensTransportados(nomeProduto, quantidade);
                    System.out.println("Produto adicionado à lista: " + nomeProduto + "\nQuantidade: " + quantidade);
                } else {
                    produtosList.put(nomeProduto, quantidade);
                    atualizarTotalDeItensTransportados(nomeProduto, quantidade);
                    System.out.println("Produto adicionado à lista: " + nomeProduto + "\nQuantidade: " + quantidade);
                }

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
        leitor = new Scanner(System.in);
        System.out.print("Digite o nome do produto que deseja excluir: ");
        String nomeProduto = leitor.nextLine();

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

    public void atualizarCustoTotal() {
        // soma o custo total com o valor
        for (Map.Entry<String, Double> custo : this.precoTrecho.entrySet()) {
            custoTotal += custo.getValue();
        }
    }

    public void atualizarCustoMedioKm() {
        custoMedioPorKm = custoTotal / totalKm;
    }

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

    public void atualizarCustoTotalPorModalidadeDeTransporte(String tipoCaminhao, int quantidade, int distancia) {
        if (totalDeCustoPorModalidadeDeTransporte.containsKey(tipoCaminhao)) {
            for (Map.Entry<String, Double> tipo : totalDeCustoPorModalidadeDeTransporte.entrySet()) {
                if (tipo.getKey().equalsIgnoreCase(tipoCaminhao)) {
                    double novoValor = tipo.getValue() + (quantidade * caminhoes.getPrecoPorKm(tipoCaminhao)) * distancia;
                    totalDeCustoPorModalidadeDeTransporte.put(tipoCaminhao, novoValor);
                }
            }
        } else {
            double valor = quantidade * caminhoes.getPrecoPorKm(tipoCaminhao) * distancia;
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

    public void solicitarCidadesETransportes(boolean cadastrar) {
        leitor = new Scanner(System.in);
        try {
            System.out.print("Digite a cidade de origem: "); //pede cidade de origem
            this.cidadeOrigem = this.leitor.nextLine().toUpperCase();
            if (this.cidadeOrigem.isEmpty()) {
                throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
            }
            if (!Arrays.asList(distancias.getCidades()).contains(this.cidadeOrigem.toUpperCase())) {
                throw new IllegalArgumentException("A cidade escolhida não existe no nosso banco de dados!");
            }
            if (this.cidadeDestino != null && !this.cidadeOrigem.equalsIgnoreCase(this.cidadeDestino)){
                throw new IllegalArgumentException("Para um novo trecho a cidade de origem deve ser igual a cidade de destino!");
            }
            System.out.print("Digite a cidade de destino: "); //pede cidade de destino
            this.cidadeDestino = this.leitor.nextLine().toUpperCase();
            if (this.cidadeDestino.isEmpty()) {
                throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
            }
            if (!Arrays.asList(distancias.getCidades()).contains(this.cidadeDestino.toUpperCase())) {
                throw new IllegalArgumentException("A cidade escolhida não existe no nosso banco de dados!");
            }
            if (!cadastrar) {
                System.out.print("Escolha o tipo de caminhão (pequeno, medio, grande): "); //pede o tipo de caminhão
                this.modalidadeCaminhao = this.leitor.nextLine().toLowerCase();
                if (this.modalidadeCaminhao.isEmpty()) {
                    throw new InputMismatchException("Os campos precisam ser preenchidos corretamente!");
                }
                if (!Arrays.asList(caminhoes.getTipoCaminhoes()).contains(this.modalidadeCaminhao.toLowerCase())) {
                    throw new IllegalArgumentException("O tipo de caminhão escolhido não existe na nossa base de dados!");
                }
            }
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
        }

    }

    // essa lista armazena o custo total por produto, todas as vezes que ele foi cadastrado
    private void atualizarCustoTotalPorProduto(double custoTotal, double pesoTotalProduto) {
        for (Map.Entry<String, Double> produto : this.pesoPorQuantidadeProduto.entrySet()) {
            double porcentagem = (100 * produto.getValue()) / pesoTotalProduto;
            double custoProduto = (porcentagem * custoTotal) / 100;
            // verifica se o produto ja está na lista
            if (custoTotalPorProduto.containsKey(produto.getKey())) {
                // se ele ta na lista vai aumentar o valor do produto
                // faz o valor total dos produtos
                custoTotalPorProduto.put(produto.getKey(), custoTotalPorProduto.get(produto.getKey()) + custoProduto);
                System.out.println(produto.getKey() + " - " + custoTotalPorProduto.get(produto.getKey()));
            } else {
                // se nao ta na lista
                // ele adiciona o novo produto na lista com o novo valor total
                custoTotalPorProduto.put(produto.getKey(), custoProduto);
                System.out.println(produto.getKey() + " - " + custoTotalPorProduto.get(produto.getKey()));
            }
        }

    }

    private HashMap<String, Double> calculaValorMedioProduto() {
        HashMap<String, Double> valorMedioProduto = new HashMap<String, Double>();
        for (Map.Entry<String, Integer> produto : totalDeCadaTipoDeItemTransportado.entrySet()) {
            // primeiro pega o valor total do produto e depois divide pela quantidade de produto que já foi cadastrado
            double valorMedio = custoTotalPorProduto.get(produto.getKey()) / produto.getValue();
            valorMedioProduto.put(produto.getKey(), valorMedio);
        }
        // retorna um hashmap com todos os valores médios dos produtos
        return valorMedioProduto;
    }

    public void consultarTrechosEModalidades() {
        this.cidadeOrigem = null;
        this.cidadeDestino = null;
        System.out.println("\nTrechos disponíveis para transporte: ");
        listarCidades();

        solicitarCidadesETransportes(false);

        calculaValorDaViagem(this.cidadeOrigem.toUpperCase(), this.cidadeDestino.toUpperCase(), this.modalidadeCaminhao.toLowerCase());
    }

    public boolean cadastrarTransportes() {
        this.cidadeOrigem = null;
        this.cidadeDestino = null;
        leitor = new Scanner(System.in);
        double custoTotal = 0;
        boolean continuarPrograma = false;
        int opcao;
        boolean quebra = false;
        while (!continuarPrograma) {
            quebra = false;
            System.out.println("""
                    Cadastrar Transportes
                    1. Listar Cidades
                    2. Adicionar Produtos
                    3. Cadastrar trecho
                    """);

            opcao = this.leitor.nextInt();

            switch (opcao) {
                case 1:
                    this.listarCidades();
                    quebra = true;
                case 2:
                    if (!quebra) {
                        this.menuProdutos();
                        quebra = true;
                    }
                case 3:
                    if (!quebra) {
                        quebra = true;
                        continuarPrograma = true;
                    }
                default:
                    if (!quebra) {
                        System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
                    }
            }
        }

        System.out.println("Digite o primeiro trecho");
        this.solicitarCidadesETransportes(true);
        custoTotal += this.calculaCustoESelecionaMelhoModalidade();
        String trecho = this.cidadeOrigem + " - " + this.cidadeDestino;
        this.precoTrecho.put(trecho, custoTotal);

        int descarregar;
        boolean finalizar = false;
        while (!finalizar) {
            boolean quebraDescarregar = false;
            quebra = false;
            System.out.println("""
                     
                    1. Digitar novo trecho
                    2. Calcular custo total
                    """);
            opcao = this.leitor.nextInt();
            switch (opcao) {
                case 1:
                    this.solicitarCidadesETransportes(true);
                    System.out.println("""
                            Descarregar produtos:
                            1. Sim
                            2. Não
                            """);
                    descarregar = leitor.nextInt();
                    switch (descarregar){
                        case 1:
                            this.descarregarProdutos();
                            custoTotal += this.calculaCustoESelecionaMelhoModalidade();
                            trecho = this.cidadeOrigem + " - " + this.cidadeDestino;
                            this.precoTrecho.put(trecho, custoTotal);
                            quebraDescarregar = true;
                        case 2:
                            if (!quebraDescarregar){
                                custoTotal += this.calculaCustoESelecionaMelhoModalidade();
                                trecho = this.cidadeOrigem + " - " + this.cidadeDestino;
                                this.precoTrecho.put(trecho, custoTotal);
                                quebraDescarregar = true;
                            }
                        default:
                            if (!quebraDescarregar){
                                System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
                            }
                    }
                    quebra = true;
                case 2:
                    if (!quebra) {
                        System.out.println(custoTotal);
                        quebra = true;
                        finalizar = true;
                    }
                default:
                    if (!quebra) {
                        System.out.println("Essa opção não existe no menu! Digite uma opção válida.");
                    }
            }

        }

        System.out.println("Descrição do custo:");
        for (Map.Entry<String, Double> trechoPreco : this.precoTrecho.entrySet()) {
            System.out.println("Trecho " + trechoPreco.getKey() + " - R$" + format("%.2f", trechoPreco.getValue()));
        }
        System.out.println("O custo total R$" + format("%.2f", custoTotal));
        atualizarCustoTotal();
        return true;

    }

    private double calculaCustoESelecionaMelhoModalidade() {

        // calculando peso dos produtos e peso total
        this.pesoPorQuantidadeProduto = new HashMap<String, Double>();
        double pesoTotal = 0;
        for (Map.Entry<String, Integer> produto : this.produtosList.entrySet()) {
            double pesoProduto = produto.getValue() * this.produtos.getPesoProduto(produto.getKey()) * 0.001; // calcula peso do produto e converte para tonelada
            pesoPorQuantidadeProduto.put(produto.getKey(), pesoProduto); // adiciona peso do produto
            pesoTotal += pesoProduto; // incrementa peso total
        }

        double pesoTodosOsProdutos = pesoTotal;

        // calculando custo total definindo melhores modalidades de transporte
        int caminhaoPequeno = 0;
        int caminhaoMedio = 0;
        int caminhaoGrande = 0;

        boolean fim = false;

        while (!fim) {
            if (pesoTotal > 8) {
                caminhaoGrande++;
                if (pesoTotal >= 10) {
                    pesoTotal -= 10;
                } else {
                    fim = true;
                }
            } else if (pesoTotal > 2) {
                caminhaoMedio++;
                if (pesoTotal >= 4) {
                    pesoTotal -= 4;
                } else {
                    fim = true;
                }
            } else {
                caminhaoPequeno++;
                if (pesoTotal >= 2) {
                    pesoTotal -= 2;
                } else if (pesoTotal < 1 && pesoTotal > 0) {
                    caminhaoPequeno++;
                    fim = true;
                } else {
                    fim = true;
                }
            }

            if (pesoTotal == 0) {
                fim = true;
            }
        }

        int distanciaTrecho = distancias.calcularDistanciaEntreCidades(this.cidadeOrigem, this.cidadeDestino);
        totalKm += distanciaTrecho;
        double custoCaminhaoPequeno = caminhaoPequeno * caminhoes.getPrecoPorKm("pequeno") * distanciaTrecho;
        double custoCaminhaoMedio = caminhaoMedio * caminhoes.getPrecoPorKm("medio") * distanciaTrecho;
        double custoCaminhaoGrande = caminhaoGrande * caminhoes.getPrecoPorKm("grande") * distanciaTrecho;
        double custoTrecho = custoCaminhaoPequeno + custoCaminhaoMedio + custoCaminhaoGrande;
        atualizarCustoPorTrecho(this.cidadeOrigem, this.cidadeDestino, "pequeno", custoCaminhaoPequeno);
        atualizarCustoPorTrecho(this.cidadeOrigem, this.cidadeDestino, "medio", custoCaminhaoMedio);
        atualizarCustoPorTrecho(this.cidadeOrigem, this.cidadeDestino, "grande", custoCaminhaoGrande);
        atualizarCustoTotalPorModalidadeDeTransporte("pequeno", caminhaoPequeno, distanciaTrecho);
        atualizarCustoTotalPorModalidadeDeTransporte("medio", caminhaoMedio, distanciaTrecho);
        atualizarCustoTotalPorModalidadeDeTransporte("grande", caminhaoGrande, distanciaTrecho);
        int totalVeiculos = caminhaoPequeno + caminhaoMedio + caminhaoGrande;
        atualizarNumeroTotalDeVeiculosDeslocados(totalVeiculos);
        atualizarCustoMedioKm();
        atualizarCustoTotalPorProduto(pesoTodosOsProdutos,custoTrecho);
        return custoTrecho;
    }

    private void descarregarProdutos() {
        this.leitor = new Scanner(System.in);
        String produto;
        int quantidade;
        boolean fim = false;

        do {
            System.out.print("Digite o produto ou aperte ENTER para CANCELAR: ");
            produto = this.leitor.nextLine().toUpperCase();
            if (produto.isEmpty()) {
                fim = true;
            } else if(this.produtosList.containsKey(produto)){
                System.out.print("Digite a quantidade:");
                quantidade = this.leitor.nextInt();
                if (quantidade <= 0 || quantidade > this.produtosList.get(produto)) {
                    System.out.println("Quantidade inválida.");
                } else {
                    this.produtosList.put(produto, this.produtosList.get(produto) - quantidade);
                    System.out.println("Descarga salva! \n");
                }
            } else {
                System.out.println("Este produto não está na lista");
            }
        } while (!fim);
    }

    public void relatorioTransportesCadastrados() {
        atualizarCustoMedioKm();
        System.out.println("Número total de veículos deslocados :" + numeroTotalDeVeiculosDeslocados);
        System.out.println("Número total de itens transportados :" + totalDeItensTransportados);
        System.out.println("Número do custo total R$: " + format("%.2f", custoTotal));
        System.out.println("Custo por trecho: ");
        for (HashMap.Entry<String, HashMap<String, Double>> trechosModalidade : custoPorTrecho.entrySet()) {
                for (Map.Entry<String, Double> trecho : trechosModalidade.getValue().entrySet()) {
                    System.out.println("Modalidade: "+ trechosModalidade.getKey().toUpperCase() +" - " + trecho.getKey() + " R$" + format("%.2f", trecho.getValue()));
                }
        }
        System.out.println("Custo total por trecho: ");
        for (Map.Entry<String, Double> trecho : custoTotalPorTrecho.entrySet()) {
            System.out.println(trecho.getKey() + " R$" + format("%.2f", trecho.getValue()));
        }
        System.out.println("Custo total por modalidade transporte: ");
        for (HashMap.Entry<String, Double> custoTotalModalidade : totalDeCustoPorModalidadeDeTransporte.entrySet()) {
            System.out.println("Modalidade: " + custoTotalModalidade.getKey() + " - R$" + format("%.2f", custoTotalModalidade.getValue()));
        }
        System.out.println("Custo médio por Km: R$" + format("%.2f", custoMedioPorKm));
        System.out.println("Custo médio por produto: ");
        for(Map.Entry<String, Double> produtoValor : custoTotalPorProduto.entrySet()){
            double mediaProduto = produtoValor.getValue() / totalDeCadaTipoDeItemTransportado.get(produtoValor.getKey());
            System.out.println("          " + produtoValor.getKey() + " - R$" + format("%.2f", mediaProduto));
        }
    }
}


