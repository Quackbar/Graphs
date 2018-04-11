package graphs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Graphs {

	public static void main(String[] args) {
		//Prim's Algorithm
		System.out.println("Prim's Algorithm");
		Prims p = new Prims(); 
		System.out.print("Finished\n-----------------------\n");
		
		//Kruskal's Algorithm
		System.out.println("Kruskal's Algorithm");
        /* weighted graph
                 10
            A--------B
            |  \     |
           6|   5\   |15
            |      \ |
            C--------D
                4       
                
           Adjacency Matrix
           
           A  B  C  D
         A 0 10  6  5
         B 10 0  0  15
         C 6  0  0  4
         D 5  15 4  0
                
                */
	    	String iFile = "textFiles/inputK.txt";
	    	String s;
		String[] array = null;
		Path inputPath = Paths.get(iFile);
		int row=0;
		
		int[][] adjMatrix = null;
		
		try {
			InputStream iStream = new BufferedInputStream(Files.newInputStream(inputPath));
			BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
			s = reader.readLine();
			while(s != null) {
				array = s.split(",");
				if(row == 0) {
					adjMatrix = new int[array.length+1][array.length];
					Kruskals.vertexAlias = new String[array.length];
					for(int i=0;i<array.length;i++) {
						adjMatrix[0][i] = i;
						Kruskals.vertexAlias[i] = array[i].toString();
					}
					row++;
				}
				else {
					for(int i=0;i<array.length;i++) {
						adjMatrix[row][i] = Integer.parseInt(array[i]);
					}
					row++;
				}
				s = reader.readLine();
			}
			reader.close();
		}
		catch(IOException e) {
	        e.printStackTrace();
	    }
    	
        int V = Kruskals.getV(adjMatrix);  //Finds number of vertices in adjacency matrix
        int E = Kruskals.getE(adjMatrix,V);  //Finds number of edges in adjacency matrix
        Kruskals graph = new Kruskals(V, E);
        
        int c = 0;
        for(int k=1;k<adjMatrix.length;k++) {
			for(int i =(k-1);i<adjMatrix[k].length;i++) {
				if(adjMatrix[k][i]!=0) {
					if(adjMatrix[k][i]==adjMatrix[i+1][k-1]) {
						graph.edge[c].src=(k-1);
						graph.edge[c].dest=(i);
						graph.edge[c].weight=adjMatrix[k][i];
						c++;
					}
				}
			}
		}
        graph.KruskalMST();
        System.out.print("Finished\n-----------------------\n");
		//Floyd Warshall's Algorithm
		System.out.println("Floyd Warshall's Algorithm");
			/*
	        1
	  (A)--------(D)
	   | \    3 / |
	10  |   \ /    |
	   |   / \ 5  | 2
	   | /     \  |
	  (B)--------(C)
	     4           */
		
	  	String iFile1 = "textFiles/inputFW.txt";
	  	String s1;
		String[] array1 = null;
		Path inputPath1 = Paths.get(iFile1);
		int row1=0;
		boolean first = true;
		
		int[][] adjMatrix1 = null;
			
	  	try {
				InputStream iStream = new BufferedInputStream(Files.newInputStream(inputPath1));
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
				s1 = reader.readLine();
				while(s1 != null) {
					array1 = s1.split(",");
					if(first) {
						adjMatrix1 = new int[array1.length][array1.length];
						FloydWarshall.vertexAlias = new String[array1.length];
						for(int i=0;i<array1.length;i++) {
							FloydWarshall.vertexAlias[i] = array1[i].toString();
						}
						first = false;
					}
					else{
						for(int i=0;i<array1.length;i++) {
							if(array1[i].equals("I")) {
								adjMatrix1[row1][i] = FloydWarshall.INF;
							}
							else {
								adjMatrix1[row1][i] = Integer.parseInt(array1[i]);
							}
						}
						row1++;
					}
					s1 = reader.readLine();
				}
				reader.close();
	  	}
	  	catch(IOException e){
		        e.printStackTrace();
	  }
	  	
	  FloydWarshall fw = new FloydWarshall();
	  
	  fw.floydWarshall(adjMatrix1);
	  System.out.print("Finished\n-----------------------\n");
	}
}
