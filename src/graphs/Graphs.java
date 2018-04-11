



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author sluther
 */
public class Graphs {

    String iFile = "textFiles/input.txt";
    Path iPath = Paths.get(iFile);
    int in;
    int[][] table;
    
    public String[][] Prim(String[][] input){
        String[][] output = new String[5][6];
        String[][] queue = new String[5][1];
        String tempStr="";
        String tempNum = "0";
        //Where the queue will start
        String start = input[0][0];
        int startNum = 0;

        //filling the queue initially with the starts references
        for(int i=0; i<6; i++){
            queue[i][0]=input[i][0];
            queue[i][1]=input[0][i+1];
            for(int k=0; k<7; k++){
                output[i][k]=input[i][k];
            }
            System.out.println(queue[i][0]+"=>"+queue[i][1]);
        }
        for(int q=0; q<6; q++){
            for(int j=0; j<queue.length; j++){
                if(!queue[1][j].equals("I")){
                    if(Integer.parseInt(queue[1][j])>Integer.parseInt(tempNum)){
                        tempStr = queue[0][j];
                        tempNum = queue[1][j];
                    }
                }
            }
            int spot = 1;
            int letter=startNum;
            if(tempStr.equals("A")){
                spot=1;
                startNum=0;
            }
            else if(tempStr.equals("B")){
                spot=2;
                startNum=1;
            }
            else if(tempStr.equals("C")){
                spot=3;
                startNum=2;
            }
            else if(tempStr.equals("D")){
                spot=4;
                startNum=3;
            }
            else if(tempStr.equals("E")){
                spot=5;
                startNum=4;
            }
            else if(tempStr.equals("F")){
                spot=6;
                startNum=5;
            }

            output[letter][spot] = tempNum;
            tempNum = "0";
            tempStr = "";
            for(int i=0; i<6; i++){
                queue[i][0]=input[i][startNum];
                System.out.println(queue[i][0]+"=>"+queue[i][1]);
            }        
        }

        
        return output;
    }
    public void Kruskel(){
        
    }
    public void FloydWarshal(){
        
    }
    
    public Graphs(){
        try{
            InputStream iStream = new BufferedInputStream(Files.newInputStream(iPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            in = reader.read();

            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
       
    }
    
    public static void main(String[] args) {
        Graphs g = new Graphs();
    }
    
}







