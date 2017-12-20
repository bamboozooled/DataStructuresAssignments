import java.util.Iterator;

import java.util.*;
import net.datastructures.Graph;
import net.datastructures.Vertex;
import net.datastructures.Edge;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Map;
import net.datastructures.*;
public class ParisMetro{
    static WeightGraph sGaph;
    static Graph<String,String> sGraph;
    static Hashtable<String,Vertex> vertices;
    static Hashtable<String,Boolean> visited;
    static Map<Vertex<String>, String> cloud;
    static Map<Vertex<String>, Entry<String,Vertex<String>>> tokens;
    static AdaptablePriorityQueue<String, Vertex<String>> pq;
    static Map<Vertex<String>, String> reachable = new ProbeHashMap<>();
    static Map<Vertex<String>,String> actual = new ProbeHashMap<>();
    static Graph<String,Integer> intGraph;
    static String theone;
    public static void main(String[] args){
        try{
            vertices = new Hashtable<String, Vertex>();
            cloud = new ProbeHashMap<>();
            tokens = new ProbeHashMap<>(); //Maps from vertex to its pq locator
            pq = new HeapAdaptablePriorityQueue<>(); // vertices as elements cloud.get(v) as key
            if (args.length == 1){
                sGaph = new WeightGraph("metro.txt");
                sGraph = sGaph.getGraph();
                System.out.println("Input: ");
                System.out.println("N1 = " + args[0]);
                DFSMain(args[0]);
            }

            else if (args.length == 2){
                WeightGraph second = new WeightGraph("metro.txt", false);
                intGraph = second.getGraph2();   
                //Map<Vertex<String>,Integer> result =
                theone = args[1]; 
                shortestpath(intGraph,getMainV2(args[0]), getMainV2(args[1]));
            }

            else if (args.length == 3){
                System.out.println("Sorry question 2(iii) not yet implemented. We are deeply sorry :( .");
            }
		}
		catch (RuntimeException except){
            System.out.println(except.getClass().getName());
        }

        catch (Exception exception){

        }
    }

    public static void DFSMain(String start) {
        System.out.println("Performing Depth First Search");
        System.out.println("Output: ");
        System.out.print("Line: ");
        Vertex<String> startingV = getMainV(start);
        
        visited = new Hashtable<>();
        
        DFS(sGraph,startingV);
        
        return;
  }
    private static void DFS(Graph<String,String> graph, Vertex<String> v ) {
        if(visited.get(v.getElement())!=null){
            return;
        }
        visited.put(v.getElement(), Boolean.TRUE);
        startT(v);
        
        
        for(Edge<String> edge : graph.outgoingEdges(v)){
            Vertex<String> h = graph.opposite(v, edge);
            DFS(graph,h);
        }
        
        return;
  }
  private static void startT( Vertex<String> v ) {
    System.out.print( v.getElement() + " ");
  }
  private static Vertex<String> getMainV( String source ) {

    for( Vertex<String> vertx : sGraph.vertices() ) {
      if ( vertx.getElement().equals(source)) {
	    return vertx;
      }
    }
    return null;
  }

  private static Vertex<String> getMainV2(String source){
      for (Vertex<String> vertx : intGraph.vertices()){
          if (vertx.getElement().equals(source)){
              return vertx;
          }
      }

      return null;
  }

  private static /*Map<Vertex<String>, Integer>*/ void shortestpath(Graph<String,Integer> graph,Vertex from, Vertex to){
    GraphAlgorithms dj = new GraphAlgorithms();
    Map<Vertex<String>,Integer> result = dj.shortestPathLengths(graph, from, to);
    
    //ArrayList<String> all = dj.getArrayList();

    System.out.print(from.getElement() + " ");
    /*for (int i = dj.getArrayList().size()-1; i>=0; i--){
        System.out.print(dj.getArrayList().get(i) + " ");
    }*/

    for (Vertex<String> vGoal: intGraph.vertices()){
        if (vGoal.getElement().equals(theone)){
            System.out.println(" | " + from.getElement() + " to " + vGoal.getElement() + " | Time = " + result.get(vGoal));
        }
    }

      //return result;
}
}