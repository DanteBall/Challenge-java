package teco.challenge.challengejava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dominio.ResultadoCosto;
import teco.challenge.challengejava.repositorios.RepoCamino;
import teco.challenge.challengejava.servicios.ServicioCamino;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServicioCaminoTest {

    @Mock
    private RepoCamino repoCamino;

    @InjectMocks
    private ServicioCamino servicioCamino;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCaminos() {
        Camino camino1 = new Camino();
        Camino camino2 = new Camino();
        List<Camino> caminos = Arrays.asList(camino1, camino2);
        when(repoCamino.findAll()).thenReturn(caminos);

        List<Camino> result = servicioCamino.getCaminos();

        assertEquals(caminos, result);
        verify(repoCamino, times(1)).findAll();
    }

    @Test
    void testGetCamino() {
        Camino camino = new Camino();
        when(repoCamino.findById(anyLong())).thenReturn(Optional.of(camino));

        Camino result = servicioCamino.getCamino(1L);

        assertEquals(camino, result);
        verify(repoCamino, times(1)).findById(1L);
    }

    @Test
    void testSaveCamino() {
        Camino camino = new Camino();
        when(repoCamino.save(any(Camino.class))).thenReturn(camino);

        Camino result = servicioCamino.saveCamino(camino);

        assertEquals(camino, result);
        verify(repoCamino, times(1)).save(camino);
    }
}