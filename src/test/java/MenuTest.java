import classes.Menu;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;


public class MenuTest {
    @Test
    public void exibirMenu() {
        Menu menu = new Menu();
        boolean resultado = menu.exibirMenu();
        assertTrue(resultado);
    }

    @Test
    public void listarProdutos() {
        Menu menu = new Menu();
        boolean resultado = menu.listarProdutos();
        assertTrue(resultado);
    }

    @Test
    public void atualizarCustoTotal() {
        Menu menu = new Menu();
        boolean resultado = menu.atualizarCustoTotal();
        assertTrue(resultado);
    }

    @Test
    public void atualizarTotalDeItensTransportados() {
        Menu menu = new Menu();
        boolean resultado = menu.atualizarTotalDeItensTransportados("Celular", 10);
        assertTrue(resultado);
    }

    @Test
    public void atualizarCustoTotalModalidadePorTransporte() {
        Menu menu = new Menu();
        boolean resultado = menu.atualizarCustoTotalPorModalidadeDeTransporte("pequeno", 10, 1000);
        assertTrue(resultado);
    }

    @Test
    public void calculaValorViagem() {
        Menu menu = new Menu();
        boolean resultado = menu.calculaValorDaViagem("porto alegre", "belem", "pequeno");
        assertTrue(resultado);
    }
    @Test
    public void relatorioTransportesCadastrados() {
        Menu menu = new Menu();
        boolean resultado = menu.relatorioTransportesCadastrados();
        assertTrue(resultado);
    }
}