import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;



public class Graph {
	public class Vertex {
		private ArrayList<Vertex> neighbors;
		private int distance;
		private String label;
		
		//A variable to check if the node is connected to a source node
		private int connection;
	
		public Vertex(String label) {
			neighbors = new ArrayList<Vertex>();
			this.label = label;
		}
		
		private String getLabel(){
			return label;
		}
		
		private void addNeigbor(Vertex neighbor) {
			neighbors.add(neighbor);
		}
		
		private void printNeighbors() {
			if(neighbors.isEmpty()) {
				System.out.print("empty");
				return;
			}
			for(Vertex temp: neighbors) {
				System.out.print(temp.getLabel() + ", ");
			}
		}
		
		private int getDistance() {
			return distance;
		}
		
		private void setDistance(int temp) {
			distance = temp;
		}
		
		private int getConnection() {
			return connection;
		}
		
		private void setConnection(int temp) {
			connection = temp;
		}
	}
	
	private Vector<Vertex> verticies;
	
	Vertex startV;
	int[] costs = new int[6];
	
	private Vector<Vertex> vertices;
	
	Vertex startV;
	int[] costs = new int[6];
	
	public Graph(int fA, int fB, int eA, int eB, int pAB, int pBA) {
		startV = null;
		costs[0] = fA;
		costs[1] = fB;
		costs[2] = eA;
		costs[3] = eB;
		costs[4] = pAB;
		costs[5] = pBA;
		
	}
	
	
	Vertex startV;
	int[] costs = new int[6];
	
	
	public void buildgraph(Ca, Cb) {
		int numNodes = 0;
		int numEdges = 0;
		File file = new File(graphInput);
		if(!file.exists()) {
			System.out.println("The file entered was not found");
			return;
		}
		try {
			Scanner scanner = new Scanner(file);
			numNodes = scanner.nextInt();
			numEdges = scanner.nextInt();
			
			for(int i = 0; i < numNodes; i++) {
				vertices.addElement(new Vertex(scanner.next()));
			}
			
			int j;
			int k;
			String vertexWithNeighbor;
			String neighborToAdd;
			
			for(int i = 0; i < numEdges; i++) {
				j = 0;
				k = 0;
				vertexWithNeighbor = scanner.next();
				neighborToAdd = scanner.next();
				
				//Finding the position in the Vector of the vertex with a neighbor
				while(!vertexWithNeighbor.equals(vertices.get(j).getLabel())) {
					j+=1;
				}
				
				//Finding the position in the Vector of the neighbor
				while(!neighborToAdd.equals(vertices.get(k).getLabel())) {
					k+=1;
				}
				
				//Adding the neighbor to the vertex's neighbors field
				vertices.get(j).addNeigbor(vertices.get(k));
			}
			
			try{
			} catch( IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
			scanner.close();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
	}
	
	public void display() {
		for(Vertex temp: vertices) {
			System.out.print(temp.getLabel() + "-> ");
			temp.printNeighbors();
			System.out.println();
		}
	}
	
	public void display_levels() {
		for(Vertex temp: vertices) {
			System.out.println(temp.getLabel() + " -> " + temp.getDistance());
		}
	}
	
	public void set_levels(Vertex source) {
		LinkedList<Vertex> q = null;
		
		//Setting each distance to a large int except for the source vertex
		for(Vertex temp: vertices) {
			if(!temp.getLabel().equals(source.getLabel())) {
				temp.setDistance(Integer.MAX_VALUE);
			} else {
				temp.setDistance(0);
				q = new LinkedList<Vertex>();
				q.add(temp);
			}
		}
		
		Vertex start;
		while(!q.isEmpty()) {
			start = q.pop();
			for(Vertex temp: start.neighbors) {
				if(temp.getDistance() == Integer.MAX_VALUE) {
					temp.setDistance(start.getDistance() + 1);
					q.push(temp);
				}
			}
		}
	}
	
	public boolean is_connected(Vertex source) {
		LinkedList<Vertex> q = null;
		
		//Setting each distance to a large int except for the source vertex
		for(Vertex temp: vertices) {
			if(!temp.getLabel().equals(source.getLabel())) {
				temp.setConnection(Integer.MAX_VALUE);
			} else {
				temp.setConnection(0);
				q = new LinkedList<Vertex>();
				q.add(temp);
			}
		}
		
		Vertex start;
		while(!q.isEmpty()) {
			start = q.pop();
			for(Vertex temp: start.neighbors) {
				if(temp.getConnection() == Integer.MAX_VALUE) {
					temp.setConnection(start.getConnection() + 1);
					q.push(temp);
				}
			}
		}
		
		//If one of the vertices distance is the large int then it has not been reached, no connection
		for(Vertex temp: vertices) {
			if(temp.getConnection() == Integer.MAX_VALUE)
				return false;
		}
		return true;
	}
}
