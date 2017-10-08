import java.util.*;
import java.io.*;
public class LeakyStack {
    public static void main(String[] args){
        //read input from file

        //FIXMe make sure to put src code in src folder and zip that folder up with text files
        try{
            File file = new File("in3.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());
            System.out.println(numTests);

            for(int i = 0; i < numTests; i++){
                LeakyStackTestCase case1 = new LeakyStackTestCase(input.nextLine(), input.nextLine());
                System.out.println(case1.getCapacity());
                case1.printOperations();
            }
        }catch(Exception e){
            System.out.println("File does not exist. Please place a file name \"in3.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }
    }
}

class LeakyStackTestCase{
    private int capacity;
    private String[] operations;

    public LeakyStackTestCase(String capacityString, String operationsString){
        capacity = Integer.parseInt(capacityString);
        operations = operationsString.split(" ");
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String[] getOperations(){
        return this.operations;
    }

    public void printOperations(){
        for(String ops : operations){
            System.out.print(ops + " ");
        }
        System.out.println();
    }

}
