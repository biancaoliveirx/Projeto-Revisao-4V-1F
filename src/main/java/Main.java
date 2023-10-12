import classes.Caminhoes;
import classes.Distancia;

public class Main {

    public static void main(String[] args) {

        Distancia distancia = new Distancia();

//        System.out.println(distancia.calculaDistancia("CAMPO GRANDE", "RIO DE JANEIRO"));

        Caminhoes caminhoes = new Caminhoes();
        System.out.println(caminhoes.getCapacidadeMaximaTonelada("pequeno"));
        System.out.println(caminhoes.getCapacidadeMaximaTonelada("medio"));
        System.out.println(caminhoes.getCapacidadeMaximaTonelada("grande"));
        System.out.println(caminhoes.getCapacidadeMaximaTonelada("a"));

    }

}
