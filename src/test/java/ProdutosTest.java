import classes.Produtos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutosTest {
    @Test
    public void getPesoProduto() {
        Produtos produtos = new Produtos();

        double pesoCelular = produtos.getPesoProduto("Celular");
        // assertEquals pega o que ele espera e compara com o resultado
        assertEquals(0.7, pesoCelular);

        double pesoGeladeira = produtos.getPesoProduto("Geladeira");
        assertEquals(50.0, pesoGeladeira);

        double pesoAirFryer = produtos.getPesoProduto("Air Fryer");
        assertEquals(3.5, pesoAirFryer);

        double pesoCadeira = produtos.getPesoProduto("Cadeira");
        assertEquals(5.0, pesoCadeira);

        double pesoLuminaria = produtos.getPesoProduto("Luminária");
        assertEquals(0.8, pesoLuminaria);

        double pesoLavadoraDeRoupa = produtos.getPesoProduto("Lavadora de roupa");
        assertEquals(15.0, pesoLavadoraDeRoupa);

        double pesoPlayStation5 = produtos.getPesoProduto("PlayStation 5");
        assertEquals(3.9, pesoPlayStation5);

        double pesoNintendoSwitch = produtos.getPesoProduto("Nintendo Switch");
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


