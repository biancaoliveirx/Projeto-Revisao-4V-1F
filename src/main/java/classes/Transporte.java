package classes;

public class Transporte {

    private String tipoCaminhao;
    private double custo;

    public Transporte(String tipoCaminhao, double custo) {
        this.tipoCaminhao = tipoCaminhao;
        this.custo = custo;
    }

    public String getTipoCaminhao() {
        return tipoCaminhao;
    }

    public double getCusto() {
        return custo;


    }
}