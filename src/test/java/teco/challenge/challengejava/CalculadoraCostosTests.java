package teco.challenge.challengejava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import teco.challenge.challengejava.dominio.CalculadoraCostos;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dominio.Camino;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraCostosTests {

    private CalculadoraCostos calculadoraCostos;
    private PuntoDeVenta puntoA;
    private PuntoDeVenta puntoB;
    private PuntoDeVenta puntoC;

    @BeforeEach
    public void setUp() {
        calculadoraCostos = new CalculadoraCostos();
        puntoA = new PuntoDeVenta(1L, "Punto A");
        puntoB = new PuntoDeVenta(2L, "Punto B");
        puntoC = new PuntoDeVenta(3L, "Punto C");

        Camino caminoAB = new Camino(puntoA, puntoB, new BigDecimal("10"));
        Camino caminoBC = new Camino(puntoB, puntoC, new BigDecimal("15"));
        Camino caminoAC = new Camino(puntoA, puntoC, new BigDecimal("30"));

        List<Camino> caminos = new ArrayList<>();
        caminos.add(caminoAB);
        caminos.add(caminoBC);
        caminos.add(caminoAC);

        calculadoraCostos.setCaminos(caminos);
    }

    @Test
    public void testCalcularCostoDirecto() {
        BigDecimal costo = calculadoraCostos.calcularCosto(puntoA, puntoB);
        assertEquals(new BigDecimal("10"), costo);
    }

    @Test
    public void testCalcularCostoIndirecto() {
        BigDecimal costo = calculadoraCostos.calcularCosto(puntoA, puntoC);
        assertEquals(new BigDecimal("25"), costo); // A -> B -> C
    }

    @Test
    public void testCalcularCostoSinCamino() {
        PuntoDeVenta puntoD = new PuntoDeVenta(4L, "Punto D");
        BigDecimal costo = calculadoraCostos.calcularCosto(puntoA, puntoD);
        assertEquals(new BigDecimal("-1"), costo); // No hay camino
    }

}
