package classes;

import java.util.HashMap;
import java.util.Map;

public class Caminhoes {

    private Map<String, Double> precoPorKm = new HashMap<>();

    public Caminhoes() {
        // Inicialize os preços por km para cada tipo de caminhão
        precoPorKm.put("pequeno", 5.83);
        precoPorKm.put("medio", 13.42);
        precoPorKm.put("grande", 29.21);
    }

    public double getPrecoPorKm(String tipoCaminhao) {
        // Verifique se o tipo de caminhão existe no mapa
        if (precoPorKm.containsKey(tipoCaminhao)) {
            return precoPorKm.get(tipoCaminhao);
        } else {
            System.out.println("Modalidade de caminhão inválida.");
            return 0;
        }
    }
}
