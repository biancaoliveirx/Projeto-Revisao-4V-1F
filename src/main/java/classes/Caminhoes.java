package classes;

public class Caminhoes extends Distancia {

    private double[] precoPorKm = {5.83, 13.42, 29.21};
    private double[] capacidadeMaximaTonelada = {1, 4, 10};

    public Caminhoes() {
    }

    public double getPrecoPorKm(String tipoCaminhao) {
        try {
            // pega preço por km conforme o tipo do caminhão
            switch (tipoCaminhao) {
                case "pequeno":
                    return this.precoPorKm[0];
                case "medio":
                    return this.precoPorKm[1];
                case "grande":
                    return this.precoPorKm[2];
                default:
                    // mensagem de erro do try catch
                    throw new IllegalArgumentException("Modalidade de caminhão inválida");

            }
            // chamando o throw new
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public double getCapacidadeMaximaTonelada(String tipoCapacidade) {
        try {
            // pega a capacidade por tonelada conforme o tipo do caminhão
            switch (tipoCapacidade) {
                case "pequeno":
                    return this.capacidadeMaximaTonelada[0];
                case "medio":
                    return this.capacidadeMaximaTonelada[1];
                case "grande":
                    return this.capacidadeMaximaTonelada[2];
                default:
                    // mensagem de erro do try catch
                    throw new IllegalArgumentException("Modalidade de caminhão inválida");

            }
            // chamando o throw new
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }

}