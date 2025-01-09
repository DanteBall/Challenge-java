package teco.challenge.challengejava.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "CAMINO",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_PUNTO_A", "ID_PUNTO_B"})
})
public class Camino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_PUNTO_A", nullable = false)
    private PuntoDeVenta puntoA;

    @ManyToOne
    @JoinColumn(name = "ID_PUNTO_B", nullable = false)
    private PuntoDeVenta puntoB;

    private BigDecimal costo;

    public Camino(PuntoDeVenta puntoA, PuntoDeVenta puntoB, BigDecimal costo) {
        if (puntoA.getId() > puntoB.getId()) {
            this.puntoA = puntoB;
            this.puntoB = puntoA;
        } else {
            this.puntoA = puntoA;
            this.puntoB = puntoB;
        }
        this.costo = costo;
    }

    public Camino() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuntoDeVenta getPuntoA() {
        return puntoA;
    }

    public void setPuntoA(PuntoDeVenta puntoA) {
        this.puntoA = puntoA;
    }

    public PuntoDeVenta getPuntoB() {
        return puntoB;
    }

    public void setPuntoB(PuntoDeVenta puntoB) {
        this.puntoB = puntoB;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}
