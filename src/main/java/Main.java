import classes.Distancia;
import classes.Produtos;

public class Main {

    public static void main(String[] args) {

        Distancia distancia = new Distancia();

        System.out.println(distancia.calculaDistancia("CAMPO GRANDE", "RIO DE JANEIRO"));

        Produtos produtos = new Produtos();
        System.out.println(produtos.getPesoProduto("Celular"));
        System.out.println(produtos.getPesoProduto(""));
        System.out.println(produtos.getPesoProduto("termica"));

    }

}
