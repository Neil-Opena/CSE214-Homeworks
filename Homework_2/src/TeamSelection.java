import java.io.*;
import java.util.*;
public class TeamSelection {
    public static void main(String[] args){
        //read input from fileA
        try{
            File file = new File("in2.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());
            System.out.println(numTests);

            for(int i = 0; i < numTests; i++){
                TeamSelectionTestCase case1 = new TeamSelectionTestCase(input.nextLine(),input.nextLine());

            }

            //create object for each test case

        }catch(Exception e){
            System.out.println("File does not exist. Please place a file named \"in2.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }

    }

}

class TeamSelectionTestCase{
    private int[] jerseyNums;
    private int[] heights;

    public TeamSelectionTestCase(String jerseyNumString, String heightString){
        String[] temp1 = jerseyNumString.split(" ");
        String[] temp2 = heightString.split(" ");
        jerseyNums = new int[temp1.length];
        heights = new int[temp2.length];
        for(int i = 0; i < temp1.length; i++){
           //parse into int and transfer
            jerseyNums[i] = Integer.parseInt(temp1[i]);
            heights[i] = Integer.parseInt(temp2[i]);
        }
    }

    public int[] getJerseyNums(){
        return this.jerseyNums;
    }

    public int[] getHeights(){
        return this.heights;
    }

    public void printJerseyNums(){
        for(int nums: jerseyNums){
            System.out.print(nums + " ");
        }
        System.out.println();
    }

    public void printHeights(){
        for(int nums: heights){
            System.out.print(nums + " ");
        }
        System.out.println();
    }
}
