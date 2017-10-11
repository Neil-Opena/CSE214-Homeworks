import java.util.*;
import java.io.*;
public class WhiteWalkerProgram {
    public static void main(String[] args){
        //read input from the file
        try{
            File file = new File("in4.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());

            for(int i = 0; i < numTests; i++){
                WhiteWalkerTestCase case1 = new WhiteWalkerTestCase(input.nextLine(), input.nextLine());
                for(int j = 0; j < case1.getM(); j++){
                    case1.iterate();
                }
                case1.printFinalList();
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
    private Queue list;
    private Queue finalList;

    public WhiteWalkerTestCase(String integersString, String powersString){
        String[] temp1 = integersString.split(" ");
        String[] temp2 = powersString.split(" ");
        N = Integer.parseInt(temp1[0]);
        M = Integer.parseInt(temp1[1]);
        list = new Queue();
        finalList = new Queue();
        if(N != temp2.length){
            System.out.println("Mismatched numbers for queue number and length of powers");
        }else{
            for(int i = 0; i < temp2.length; i++){
                list.enqueue(new WhiteWalker(i, Integer.parseInt(temp2[i])));
            }
        }

    }

    public int getM(){
        return this.M;
    }

    public void iterate(){
        Queue tempList = new Queue();
        for(int i = 0; i < M; i++){
            tempList.enqueue(list.dequeue());
        }
        WhiteWalker max = tempList.findMax();
        while(!tempList.isEmpty()){
            WhiteWalker temp = tempList.dequeue();
            if(temp == max){
                finalList.enqueue(temp);
            }else{
                temp.decreasePower();
                list.enqueue(temp);
            }
        }

    }

    public void printFinalList(){
        while(!finalList.isEmpty()){
            System.out.print(finalList.dequeue().getInitialPosition() + " ");
        }
        System.out.println();
    }

}

class WhiteWalker{
    private int initialPosition;
    private int power;

    public WhiteWalker(int initialPosition, int power){
        this.initialPosition = initialPosition;
        this.power = power;
    }

    public int getInitialPosition(){
        return initialPosition;
    }

    public int getPower(){
        return power;
    }

    public void decreasePower() {
        if(--power < 0){
            power = 0;
        }
    }

    public String toString(){
        return "[" + initialPosition + " - " + power + "]";
    }
}

class Queue implements Cloneable{
    private Node front;
    private Node back;

    private class Node{
        private WhiteWalker walker;
        private Node next;

        public Node(WhiteWalker walker){
            this.walker = walker;
        }
    }

    public boolean isEmpty(){
        return front == null;
    }

    public void enqueue(WhiteWalker walker){
        if(walker == null){
            return;
        }
        Node temp = new Node(walker);
        if(!isEmpty()){
            back.next = temp;
            back = back.next;
        }else{
            front = temp;
            back = temp;
        }
    }

    public WhiteWalker dequeue(){
        if(!isEmpty()){
            WhiteWalker temp = front.walker;
            front = front.next;
            return temp;
        }
        return null;
    }

    public void printCurrentList(){
        //FIXME deleteLater
        Node current = front;
        while(current!=null){
            System.out.print(current.walker + " ");
            current = current.next;
        }
        System.out.println();
    }

    public WhiteWalker findMax(){
        Node current = front;
        Node max = current;
        while(current!=null){
            if(current.walker.getPower() > max.walker.getPower()){
                max = current;
            }
            current = current.next;
        }
        return max.walker;
    }

}
