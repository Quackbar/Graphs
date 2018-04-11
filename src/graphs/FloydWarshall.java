package fw;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public static void main(String[] args){
        /*
              1
        (A)--------(D)
         | \    3 / |
     10  |   \ /    |
         |   / \ 5  | 2
         | /     \  |
        (B)--------(C)
           4           */
    	
	    	String iFile = "textFiles/inputFW.txt";
	    	String s;
		String[] array = null;
		Path inputPath = Paths.get(iFile);
		int row=0;
		boolean first = true;
		
		int[][] adjMatrix = null;
			
	    	try {
				InputStream iStream = new BufferedInputStream(Files.newInputStream(inputPath));
				BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
				s = reader.readLine();
				while(s != null) {
					array = s.split(",");
					if(first) {
						adjMatrix = new int[array.length][array.length];
						vertexAlias = new String[array.length];
						for(int i=0;i<array.length;i++) {
							vertexAlias[i] = array[i].toString();
						}
						first = false;
					}
					else{
						for(int i=0;i<array.length;i++) {
							if(array[i].equals("I")) {
								adjMatrix[row][i] = INF;
							}
							else {
								adjMatrix[row][i] = Integer.parseInt(array[i]);
							}
						}
						row++;
					}
					s = reader.readLine();
				}
				reader.close();
	    	}
	    	catch(IOException e){
		        e.printStackTrace();
        }
	    	
        FloydWarshall fw = new FloydWarshall();
        
        fw.floydWarshall(adjMatrix);
    }
}
 