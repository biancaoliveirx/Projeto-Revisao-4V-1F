package classes;

public class Transporte {

    private String tipoCaminhao;
    private double custo;


    //Construtor recebe dois parametros: tipoCaminhao e custo
    public Transporte(String tipoCaminhao, double custo) {
        // O construtor inicializa os atributos da classe com os valores passados como parâmetro
        this.tipoCaminhao = tipoCaminhao;
        this.custo = custo;
    }

    //Método que retorna o tipo de caminhão
    public String getTipoCaminhao() {
        return tipoCaminhao;
    }

    //Método que retorna o tipo de caminhao
    public double getCusto() {
        return custo;


    }
}