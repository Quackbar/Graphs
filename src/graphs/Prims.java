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
 * @author sluther
 */
public class Prims {

    String iFile = "textFiles/inputP.txt";
    Path iPath = Paths.get(iFile);
    int in;
    int[][] table;
    
    public String[][] Prim(String[][] input){
        String[][] output = new String[7][6];
        String[][] queue = new String[6][2];
        String tempStr="";
        String tempNum = "20";
        //Where the queue will start
        String start = input[0][0];
        int startNum = 0;

        //filling the queue initially with the starts references
        for(int i=0; i<6; i++){
            queue[i][0]=input[0][i];
            queue[i][1]=input[i+1][0];
            for(int k=0; k<7; k++){
                output[k][i]="I";
            }
            output[0][i]=queue[i][0];
            //System.out.println(queue[i][0]+"=>"+queue[i][1]);
        }

        for(int q=0; q<5; q++){
            for(int j=0; j<queue.length; j++){
                //System.out.println(queue[j][1]);
                if(!queue[j][1].equals("I")){
                    if(Integer.parseInt(queue[j][1])<Integer.parseInt(tempNum)){
                        tempStr = queue[j][0];
                        tempNum = queue[j][1];
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

            output[spot][letter] = tempNum;
            tempNum = "20";
            tempStr = "";
            for(int i=0; i<6; i++){
                queue[i][1]=input[i+1][startNum];
                System.out.println(queue[i][0]+"=>"+queue[i][1]);
            }        
        }

        for(int i=0; i<7; i++){
            for(int k=0; k<6; k++){
                System.out.print(output[i][k]+",");
                
            }
            System.out.println();
        }
        for(int z=0; z<6; z++){
            System.out.print(output[0][z]);
            for(int x=0; x<6; x++){
               if(!output[x+1][z].equals("I")){
                    if((x+1)==1){
                        System.out.print("A");
                    }
                    else if((x+1)==2){
                        System.out.print("B");
                    }
                     else if((x+1)==3){
                        System.out.print("C");
                    }
                    else if((x+1)==4){
                        System.out.print("D");
                    }
                    else if((x+1)==5){
                        System.out.print("E");
                    }
                    else if((x+1)==6){
                        System.out.print("F");
                    }               
               }
            }
            System.out.println();
        }
        return output;
    }
    
    public Prims(){
        String[][] array = new String[7][6];
        int counter =0;
        try{
            InputStream iStream = new BufferedInputStream(Files.newInputStream(iPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            String s = reader.readLine();
            while(s != null){
                
                String[] tempAr = s.split(",");
                for(int i=0; i<tempAr.length;i++){
                    array[counter][i]=tempAr[i];
                }
                counter++;
                s = reader.readLine();
                
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        for(int i=0; i<7; i++){
            for(int k=0; k<6; k++){
                System.out.print(array[i][k]+",");
                
            }
            System.out.println();
        }
        Prim(array);
//        String[][] thing = {
//            {"A","B","C","D","E","F"},
//            {"I","I","I","I","I","I"},
//            {"I","5","I","1","11","I"},
//            {"I","1","I","I","I","I"},
//            {"5","I","I","I","3","I"},
//            {"I","I","I","11","I","7"},
//            {"I","I","3","I","I","I"}};
//        Prim(thing);
       
    }
}







