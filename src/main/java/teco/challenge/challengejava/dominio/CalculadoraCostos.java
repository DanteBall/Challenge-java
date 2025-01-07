package teco.challenge.challengejava.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Setter
@NoArgsConstructor
public class CalculadoraCostos {

    private List<camino> caminos;

    public BigDecimal calcularCosto(PuntoDeVenta puntoA, PuntoDeVenta puntoB) {
        // Mapa de adyacencias para representar el grafo
        Map<PuntoDeVenta, List<camino>> grafo = construirGrafo();

        // Mapa para almacenar las distancias mínimas desde puntoA
        Map<PuntoDeVenta, BigDecimal> distancias = new HashMap<>();
        Set<PuntoDeVenta> visitados = new HashSet<>();
        PriorityQueue<PuntoDeVenta> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

        // Inicializar distancias con un valor muy alto
        for (camino c : caminos) {
            distancias.putIfAbsent(c.puntoA, BigDecimal.valueOf(Double.MAX_VALUE));
            distancias.putIfAbsent(c.puntoB, BigDecimal.valueOf(Double.MAX_VALUE));
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
            List<camino> vecinos = grafo.getOrDefault(actual, new ArrayList<>());
            for (camino c : vecinos) {
                PuntoDeVenta vecino = c.puntoA.equals(actual) ? c.puntoB : c.puntoA;
                if (!visitados.contains(vecino)) {
                    BigDecimal nuevoCosto = distancias.get(actual).add(c.costo);
                    if (nuevoCosto.compareTo(distancias.get(vecino)) < 0) {
                        distancias.put(vecino, nuevoCosto);
                        cola.add(vecino);
                    }
                }
            }
        }

        // Retornar el costo mínimo al punto objetivo
        return distancias.getOrDefault(puntoB, BigDecimal.valueOf(-1)); // -1 indica que no hay camino
    }

    private Map<PuntoDeVenta, List<camino>> construirGrafo() {
        Map<PuntoDeVenta, List<camino>> grafo = new HashMap<>();
        for (camino c : caminos) {
            grafo.computeIfAbsent(c.puntoA, k -> new ArrayList<>()).add(c);
            grafo.computeIfAbsent(c.puntoB, k -> new ArrayList<>()).add(c);
        }
        return grafo;
    }

}
