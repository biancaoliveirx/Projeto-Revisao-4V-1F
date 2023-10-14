package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Distancia {

    private Map<String, Integer> cidadeIndex = new HashMap<>();
    private String[] cidades = null;
    private int[][] distancias = null;

    private String diretorioAtual = System.getProperty("user.dir");
    private String caminhoRelativo = diretorioAtual + "/csv/distancia.csv";

    public Distancia() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(this.caminhoRelativo))) {
            String linha;
            int row = 0;
            while ((linha = leitor.readLine()) != null) {
                String[] valores = linha.split(";");
                if (this.cidades == null) {
                    this.cidades = valores;
                    this.distancias = new int[this.cidades.length][this.cidades.length];
                } else {
                    for (int col = 0; col < valores.length; col++) {
                        this.distancias[row][col] = Integer.parseInt(valores[col]);
                    }
                    this.cidadeIndex.put(this.cidades[row], row);
                    row++;
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int calculaDistancia(String cidade1, String cidade2) {
        if (this.cidadeIndex.containsKey(cidade1) && this.cidadeIndex.containsKey(cidade2)) {
            int distancia = distancias[this.cidadeIndex.get(cidade1)][this.cidadeIndex.get(cidade2)];
            return distancia;
        } else {
            return 0;
        }
    }

    public String[] getCidades(){
        return this.cidades;
    }
}
