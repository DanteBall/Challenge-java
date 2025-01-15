package teco.challenge.challengejava.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class Puntos {

    @NotNull
    private Long puntoA;
    @NotNull
    private Long puntoB;

    public Puntos(Long puntoA, Long puntoB) {
        this.puntoA = puntoA;
        this.puntoB = puntoB;
    }

    public @NotNull Long getPuntoA() {
        return puntoA;
    }

    public void setPuntoA(@NotNull Long puntoA) {
        this.puntoA = puntoA;
    }

    public @NotNull Long getPuntoB() {
        return puntoB;
    }

    public void setPuntoB(@NotNull Long puntoB) {
        this.puntoB = puntoB;
    }
}
