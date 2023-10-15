package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe Produtos que gerencia os pesos de diferentes produtos.
public class Produtos {
    // Mapa que armazena o nome do produto como chave e seu peso como valor.
    private Map<String, Double> produtos;

    // Construtor da classe Produtos.
    public Produtos() {
        produtos = new HashMap<>();

        // Adicina produtos e seus pesos correspondentes ao mapa.
        produtos.put("Celular", 0.7);
        produtos.put("Geladeira", 50.0);
        produtos.put("Air Fryer", 3.5);
        produtos.put("Cadeira", 5.0);
        produtos.put("Luminária", 0.8);
        produtos.put("Lavadora de roupa", 15.0);
        produtos.put("PlayStation 5", 3.9);
        produtos.put("Nintendo Switch", 0.3);
    }

    // Método que retorna o peso de um produto específico.
    public double getPesoProduto(String nomeProduto) {
        try {
            // Verifica se o nome do produto é null e lança uma exceção se for.
            if (nomeProduto == null) {
                throw new IllegalArgumentException("Nome do produto não pode ser null");
            }

            // Se o produto existir no mapa, retorna seu nome e peso.
            if (produtos.containsKey(nomeProduto)) {
                return produtos.get(nomeProduto);
            } else {
                // Se o produto não estiver no mapa, informa que não foi encontrado e retorna 0.
                System.out.println("Produto não encontrado");
                return 0;
            }
        } catch (IllegalArgumentException e) {
            // Captura a exceção lançada e imprime a mensagem de erro.
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public List<String> getNomesProdutos() {
        return new ArrayList<>(produtos.keySet());
    }
}