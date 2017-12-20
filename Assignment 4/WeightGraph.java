
// $Id: WeightGraph.java,v 1.1 2006/11/18 01:20:12 jlang Exp $
// CSI2110 Fall 2006 Laboratory 9: Adjacency List and DFS
// ==========================================================================
// (C)opyright:
//
//   Jochen Lang
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada.
//   http://www.site.uottawa.ca
//
// Creator: jlang (Jochen Lang)
// Email:   jlang@site.uottawa.ca
// ==========================================================================
// $Log: WeightGraph.java,v $
// Revision 1.1  2006/11/18 01:20:12  jlang
// Added lab10
//
// Revision 1.1  2006/11/11 03:15:52  jlang
// Added Lab9
//
// Modified by Thais Bardini on November 19th, 2017 (tbard069@uottawa.ca) 
// ==========================================================================
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.ArrayList;
//import net.datastructures.Dijkstra;
import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.Map;
import net.datastructures.Vertex;
import net.datastructures.*;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

public class WeightGraph {
	Graph<String, String> sGraph;
	Graph<String, Integer> sGraph2;
	Graph<String, Integer> sGraph3;
	ArrayList<String> all;
	//ArrayList<ArrayList<Edge<Integer>>> st;
	//ArrayList<Vertex<String>> previous;
	Object WEIGHT = new Object();
	int count = 0;
	public WeightGraph(String fileName) throws Exception, IOException { // Constructor 1
		sGraph = new AdjacencyMapGraph<String, String>(false);
		sGraph3 = new AdjacencyMapGraph<String,Integer>(false);
		//all = new ArrayList<>();
		//st = new ArrayList<>();
		//previous = new ArrayList<>();
		readMetro(fileName); // Read Integer edges graph and String edges graph with 90 edges deleted for question 2-1
	}

	public WeightGraph(String fileName, boolean correct) throws Exception, IOException{ // Constructor 2
		sGraph2 = new AdjacencyMapGraph<String, Integer>(false);
		readMetro2(fileName); // Read integer edges graph with 90 edges kept.
	}
	public Graph<String, String>  getGraph(){
		return this.sGraph; // Getter to get graph
	}

	public Graph<String,Integer> getGraph2(){
		return this.sGraph2; //Getter to get graph with 90 edges
	}
	protected void readMetro(String fileName) throws Exception, IOException {
		// Create a hash map to store all the vertices read
		Hashtable<String, Vertex> vertices = new Hashtable<String, Vertex>();
		Hashtable<String,Vertex> vertices2 = new Hashtable<String,Vertex>();
		Hashtable<String,Vertex> verticeswithdest = new Hashtable<String, Vertex>();
		String y = "False";
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int i = 0;
			for(String line; (line = br.readLine()) != null; ) {
				if (line.equals("$")){ // If line == "$" it will start appending Edges, weight
					y = "True";
				}
				if (y.equals("True") && line.equals("$") == false){
					String[] parts1 = line.split(" ");
					String[] parts2 = line.split(" ");
					Vertex<String> ev = vertices.get(parts1[0]);
					Vertex<String> ev0 = vertices2.get(parts2[0]);
					Vertex<String> dv = vertices.get(parts1[1]);
					Vertex<String> dv0 = vertices2.get(parts2[1]);
					if (ev == null && ev0 == null){
						ev = sGraph.insertVertex(parts1[0]);
						ev0 = sGraph2.insertVertex(parts2[0]);
						//System.out.println(parts1[0]);
						vertices.put(parts1[0], ev);
						vertices2.put(parts2[0], ev0);
					}

					
					if (dv == null && dv0 == null/*|| dv != null && ev == null*/){
						dv = sGraph.insertVertex(parts1[1]);
						dv0 = sGraph2.insertVertex(parts2[1]);
						//System.out.println(parts1[1]);
						vertices.put(parts1[1], dv);
						vertices2.put(parts2[1],dv0);
					}
					String weight;
					String weight2;
					if (parts1[2].equals("-1")){
						break;
						//weight = "90";
					}
					else{
						weight = parts1[2];
						weight2 = parts2[2];
					}
					
					if (sGraph.getEdge(ev, dv) == null && sGraph2.getEdge(ev0,dv0) == null){
						Edge<String> e = sGraph.insertEdge(ev, dv, weight);
						Edge<Integer> r = sGraph3.insertEdge(ev0,dv0,Integer.valueOf(weight2));
						//System.out.println("Here4");
					}
				}
			}
		}
	}
	protected Vertex<String> getVertex(String vert) throws Exception {
		// Go through vertex list to find vertex -- why is this not a map
		for (Vertex<String> vs : sGraph.vertices()) {
			if (vs.getElement().equals(vert)) {
				return vs;
			}
		}
		throw new Exception("Vertex not in graph: " + vert);
	}

	protected void readMetro2(String fileName) throws Exception, IOException {
		// Create a hash map to store all the vertices read
		Hashtable<String,Vertex> vertices2 = new Hashtable<String,Vertex>();
		String y = "False";
		String weight2;
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int i = 0;
			for(String line; (line = br.readLine()) != null; ) {
				if (line.equals("$")){ // If line == "$" it will start appending Edges, weight
					y = "True";
				}
				if (y.equals("True") && line.equals("$") == false){
					String[] parts2 = line.split(" "); //Splitting by line and putting each vertices and edge to the graph
					Vertex<String> ev0 = vertices2.get(parts2[0]); // Arrays containing ed
					Vertex<String> dv0 = vertices2.get(parts2[1]);
					if (ev0 == null){
						ev0 = sGraph2.insertVertex(parts2[0]);
						//System.out.println(parts1[0]);
						vertices2.put(parts2[0], ev0);
					}

					
					if (dv0 == null/*|| dv != null && ev == null*/){
						dv0 = sGraph2.insertVertex(parts2[1]);
						//System.out.println(parts1[1]);
						vertices2.put(parts2[1],dv0);
					}
					
					weight2 = parts2[2];

					if (parts2[2].equals("-1")){
						weight2 = "90";
					}

					else{
						weight2 = parts2[2];
					}
					
					if (sGraph2.getEdge(ev0,dv0) == null){
						Edge<Integer> r = sGraph2.insertEdge(ev0,dv0,Integer.valueOf(weight2));
						//System.out.println("Here4");
					}
				}
			}
		}
	}
}
