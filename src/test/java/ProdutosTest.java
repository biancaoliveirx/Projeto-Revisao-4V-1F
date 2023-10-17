import classes.Produtos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutosTest {
    @Test
    public void getPesoProduto() {
        Produtos produtos = new Produtos();

        double pesoCelular = produtos.getPesoProduto("CELULAR");
        // assertEquals pega o que ele espera e compara com o resultado
        assertEquals(0.7, pesoCelular);

        double pesoGeladeira = produtos.getPesoProduto("GELADEIRA");
        assertEquals(50.0, pesoGeladeira);

        double pesoAirFryer = produtos.getPesoProduto("AIR FRYER");
        assertEquals(3.5, pesoAirFryer);

        double pesoCadeira = produtos.getPesoProduto("CADEIRA");
        assertEquals(5.0, pesoCadeira);

        double pesoLuminaria = produtos.getPesoProduto("LUMINARIA");
        assertEquals(0.8, pesoLuminaria);

        double pesoLavadoraDeRoupa = produtos.getPesoProduto("LAVADORA DE ROUPA");
        assertEquals(15.0, pesoLavadoraDeRoupa);

        double pesoPlayStation5 = produtos.getPesoProduto("PLAYSTATION 5");
        assertEquals(3.9, pesoPlayStation5);

        double pesoNintendoSwitch = produtos.getPesoProduto("NINTENDO SWITCH");
        assertEquals(0.3, pesoNintendoSwitch);
    }

    // aqui testa um produto null
    @Test
    public void getPesoProdutoExcecaoNull() {
        Produtos produtos = new Produtos();
        try {
            double pesoProdutoNull = produtos.getPesoProduto(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("Nome do produto não pode ser null", exception.getMessage());
        }

    }

    // aqui testa um produto que não existe
    @Test
    public void getPesoProdutoExcecao() {
        Produtos produtos = new Produtos();
        double pesoProdutoInvalido = produtos.getPesoProduto("Garrafa");
        assertEquals(0.0, pesoProdutoInvalido);
    }

}


