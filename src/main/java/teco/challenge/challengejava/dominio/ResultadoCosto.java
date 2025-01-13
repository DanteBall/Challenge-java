package teco.challenge.challengejava.dominio;

import java.math.BigDecimal;
import java.util.List;

public class ResultadoCosto {
    private BigDecimal costo;
    private List<PuntoDeVenta> camino;

    public ResultadoCosto(BigDecimal costo, List<PuntoDeVenta> camino) {
        this.costo = costo;
        this.camino = camino;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public List<PuntoDeVenta> getCamino() {
        return camino;
    }
}
