package teco.challenge.challengejava.cache;

import teco.challenge.challengejava.dominio.Camino;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CacheCaminos {
    private static final ConcurrentHashMap<Long, Camino> cache = new ConcurrentHashMap<>();

    public static Camino get(Long id) {
        return cache.get(id);
    }

    public static void put(Long id, Camino camino) {
        cache.put(id, camino);
    }

    public static void remove(Long id) {
        cache.remove(id);
    }

    public static List<Camino> getAll() {
        return List.copyOf(cache.values());
    }

    public static void clear() {
        cache.clear();
    }
}