import java.util.*;
import java.io.*;
public class WhiteWalker {
    public static void main(String[] args){
        //read input from the file

        try{
            File file = new File("in4.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());
            System.out.println(numTests);

            for(int i = 0; i < numTests; i++){
                WhiteWalkerTestCase case1 = new WhiteWalkerTestCase(input.nextLine(), input.nextLine());
                System.out.println(case1.getN() + " " + case1.getM());
                case1.printPowers();
            }
        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in3.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("Some error occurred...");
        }catch(Exception e){
            System.out.println("File is not formatted correctly. Please format it such that the first line " +
                    "contains number of test cases. Next line consists of integers N and M. Next line " +
                    "contains a list of the powers of the white walkers");
        }

    }
}
class WhiteWalkerTestCase{
    private int N;
    private int M;
    private int[] powers;

    public WhiteWalkerTestCase(String integersString, String powersString){
        String[] temp1 = integersString.split(" ");
        String[] temp2 = powersString.split(" ");
        N = Integer.parseInt(temp1[0]);
        M = Integer.parseInt(temp1[1]);

        powers = new int[temp2.length];
        for(int i = 0; i < temp2.length; i++){
            powers[i] = Integer.parseInt(temp2[i]);
        }

    }

    public int getN(){
        return this.N;
    }

    public int getM(){
        return this.M;
    }

    public int[] getPowers(){
        return this.powers;
    }

    public void printPowers(){
        for(int power : powers){
            System.out.print(power + " ");
        }
        System.out.println();
    }
}
