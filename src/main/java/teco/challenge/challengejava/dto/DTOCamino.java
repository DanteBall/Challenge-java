package teco.challenge.challengejava.dto;

import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public class DTOCamino {

    private Long idPuntoVentaA;

    private Long idPuntoVentaB;

    private BigDecimal costo;

    public Long getIdPuntoVentaA() {
        return idPuntoVentaA;
    }

    public void setIdPuntoVentaA(Long idPuntoVentaA) {
        this.idPuntoVentaA = idPuntoVentaA;
    }

    public Long getIdPuntoVentaB() {
        return idPuntoVentaB;
    }

    public void setIdPuntoVentaB(Long idPuntoVentaB) {
        this.idPuntoVentaB = idPuntoVentaB;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public DTOCamino(Long idPuntoVentaA, Long idPuntoVentaB, BigDecimal costo) {
        this.idPuntoVentaA = idPuntoVentaA;
        this.idPuntoVentaB = idPuntoVentaB;
        this.costo = costo;
    }
}
