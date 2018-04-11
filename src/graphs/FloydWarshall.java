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

/**
 *
 * @author mwintersteen
 */
public class FloydWarshall
{
    final static int INF = 99999;
    static String[] vertexAlias;
    int vert;
    
    public void floydWarshall(int graph[][])
    {
    		vert = graph[0].length;
        int fw[][] = new int[vert][vert];
        int i, j, k;
        
        for(i=0;i<vert;i++) {
            for(j=0;j<vert;j++) {
            		fw[i][j] = graph[i][j];
            }
        }
        
        printAdjMatrix(fw);
		System.out.print("-----------------------\n");
        
        for(k=0;k<vert;k++)
        {
            for(i=0;i<vert;i++)
            {
                for(j=0;j<vert;j++)
                {
                    if(fw[i][k]+fw[k][j]<fw[i][j]){
                    		fw[i][j]=fw[i][k]+fw[k][j];
                    		printAdjMatrix(fw);
                    		System.out.print("-----------------------\n");
                    }
                }
            }
        }
        
        printAdjMatrix(fw);
    }
 
    public void printAdjMatrix(int fw[][])
    {
    		System.out.print("  ");
    		for(int i=0;i<vert;++i){
    			System.out.print(vertexAlias[i]+" ");
        }
    		System.out.print("\n");
        for(int i=0;i<vert;++i){
        		System.out.print(vertexAlias[i]+" ");
            for(int j=0;j<vert;++j){
                if(fw[i][j]==INF) {
                    System.out.print("INF ");
                }
                else {
                    System.out.print(fw[i][j]+" ");
                }
            }
            System.out.print("\n");
        }
    }
}
 