package teco.challenge.challengejava.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
public class DTOAcreditacion {

    @NotNull
    private Long idPuntoVenta;

    @NotNull
    private BigDecimal importe;

    @NotNull
    @JsonFormat(pattern = "d-M-yyyy")
    private LocalDate fechaRecepcion;

    public DTOAcreditacion(Long idPuntoVenta, BigDecimal importe, LocalDate fechaRecepcion) {

        this.idPuntoVenta = idPuntoVenta;
        this.importe = importe;
        this.fechaRecepcion = fechaRecepcion;
    }

    public DTOAcreditacion() {
    }

    public Long getIdPuntoVenta() {
        return idPuntoVenta;
    }

    public void setIdPuntoVenta(Long idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }


    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
