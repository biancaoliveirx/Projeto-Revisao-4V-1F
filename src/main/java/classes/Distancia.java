package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Distancia {

    // Mapa para armazenar o índice de cada cidade no array de cidades e matriz de distâncias.
    private Map<String, Integer> cidadeIndex = new HashMap<>();
    // Array para armazenar os nomes das cidades.
    private String[] cidades = null;
    // Matriz 2D para armazenar as distâncias entre as cidades.
    private int[][] distancias = null;

    private String diretorioAtual = System.getProperty("user.dir");
    private String caminhoRelativo = diretorioAtual + "/csv/distancia.csv";

    // Construtor da classe Distancia.
    public Distancia() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(this.caminhoRelativo))) {
            String linha;
            int row = 0;

            // Lê o arquivo linha por linha.
            while ((linha = leitor.readLine()) != null) {
                String[] valores = linha.split(";");

                // A primeira linha do arquivo contém os nomes das cidades.
                if (this.cidades == null) {
                    this.cidades = valores;
                    // Inicializa a matriz de distâncias baseada no número de cidades.
                    this.distancias = new int[this.cidades.length][this.cidades.length];
                } else {
                    // Preenche a matriz de distâncias com valores do arquivo.
                    for (int col = 0; col < valores.length; col++) {
                        this.distancias[row][col] = Integer.parseInt(valores[col]);
                    }
                    // Adiciona a cidade e seu índice correspondente no mapa.
                    this.cidadeIndex.put(this.cidades[row], row);
                    row++;
                }
            }
        } catch (IOException e) {
            // Em caso de qualquer erro de leitura/arquivo, imprime a pilha de erros e encerra o programa.
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Método para calcular a distância entre duas cidades.
    public int calculaDistancia(String cidade1, String cidade2) {
        // Verifica se ambas as cidades estão presentes no mapa.
        if (this.cidadeIndex.containsKey(cidade1) && this.cidadeIndex.containsKey(cidade2)) {
            // Retorna a distância entre as duas cidades.
            int distancia = distancias[this.cidadeIndex.get(cidade1)][this.cidadeIndex.get(cidade2)];
            return distancia;
        } else {
            // Se uma das cidades ou ambas não estiverem presentes, retorna 0.
            return 0;
        }
    }

    public String[] getCidades(){
        return this.cidades;
    }
}
