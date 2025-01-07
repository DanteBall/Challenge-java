package teco.challenge.challengejava.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Acreditacion {

    private Long id;

    private PuntoDeVenta puntoDeVenta;

    private String nombrePuntoVenta;

    private BigDecimal importe;

    private LocalDate fechaRecepcion;

}
