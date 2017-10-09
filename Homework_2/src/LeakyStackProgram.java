import java.util.*;
import java.io.*;
public class LeakyStackProgram {
    public static void main(String[] args){
        //read input from file
        //FIXMe remove print statements in the end
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
        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in3.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("Some error occurred...");
        }catch(Exception e){
            System.out.println("File is not formatted correctly. Please format it such that the first line " +
                    "contains number of test cases. Next line contains the capacity of the LeakyStack. Next line " +
                    "contains the consecutive operations in the browser");
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
