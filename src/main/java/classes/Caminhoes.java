package classes;

import java.util.HashMap;
import java.util.Map;
public class Caminhoes {

    private Map<String, Double> precoPorKm = new HashMap<>();
    private Map<String, Double> capacidadeToneladas = new HashMap<>();
    private String[] tipoCaminhoes = {"pequeno", "medio", "grande"};

    public Caminhoes() {
        //inicia os preços por km para cada tipo de caminhão
        precoPorKm.put("pequeno", 5.83);
        precoPorKm.put("medio", 13.42);
        precoPorKm.put("grande", 29.21);

        //inicia as capacidades máximas em toneladas para cada tipo de caminhão
        capacidadeToneladas.put("pequeno", 1.0);
        capacidadeToneladas.put("medio", 4.0);
        capacidadeToneladas.put("grande", 10.0);
    }

    public String[] getTipoCaminhoes() {
        return this.tipoCaminhoes;
    }

    public double getPrecoPorKm(String tipoCaminhao) {
        //verifica se o tipo de caminhão existe no mapa de preços por km
        if (precoPorKm.containsKey(tipoCaminhao)) {
            return precoPorKm.get(tipoCaminhao);
        } else {
            System.out.println("Modalidade de caminhão inválida.");
            return 0;
        }
    }

    public double getCapacidadeToneladas(String tipoCaminhao) {
        //verifica se o tipo de caminhão existe no mapa de capacidades máximas em toneladas.
        if (capacidadeToneladas.containsKey(tipoCaminhao)) {
            return capacidadeToneladas.get(tipoCaminhao);
        } else {
            System.out.println("Modalidade de caminhão inválida.");
            return 0;
        }
    }
}
