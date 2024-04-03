package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class CarpoolingSolver {

    public CarpoolingSolver(List<Driver> drivers, List<Passenger> passengers,Map<String, List<Person>> destinationMap){
        this.solve(drivers,passengers,destinationMap);
    }
    public static Map<Driver, Passenger> solveCarpooling(List<Driver> drivers, List<Passenger> passengers, Map<String, List<Person>> destinationMap) {
        Map<Driver, Passenger> matches = new HashMap<>();


        Graph graph = buildGraph(destinationMap);


        for (Driver driver : drivers) {
            Passenger bestMatch = findBestMatch(driver, passengers, graph);
            if (bestMatch != null) {
                matches.put(driver, bestMatch);
                passengers.remove(bestMatch);
            }
        }

        return matches;
    }

    private static Graph buildGraph(Map<String, List<Person>> destinationMap) {
        Graph graph= new SimpleGraph<>(DefaultEdge.class);

        for (String destination : destinationMap.keySet()) {
            graph.addVertex(destination);
            List<Person> people = destinationMap.get(destination);
            for (Person person : people) {
                if (person instanceof Passenger) {
                    Passenger passenger = (Passenger) person;
                    if(!graph.containsVertex(passenger.getDestination()))
                        graph.addVertex(passenger.getDestination());

                    if(!graph.containsVertex(destination))
                        graph.addVertex(destination);
                    graph.addEdge(destination, passenger.getDestination());
                }
            }
        }

        return graph;
    }


    private static Passenger findBestMatch(Driver driver, List<Passenger> passengers, Graph graph) {

        for (String destination : driver.getAlldestinations()) {
            for (Passenger passenger : passengers) {
                if (passenger.getDestination().equals(destination)) {
                    return passenger;
                }
            }
        }
        return null;
    }
    public void solve(List<Driver> drivers, List<Passenger> passengers,Map<String, List<Person>> destinationMap ) {

        Map<Driver, Passenger> matches = solveCarpooling(drivers, passengers, destinationMap);

        for (Map.Entry<Driver, Passenger> entry : matches.entrySet()) {
            System.out.println(entry.getKey().toString() + " will pick up " + entry.getValue().getName());
        }
    }
}
