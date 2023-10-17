package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// requisito dois.um
public class Distancia {
    //mapa para mapear nomes de cidades a índices
    private final Map<String, Integer> cidadeIndex = new HashMap<>();
    private String[] cidades = null;
    private int[][] distancias = null;

    //construtor
    public Distancia() {
        carregarDistancias();
    }

    //método para carregar as distâncias a partir de um arquivo CSV
    private void carregarDistancias() {
        String caminhoRelativo = "csv/distancia.csv";

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoRelativo))) {
            String linha;
            int row = 0;

            while ((linha = leitor.readLine()) != null) {
                String[] valores = linha.split(";");  // Divide a linha do arquivo CSV em valores

                if (cidades == null) {
                    cidades = valores;  // Se o array de cidades ainda não foi inicializado, a primeira linha contém os nomes das cidades
                    distancias = new int[cidades.length][cidades.length];  // Inicializa a matriz de distâncias com base no número de cidades
                } else {
                    for (int col = 0; col < valores.length; col++) {
                        distancias[row][col] = Integer.parseInt(valores[col]);  // Preenche a matriz de distâncias com os valores do arquivo
                    }
                    cidadeIndex.put(cidades[row], row);  // Mapeia o nome da cidade ao seu índice correspondente no mapa
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //caso dê erro de leitura do arquivo, imprime a pilha de erros e encerra o programa
            System.exit(1);
        }
    }

    //método que calcula a distância entre duas cidades
    public int calcularDistanciaEntreCidades(String cidadeOrigem, String cidadeDestino) {
        if (cidadeIndex.containsKey(cidadeOrigem) && cidadeIndex.containsKey(cidadeDestino)) {
            int indiceOrigem = cidadeIndex.get(cidadeOrigem);  //índice da cidade de origem.
            int indiceDestino = cidadeIndex.get(cidadeDestino);  //índice da cidade de destino.
            return distancias[indiceOrigem][indiceDestino];  //retorna a distância entre as duas cidades
        } else {
            return 0;
        }
    }

    public String[] getCidades() {
        return cidades;
    }
}