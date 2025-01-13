package teco.challenge.challengejava.dominio;

import java.math.BigDecimal;
import java.util.*;

public class CalculadoraCostos {

    private List<Camino> caminos;

    public ResultadoCosto calcularCosto(PuntoDeVenta puntoA, PuntoDeVenta puntoB) {
        // Mapa de adyacencias para representar el grafo
        Map<PuntoDeVenta, List<Camino>> grafo = construirGrafo();

        // Mapa para almacenar las distancias mínimas desde puntoA
        Map<PuntoDeVenta, BigDecimal> distancias = new HashMap<>();
        Map<PuntoDeVenta, PuntoDeVenta> previos = new HashMap<>();
        Set<PuntoDeVenta> visitados = new HashSet<>();
        PriorityQueue<PuntoDeVenta> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

        // Inicializar distancias con un valor muy alto
        for (Camino c : caminos) {
            distancias.putIfAbsent(c.getPuntoA(), BigDecimal.valueOf(Double.MAX_VALUE));
            distancias.putIfAbsent(c.getPuntoB(), BigDecimal.valueOf(Double.MAX_VALUE));
        }

        // Inicializar puntoA con distancia 0
        distancias.put(puntoA, BigDecimal.ZERO);
        cola.add(puntoA);

        while (!cola.isEmpty()) {
            PuntoDeVenta actual = cola.poll();
            if (visitados.contains(actual)) {
                continue;
            }
            visitados.add(actual);

            // Explorar vecinos
            List<Camino> vecinos = grafo.getOrDefault(actual, new ArrayList<>());
            for (Camino c : vecinos) {
                PuntoDeVenta vecino = c.getPuntoA().equals(actual) ? c.getPuntoB() : c.getPuntoA();
                if (!visitados.contains(vecino)) {
                    BigDecimal nuevoCosto = distancias.get(actual).add(c.getCosto());
                    if (nuevoCosto.compareTo(distancias.get(vecino)) < 0) {
                        distancias.put(vecino, nuevoCosto);
                        previos.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        // Reconstruir el camino
        List<PuntoDeVenta> camino = new ArrayList<>();
        for (PuntoDeVenta at = puntoB; at != null; at = previos.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);

        // Verificar si se encontró un camino válido
        if (camino.isEmpty() || !camino.get(0).equals(puntoA)) {
            camino = new ArrayList<>();
        }

        // Retornar el costo mínimo y el camino
        BigDecimal costo = distancias.getOrDefault(puntoB, BigDecimal.valueOf(-1)); // -1 indica que no hay camino
        return new ResultadoCosto(costo, camino);
    }

    private Map<PuntoDeVenta, List<Camino>> construirGrafo() {
        Map<PuntoDeVenta, List<Camino>> grafo = new HashMap<>();
        for (Camino c : caminos) {
            grafo.computeIfAbsent(c.getPuntoA(), k -> new ArrayList<>()).add(c);
            grafo.computeIfAbsent(c.getPuntoB(), k -> new ArrayList<>()).add(c);
        }
        return grafo;
    }

    public void setCaminos(List<Camino> caminos) {
        this.caminos = new ArrayList<>(caminos);
    }

}