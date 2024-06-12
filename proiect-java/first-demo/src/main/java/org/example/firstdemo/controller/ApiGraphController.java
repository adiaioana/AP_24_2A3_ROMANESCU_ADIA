package org.example.firstdemo.controller;
import org.example.firstdemo.controller.problemsolving.Graph;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;


@RestController
public class ApiGraphController {
    @Autowired
    private RestTemplate restTemplate;
        private final Map<String, Graph> graphStore = new HashMap<>();
        @PostMapping("/api/graph")
        public String postGraph(@RequestParam Graph graph){
            System.out.println("Received data: " + graph);
            return "S-a postat";
        }

        @GetMapping("/api/graph")
        public Graph showGraph() {
            Graph graph;
            ResponseEntity<Graph> response = restTemplate.getForEntity("http://localhost:8080/api/location", Graph.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                graph=response.getBody();
            } else {
                graph=null;
            }
            if (graph == null) {
                List<Graph.Node> nodes = new ArrayList<>();

                nodes.add(new Graph.Node("n1", "Node 1", 0, 0, 1, "#ff0000"));
                nodes.add(new Graph.Node("n2", "Node 2", 1, 1, 1, "#00ff00"));
                nodes.add(new Graph.Node("n3", "Node 3", 2, 0, 1, "#0000ff"));


                List<Graph.Edge> edges = new ArrayList<>();
                edges.add(new Graph.Edge("e1", "n1", "n2", "#cccccc"));
                edges.add(new Graph.Edge("e2", "n2", "n3", "#cccccc"));

                graph= new Graph(nodes,edges);
            }

            System.out.println(graph);

            return graph;
        }
    }
