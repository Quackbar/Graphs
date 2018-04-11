package graphs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
 
public class Kruskals
{
    int Vert, E;
    Edge edge[];
    static String[] vertexAlias;
 
    public Kruskals(int v, int e) {
        Vert = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
    public int find(cluster clusters[], int i) {
        if (clusters[i].parent != i)
            clusters[i].parent = find(clusters, clusters[i].parent);
        return clusters[i].parent;
    } 
    public void Merge(cluster clusters[], int x, int y) {
        int xroot = find(clusters, x);
        int yroot = find(clusters, y);

        if(clusters[xroot].rank < clusters[yroot].rank) {
            clusters[xroot].parent = yroot;
        }
        else if(clusters[xroot].rank > clusters[yroot].rank) {
            clusters[yroot].parent = xroot;
        }
 
        else {
            clusters[yroot].parent = xroot;
            clusters[xroot].rank++;
        }
    }
 
    public void KruskalMST() {
        Edge result[] = new Edge[Vert];
        int e = 0;
        int i = 0;
        for (i=0; i<Vert; ++i) {
            result[i] = new Edge();
        }
        
        Arrays.sort(edge);
 
        cluster clusters[] = new cluster[Vert];
        for(i=0; i<Vert; ++i) {
            clusters[i]=new cluster();
        }
 
        for (int v = 0; v < Vert; ++v) {
            clusters[v].parent = v;
            clusters[v].rank = 0;
        }
        i = 0;
        while (e < Vert - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
 
            int x = find(clusters, next_edge.src);
            int y = find(clusters, next_edge.dest);
 
            if (x != y)
            {
                result[e++] = next_edge;
                Merge(clusters, x, y);
            }
        }
        for (i = 0; i < e; ++i) {
            System.out.print(vertexAlias[result[i].src]+""+vertexAlias[result[i].dest]+" ");
        }
    }
    public static void main (String[] args) {
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
					vertexAlias = new String[array.length];
					for(int i=0;i<array.length;i++) {
						adjMatrix[0][i] = i;
						vertexAlias[i] = array[i].toString();
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
    	
        int V = getV(adjMatrix);  //Finds number of vertices in adjacency matrix
        int E = getE(adjMatrix,V);  //Finds number of edges in adjacency matrix
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
    }

	private static int getE(int[][] adjMatrix,int v) {
		int count = 0;
		for(int k=1;k<=v;k++) {
			for(int i =0;i<adjMatrix[k].length;i++) {
				if(adjMatrix[k][i]!=0) {
					count++;
				}
			}
		}
		count = count/2;
		return count;
	}

	private static int getV(int[][] adjMatrix) {
		int count = 0;
		for(int i =0;i<adjMatrix[0].length;i++) {
			count++;
		}
		return count;
	}
}