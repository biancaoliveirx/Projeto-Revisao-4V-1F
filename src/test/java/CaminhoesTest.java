import classes.Caminhoes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaminhoesTest {
    @Test
    public void getPrecoPorKm() {
        // instanciei a classe caminhoes
        Caminhoes caminhoes = new Caminhoes();

        double precoCaminhaoPequeno = caminhoes.getPrecoPorKm("pequeno");
        // aqui no assertEquals é onde ele verifica o valor esperado
        assertEquals(5.83, precoCaminhaoPequeno);

        double precoCaminhaoMedio = caminhoes.getPrecoPorKm("medio");
        assertEquals(13.42, precoCaminhaoMedio);

        double precoCaminhaoGrande = caminhoes.getPrecoPorKm("grande");
        assertEquals(29.21, precoCaminhaoGrande);

    }

    @Test
    // aqui fiz o teste da exceção de entrada inválida
    public void getPrecoPorKmExcecao() {
        Caminhoes caminhoes = new Caminhoes();
        try {
            double precoCaminhaoInvalido = caminhoes.getPrecoPorKm("gigante");
        } catch (IllegalArgumentException exception) {
            assertEquals("Modalidade de caminhão inválida", exception.getMessage());
        }
    }

    @Test
    public void getCapacidadeMaximaTonelada() {
        Caminhoes caminhoes = new Caminhoes();
        double capacidadeCaminhaoPequeno = caminhoes.getCapacidadeMaximaTonelada("pequeno");
        assertEquals(1, capacidadeCaminhaoPequeno);

        double capacidadeCaminhaoMedio = caminhoes.getCapacidadeMaximaTonelada("medio");
        assertEquals(4, capacidadeCaminhaoMedio);

        double capacidadeCaminhaoGrande = caminhoes.getCapacidadeMaximaTonelada("grande");
        assertEquals(10, capacidadeCaminhaoGrande);
    }

    @Test
    public void getCapacidadeMaximaToneladaExcecao() {
        Caminhoes caminhoes = new Caminhoes();
        try {
            double precoCaminhaoInvalido = caminhoes.getCapacidadeMaximaTonelada("gigante");
        } catch (IllegalArgumentException exception) {
            assertEquals("Modalidade de caminhão inválida", exception.getMessage());

        }
    }
}


