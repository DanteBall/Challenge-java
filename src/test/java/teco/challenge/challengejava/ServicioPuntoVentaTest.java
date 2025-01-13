package teco.challenge.challengejava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;
import teco.challenge.challengejava.servicios.ServicioPuntoVenta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServicioPuntoVentaTest {

    @Mock
    private RepoPuntoVenta repoPuntoDeVenta;

    @InjectMocks
    private ServicioPuntoVenta servicioPuntoDeVenta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePuntoDeVenta() {
        PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
        when(repoPuntoDeVenta.save(any(PuntoDeVenta.class))).thenReturn(puntoDeVenta);

        PuntoDeVenta result = servicioPuntoDeVenta.savePuntoVenta(puntoDeVenta);

        assertEquals(puntoDeVenta, result);
        verify(repoPuntoDeVenta, times(1)).save(puntoDeVenta);
    }

    @Test
    void testGetPuntoDeVenta() {
        PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
        when(repoPuntoDeVenta.findById(anyLong())).thenReturn(Optional.of(puntoDeVenta));

        PuntoDeVenta result = servicioPuntoDeVenta.getPuntoVenta(1L);

        assertEquals(puntoDeVenta, result);
        verify(repoPuntoDeVenta, times(1)).findById(1L);
    }

    @Test
    void testGetPuntoDeVentaNotFound() {
        when(repoPuntoDeVenta.findById(anyLong())).thenReturn(Optional.empty());

        PuntoDeVenta result = servicioPuntoDeVenta.getPuntoVenta(1L);

        assertNull(result);
        verify(repoPuntoDeVenta, times(1)).findById(1L);
    }

    @Test
    void testGetPuntosDeVenta() {
        PuntoDeVenta puntoDeVenta1 = new PuntoDeVenta();
        PuntoDeVenta puntoDeVenta2 = new PuntoDeVenta();
        List<PuntoDeVenta> puntosDeVenta = Arrays.asList(puntoDeVenta1, puntoDeVenta2);
        when(repoPuntoDeVenta.findAll()).thenReturn(puntosDeVenta);

        List<PuntoDeVenta> result = servicioPuntoDeVenta.getPuntosVenta();

        assertEquals(puntosDeVenta, result);
        verify(repoPuntoDeVenta, times(1)).findAll();
    }
}
