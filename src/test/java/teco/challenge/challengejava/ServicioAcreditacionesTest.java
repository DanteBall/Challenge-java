package teco.challenge.challengejava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import teco.challenge.challengejava.dominio.Acreditacion;
import teco.challenge.challengejava.repositorios.RepoAcreditacion;
import teco.challenge.challengejava.servicios.ServicioAcreditaciones;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServicioAcreditacionesTest {

    @Mock
    private RepoAcreditacion repoAcreditacion;

    @InjectMocks
    private ServicioAcreditaciones servicioAcreditaciones;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAcreditacion() {
        Acreditacion acreditacion = new Acreditacion();
        when(repoAcreditacion.save(any(Acreditacion.class))).thenReturn(acreditacion);

        Acreditacion result = servicioAcreditaciones.saveAcreditacion(acreditacion);

        assertEquals(acreditacion, result);
        verify(repoAcreditacion, times(1)).save(acreditacion);
    }

    @Test
    void testGetAcreditacion() {
        Acreditacion acreditacion = new Acreditacion();
        when(repoAcreditacion.findById(anyLong())).thenReturn(Optional.of(acreditacion));

        Acreditacion result = servicioAcreditaciones.getAcreditacion(1L);

        assertEquals(acreditacion, result);
        verify(repoAcreditacion, times(1)).findById(1L);
    }

    @Test
    void testGetAcreditacionNotFound() {
        when(repoAcreditacion.findById(anyLong())).thenReturn(Optional.empty());

        Acreditacion result = servicioAcreditaciones.getAcreditacion(1L);

        assertNull(result);
        verify(repoAcreditacion, times(1)).findById(1L);
    }

    @Test
    void testGetAcreditaciones() {
        Acreditacion acreditacion1 = new Acreditacion();
        Acreditacion acreditacion2 = new Acreditacion();
        List<Acreditacion> acreditaciones = Arrays.asList(acreditacion1, acreditacion2);
        when(repoAcreditacion.findAll()).thenReturn(acreditaciones);

        List<Acreditacion> result = servicioAcreditaciones.getAcreditaciones();

        assertEquals(acreditaciones, result);
        verify(repoAcreditacion, times(1)).findAll();
    }

    @Test
    void testGetAcreditacionesPorPuntoVenta() {
        Acreditacion acreditacion1 = new Acreditacion();
        Acreditacion acreditacion2 = new Acreditacion();
        List<Acreditacion> acreditaciones = Arrays.asList(acreditacion1, acreditacion2);
        when(repoAcreditacion.findByPuntoDeVenta(anyLong())).thenReturn(acreditaciones);

        List<Acreditacion> result = servicioAcreditaciones.getAcreditacionesPorPuntoVenta(1L);

        assertEquals(acreditaciones, result);
        verify(repoAcreditacion, times(1)).findByPuntoDeVenta(1L);
    }
}
