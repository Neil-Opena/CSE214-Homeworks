import java.lang.reflect.Array;
import java.util.*;
public class BinaryTreeSwap {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numNodes = input.nextInt();
        if (numNodes < 1 || numNodes > 105) {
            System.out.println("Value for N too large or too small. Please input an integer N 1<=N<=105");
            System.exit(0);
        }

        input.nextLine();
        String valuesString = input.nextLine();
        String[] valuesStringArray = valuesString.split(" ");
        int[] values = new int[valuesStringArray.length];

        if (values.length != numNodes) {
            System.out.println("Values for N and number of integers do not match");
            System.exit(0);
        }


        for (int i = 0; i < numNodes; i++) {
            values[i] = Integer.parseInt(valuesStringArray[i]);
            if (values[i] < 1 || values[i] > 105) {
                System.out.println("Value for a[i] too large or too small. Please input an integer ai 1<=ai<=105");
                System.exit(0);
            }
        }

        //create inorder array

        MyStack stack = new MyStack();
        //test stack
        for (int value : values) {
            stack.push(value);
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }


    }
}

    class MyStack{
        Node head;

        private class Node{
            private int data;
            private Node next;

            public Node(int data){
                this.data = data;
            }
        }

        public void push(int data){
            if(isEmpty()){
                head = new Node(data);
            }else{
                Node temp = new Node(data);
                temp.next = head;
                head = temp;
            }
        }

        public int pop(){
            Node temp = head;
            head = head.next;
            return temp.data;
        }

        public boolean isEmpty(){
            return head == null;
        }

    }

