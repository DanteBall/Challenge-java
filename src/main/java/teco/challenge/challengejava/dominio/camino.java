package teco.challenge.challengejava.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class camino {

    PuntoDeVenta puntoA;

    PuntoDeVenta puntoB;

    BigDecimal costo;

}
