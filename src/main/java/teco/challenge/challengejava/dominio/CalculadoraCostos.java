package teco.challenge.challengejava.dominio;

import java.math.BigDecimal;
import java.util.*;

public class CalculadoraCostos {
    private List<Camino> caminos;

    public ResultadoCosto calcularCosto(PuntoDeVenta puntoA, PuntoDeVenta puntoB) {
        if (puntoA == null || puntoB == null) {
            throw new IllegalArgumentException("PuntoA and PuntoB must not be null");
        }

        Map<PuntoDeVenta, BigDecimal> distancias = new HashMap<>();
        Map<PuntoDeVenta, PuntoDeVenta> predecesores = new HashMap<>();
        PriorityQueue<PuntoDeVenta> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));

        distancias.put(puntoA, BigDecimal.ZERO);
        cola.add(puntoA);

        while (!cola.isEmpty()) {
            PuntoDeVenta actual = cola.poll();
            if (actual.equals(puntoB)) {
                break;
            }

            List<Camino> adyacentes = construirGrafo().get(actual);
            if (adyacentes != null) {
                for (Camino camino : adyacentes) {
                    PuntoDeVenta vecino = camino.getPuntoA().equals(actual) ? camino.getPuntoB() : camino.getPuntoA();
                    BigDecimal nuevaDistancia = distancias.get(actual).add(camino.getCosto());

                    if (distancias.getOrDefault(vecino, BigDecimal.valueOf(Double.MAX_VALUE)).compareTo(nuevaDistancia) > 0) {
                        distancias.put(vecino, nuevaDistancia);
                        predecesores.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        List<PuntoDeVenta> camino = new ArrayList<>();
        for (PuntoDeVenta at = puntoB; at != null; at = predecesores.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);

        if (camino.isEmpty() || !camino.get(0).equals(puntoA)) {
            camino = new ArrayList<>();
        }

        BigDecimal costo = distancias.getOrDefault(puntoB, BigDecimal.valueOf(-1));
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