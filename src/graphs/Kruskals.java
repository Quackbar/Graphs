/*
 * Authors: Matt Wintersteen, Sam Luther
 * Date: Tues, Apr 10
 * Overview: It graph
 */
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
/**
 *
 * @author mwintersteen
 */ 
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
	public static int getE(int[][] adjMatrix,int v) {
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

	public static int getV(int[][] adjMatrix) {
		int count = 0;
		for(int i =0;i<adjMatrix[0].length;i++) {
			count++;
		}
		return count;
	}
}