import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;


public class Jug {
		int Ca, Cb; //Capacities of A & B 
	int N; // The goal
	int fA, fB; //The cost to fill A & fill B
	int eA, eB; //The cost to empty A & empty B
	int pAB, pBA; //The cost to pour A to B (pAB) & the cost to pour B to A (pBA)
//	int numVerticies = 0;
//	int numEdges = 0;


	static int Ca1, Cb1, N1, fA1, fB1, eA1, eB1, pAB1, pBA1;
	public int numVerticies;
	
	
	
	
	Jug(int Ca, int Cb, int N, int fA, int fB, int eA, int eB, int pAB, int pBA){
		
		int checkNumVerticies = 0;
		
		this.Ca1 = Ca; //Capacities of A & B 
		this.Cb1 = Cb;
		this.N1 = N; //Goal
		this.fA1 = fA; //The cost to fill A & fill B
		this.fB1 = fB;
		this.eA1 = eA;//The cost to empty A & empty B
		this.eB1 = eB;
		this.pAB1 = pAB;//The cost to pour A to B (pAB) & the cost to pour B to A (pBA)
		this.pBA1 = pBA;
		
		verticies = new Vector<Vertex>();
		for(int i = 0; i<(Ca+1); i++){
			for(int j = 0; j<(Cb+1); j++){
				verticies.addElement(new Vertex(Ca, Cb, i, j));
				checkNumVerticies++;
				
			}
		}
		

		//Set amount full for first vertex to 0
		verticies.get(0).setAFilled(0);
		verticies.get(0).setBFilled(0);
		
		numVerticies = checkNumVerticies;

		/*
		int j;
		int k;
		Vertex vertexWithNeighbor = new Vertex();
		Vertex neighborToAdd = new Vertex();
		
		for(Vertex temp: verticies) {
			for(Vertex temp2: verticies){
				if(!(temp.equals(temp2))){
					if(temp.ACap == 0){
						temp.addNeighbor(temp2);
					}
				}
				
				
			}
			
			
		}
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
		
		
		
		
		*/
		
		for(Vertex temp: verticies) {
			
			setNeighbors(temp);
			
			System.out.print(temp.getLabel() + ", ");
		}
		
		
		numVerticies = checkNumVerticies;
		
		//int numEdges = getNumEdges();

	}

	//Vertex v = new Vertex(4,6);
	
	Vertex 
	
	
	public void printNeighbors(){

		
		for(Vertex v: verticies){
			
			int A1 = v.getAFilled();
			System.out.println("A1 = " + A1);
			int B1 = v.getBFilled();
			int A2;
			int B2;
			int ASIZE = v.getASize();
			int BSIZE = v.getBSize();
			int extraA = 0;
			int extraB = 0;
			int AResult = 0;
			int BResult = 0;
			
			LinkedList<Vertex> queue = new LinkedList();
			queue.add(start);
			
			//If A empty and filling A
			if(v.getAFilled() == 0){
				Vertex newV = new Vertex();
				newV.AFilled = ASIZE;
				newV.BFilled = v.getBFilled();
				v.addNeighbor(newV);
			}
			
			//If A empty and filling A
			if(v.getBFilled() == 0){
				Vertex newV = new Vertex();
				newV.AFilled = v.getAFilled();
				newV.BFilled = BSIZE;
				v.addNeighbor(newV);
			}
			
			
			//empty A iff A >0
			if(v.getAFilled() > 0){
				Vertex newV = new Vertex();
				newV.AFilled = 0;
				newV.BFilled = v.getBFilled();
				v.addNeighbor(newV);
			}
			//empty B iff A >0
			if(v.getBFilled()>0){
				Vertex newV = new Vertex();
				newV.AFilled = v.getAFilled();
				newV.BFilled = 0;
				v.addNeighbor(newV);
			}
			
			//add B to A
			A2 = A1 + B1;
			if(A2 > ASIZE){ ////Adding B to A caused overflow
				extraA = A2 - ASIZE;
				System.out.println("extraA = " + extraA);
				AResult = A2 - extraA;
				System.out.println("AResult = " + AResult);
				BResult = extraA;
				System.out.println("BResult = " + BResult);
				
				Vertex newV = new Vertex();
				newV.AFilled = AResult;
				newV.BFilled = BResult;
				v.addNeighbor(newV);
				
			}else{ //Adding B to A did not cause overflow
				AResult = A2;
				BResult = 0;
				
				Vertex newV = new Vertex();
				newV.AFilled = AResult;
				newV.BFilled = BResult;
				v.addNeighbor(newV);
				
			}
			System.out.println("\n\n");
			
			B2 = A1 + B1; 
			if(B2 > BSIZE){
				extraB = B2 - BSIZE;
				System.out.println("extraB = " + extraB);
				BResult = B2 - extraB;
				System.out.println("BResult = " + BResult);
				AResult = extraB;
				System.out.println("BResult = " + AResult);
				
				
				Vertex newV = new Vertex();
				newV.AFilled = AResult;
				newV.BFilled = BResult;
				v.addNeighbor(newV);
				
			}else{
				AResult = 0;
				BResult = B2;
				
				Vertex newV = new Vertex();
				newV.AFilled = AResult;
				newV.BFilled = BResult;
				v.addNeighbor(newV);
			}
			
		//	System.out.println("AResult = " + AResult + "\nBResult = " + BResult);
			
			System.out.println(v.getLabel() + "'s neighbors have labels:");
			System.out.print(v.neighbors.get(0).getAFilled());
			System.out.println(" & " + v.neighbors.get(0).getBFilled());
			System.out.print(v.neighbors.get(1).getAFilled());
			System.out.println(" & " + v.neighbors.get(0).getBFilled());
			
			
		}
		
		
		
	
		
		
	}
	
	public void setNeighbors(Vertex v){
		int options = 4;
		int aStart = v.AFilled; // 0 or 1
		int bStart = v.BFilled; // 0, 1, or 2
		// int ACap, BCap, AFilled, BFilled
		int[] aEnd;
		int[] bEnd;
		
		
		
		
		//for(Vertex temp: verticies){
			
	
		//	if(v.AFilled < v.ACap){
		//		v.addNeighbor(v.ACap, v.BFilled);
			
		//	}
		
		
		//In Vertex: private ArrayList<Vertex> neighbors;
		
		
		
/*		for(Vertex temp: verticies) {
			if(temp.getLabel().equals(v.getLabel())){
				System.out.println("Ignore this check");
			}else if(temp.getAFilled() == 0){
				Vertex newV = temp;
				
			}
		}
		
		
		vertices.get(j).addNeigbor(vertices.get(k));
	*/	
	}
	
	
	
	
	public void buildgraph() {
		/*int numNodes = 0;
		int numEdges = 0;
	
		for(int i = 0; i < numNodes; i++) {
			//vertices.addElement(new Vertex(scanner.next()));
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
		*/
	}
}
		
