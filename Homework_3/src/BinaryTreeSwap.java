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

        if (valuesStringArray.length != numNodes) {
            System.out.println("Values for N and number of integers do not match");
            System.exit(0);
        }

        NodeTree[] values = new NodeTree[numNodes];


        for (int i = 0; i < numNodes; i++) {
            values[i] = new NodeTree(Integer.parseInt(valuesStringArray[i]), i);
            if (values[i].getData() < 1 || values[i].getData() > 105) {
                System.out.println("Value for a[i] too large or too small. Please input an integer ai 1<=ai<=105");
                System.exit(0);
            }
        }

        //create inorder array
        MyStack stack = new MyStack();
        ArrayList<Integer> temp = new ArrayList<>();

        NodeTree current = values[0];
        stack.push(current);

        while(!stack.isEmpty() || current != null){
            if(current == null){
                NodeTree popped = stack.pop();
                temp.add(popped.getData());
                if((popped.getIndex() * 2 + 2) >= values.length){
                    //check if right child of popped exists
                    current = null;
                }else{
                    current = values[popped.getIndex() * 2 + 2];
                    stack.push(current);
                }
                continue;
            }

            if((current.getIndex() * 2 + 1) >= values.length){
                //check if left child exists
                current = null;
            }else{
                current = values[current.getIndex() * 2 + 1];
                stack.push(current);
            }

        }

        int[] inorder = new int[numNodes];
        for(int i = 0; i < inorder.length; i++){
            inorder[i] = temp.get(i);
            //turn arrayList into array
            //System.out.print(inorder[i] + " ");
        }

        //System.out.println();

        temp = null; //delete arrayList
        System.out.println(sort(inorder));

        /*for(int value : inorder){
            System.out.print(value + " ");
        }
        System.out.println(); */



    }

    public static int sort(int[] arr){
        int numSwaps = 0;
        for(int i = 0; i < arr.length; i++){
            int min = arr[i];
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if(min < arr[i]){
                //swap
                numSwaps++;
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

        return numSwaps;
    }
}

    class NodeTree{
        private int data;
        private int index;

        public NodeTree(int data, int index){
            this.data = data;
            this.index = index;
        }

        public int getData(){
            return data;
        }

        public int getIndex(){
            return index;
        }
    }

    class MyStack{
        Node head;

        private class Node{
            private NodeTree data;
            private Node next;

            public Node(NodeTree data){
                this.data = data;
            }
        }

        public void push(NodeTree data){
            if(isEmpty()){
                head = new Node(data);
            }else{
                Node temp = new Node(data);
                temp.next = head;
                head = temp;
            }
        }

        public NodeTree pop(){
            Node temp = head;
            head = head.next;
            return temp.data;
        }

        public boolean isEmpty(){
            return head == null;
        }

    }

