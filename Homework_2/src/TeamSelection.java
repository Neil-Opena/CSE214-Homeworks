import java.io.*;
import java.util.*;
public class TeamSelection {
    public static void main(String[] args) {
        //read input from fileA
        try {
            File file = new File("in2.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());

            for (int i = 0; i < numTests; i++) {
                PlayerLinkedList temp = new PlayerLinkedList(input.nextLine(), input.nextLine());
                /*temp.printJerseyNums();
                temp.printHeights();*/
                temp.eliminate();
                temp.printJerseyNums();
                System.out.println("----------------------------");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist. Please place a file named \"in2.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("Some error occurred...");
        }catch(Exception e){
            System.out.println("File is not formatted correctly. Please format it such that the first line " +
            "contains number of test cases. Second line contains the jersey number of the players. Third line " +
            "contains heights of the players in centimeters.");
        }

    }

}

class Player{
    private int jerseyNum;
    private int height;

    public Player(int jerseyNum, int height){
        this.jerseyNum = jerseyNum;
        this.height = height;
    }

    public int getJerseyNum(){
        return this.jerseyNum;
    }

    public int getHeight(){
        return this.height;
    }

    public String toString(){
        return "(" + jerseyNum + "-" + height + ") ";
    }
}

class PlayerLinkedList {
    private Node head;
    private Node tail;

    private class Node{
        private Player player;
        private Node next;
        private Node previous;

        public Node(Player player) {
            this.player = player;
        }

        public String toString(){
            return this.player + "";
        }
    }

    public PlayerLinkedList(String jerseyNumString, String heightsString){

        String[] temp1 = jerseyNumString.split(" ");
        String[] temp2 = heightsString.split(" ");

        for(int i = 0; i < temp1.length; i++){
            addToEnd(new Player(Integer.parseInt(temp1[i]), Integer.parseInt(temp2[i])));
        }
    }

    public void printList(){
        Node current = head;
        while(current!=null){
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void printJerseyNums(){
        Node current = head;
        while(current!=null){
            System.out.print(current.player.getJerseyNum() + " ");
            current = current.next;
        }
        System.out.println();
    }


    public void printHeights(){
        Node current = head;
        while(current!=null){
            System.out.print(current.player.getHeight() + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void addToEnd(Player player){
        if(head == null) {
            head = new Node(player);
            tail = head;
        }else{
            tail.next = new Node(player);
            tail.next.previous = tail;
            tail = tail.next;
        }
    }

    public void eliminate(){
        if (head == null) {
            System.out.println("List is empty. There are no players");
        }
        Node current = head;
        while(current.next!=null){
           if(current.next.player.getHeight() > current.player.getHeight()){
               //System.out.println(current + " will be eliminated");
               removeNode(current);
           }
           current = current.next;
        }
    }

    private void removeNode(Node node){
        if(head == node){
            head = head.next;
        }else {
            node.previous.next = node.next;
        }
    }
}
