package teco.challenge.challengejava.dominio;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "ACREDITACION")
public class Acreditacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PUNTO_VENTA", nullable = false)
    private PuntoDeVenta puntoDeVenta;

    @Column(name = "NOMBRE_PUNTO_VENTA", nullable = false)
    private String nombrePuntoVenta;

    @Column(name = "IMPORTE", nullable = false)
    private BigDecimal importe;

    @Column(name = "FECHA_RECEPCION", nullable = false)
    private LocalDate fechaRecepcion;

    @Column(name = "BORRADO", nullable = false)
    private Boolean borrado = false;

    public Acreditacion(Long id, PuntoDeVenta puntoDeVenta, String nombrePuntoVenta, BigDecimal importe, LocalDate fechaRecepcion) {
        this.id = id;
        this.puntoDeVenta = puntoDeVenta;
        this.nombrePuntoVenta = nombrePuntoVenta;
        this.importe = importe;
        this.fechaRecepcion = fechaRecepcion;
    }

    public Acreditacion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuntoDeVenta getPuntoDeVenta() {
        return puntoDeVenta;
    }

    public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
        this.puntoDeVenta = puntoDeVenta;
    }

    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}
