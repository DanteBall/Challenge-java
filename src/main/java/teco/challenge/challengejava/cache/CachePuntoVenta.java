package teco.challenge.challengejava.cache;

import teco.challenge.challengejava.dominio.PuntoDeVenta;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CachePuntoVenta {
    private static final ConcurrentHashMap<Long, PuntoDeVenta> cache = new ConcurrentHashMap<>();

    public static PuntoDeVenta get(Long id) {
        return cache.get(id);
    }

    public static void put(Long id, PuntoDeVenta puntoDeVenta) {
        cache.put(id, puntoDeVenta);
    }

    public static void remove(Long id) {
        cache.remove(id);
    }

    public static List<PuntoDeVenta> getAll() {
        return List.copyOf(cache.values());
    }

    public static void clear() {
        cache.clear();
    }
}