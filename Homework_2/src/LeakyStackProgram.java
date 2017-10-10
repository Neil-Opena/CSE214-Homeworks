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
                case1.emptyStack();
                System.out.println("--------------------------------------");
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

class LeakyStack{
    private int capacity;
    private Node top;

    private class Node{
        String operation;
        Node next;

        private Node(String operation){
            this.operation = operation;
        }
    }

    public LeakyStack(int capacity){
        this.capacity = capacity;
    }

    public void push(String operation){
        Node temp = new Node(operation);
        temp.next = top;
        top = temp;
        if(getSize() > capacity){
            deleteBottom();
        }
    }

    private void deleteBottom(){
        Node current = top;
        for(int i = 0; i < capacity - 1; i++){
            current = current.next;
        }
        current.next = null;
    }

    public String pop(){
        String tempData = top.operation;
        top = top.next;
        return tempData;
    }

    public int getSize(){
        Node current = top;
        int count = 0;
        while(current!=null){
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty(){
        return top == null;
    }

}

class LeakyStackTestCase{
    LeakyStack myStack;
    private int capacity;
    private String[] operations;

    public LeakyStackTestCase(String capacityString, String operationsString){
        this.operations = operationsString.split(" ");
        this.capacity = Integer.parseInt(capacityString);
        myStack = new LeakyStack(this.capacity);
        for(String operation : operations){
            myStack.push(operation);
        }
    }

    public int getCapacity(){
        return this.capacity;
    }

    public void printOperations(){
        for(String operation : operations){
            System.out.print(operation + " ");
        }
        System.out.println();
    }

    public void emptyStack(){
        System.out.println("Emptying stack...");
        while(!myStack.isEmpty()){
            System.out.print(myStack.pop() + " ");
        }
        System.out.println();
    }

}
