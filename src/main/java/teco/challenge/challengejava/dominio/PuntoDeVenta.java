package teco.challenge.challengejava.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "PUNTO_DE_VENTA")
public class PuntoDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "BORRADO", nullable = false)
    private Boolean borrado = false;

    public PuntoDeVenta(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PuntoDeVenta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}
