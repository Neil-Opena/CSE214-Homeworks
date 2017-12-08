import java.util.*;
import java.io.*;
public class EscapeRoutes {
    public static void main(String[] args){
        //try to read from file
        try{
            File file = new File("in1.txt");
            Scanner input = new Scanner(file);

            int T = Integer.parseInt(input.nextLine()); //number of test cases

            for(int i = 0; i < T; i++){

                int N = Integer.parseInt(input.nextLine()); //the size of N x N matrix
                int[][] matrix = new int[N][N];

                for(int j = 0; j < N; j++){
                    String[] tempArr = input.nextLine().split(" ");
                    for(int k = 0; k < N; k++){ //O(n^2)
                        matrix[j][k] = Integer.parseInt(tempArr[k]);
                    }
                }

                PrisonCase case1 = new PrisonCase(N, matrix);

                //FIXMe Delete Later
                System.out.println(N);

                case1.printMatrix();

                System.out.println(case1.getNumEscapePaths());
                //System.out.println(case1.countPaths());
                System.out.println("-------------------------------------");
            }

        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in1.txt\" in the directory");
        }
    }
}

class PrisonCase {
    private int N;
    private int[][] matrix;
    private boolean[][] marked;
    private Node start;
    private Node end;

    private class Node {
        private int row;
        private int col;
        private ArrayList<Node> forbidden;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            forbidden = new ArrayList<>();
        }

        public String toString() {
            return ("[" + (char) ('a' + row) + "," + col + "] ");
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                Node test = (Node) obj;
                return test.row == row && test.col == col;
            }
            return false;
        }
    }

    public PrisonCase(int N, int[][] matrix) {
        this.N = N;
        this.matrix = matrix;
        this.marked = new boolean[N][N];

        start = new Node(0, 0);
        end = new Node(N - 1, N - 1);

        //initialize marked
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 0){
                    marked[i][j] = false;
                }else{
                    marked[i][j] = true;
                }
            }
        }
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }


    public int getNumEscapePaths() {
        int count = 0;

        //If the first cell [0,0] and the last cell [N-1,N-1] contain motion detectors
        //John can't break out of the prison


        if (matrix[start.row][start.col] == 1 && matrix[end.row][end.col] == 1) {
            return count;
        }

        Stack<Node> myStack = new Stack<>(); //create stack for DFS
        myStack.push(start); //push starting cell

        Node vertex;
        Node justVisited = null;
        while(!myStack.isEmpty()){
            vertex = myStack.peek();
            int i = vertex.row;
            int j = vertex.col;


            //System.out.println("Current vertex = " + vertex + " JustVisited = " + justVisited);
            if(justVisited != null){
                for(int index = 0; index < justVisited.forbidden.size(); index++){
                    Node temp = justVisited.forbidden.get(index);
                    clearVertex(temp.row, temp.col);
                }
            }
            //System.out.println("Forbidden = " + vertex.forbidden);
            //System.out.println("Stack = " + myStack);

            if(vertex.equals(end)){
                count++;
                myStack.pop();
                //System.out.println("------------------------------------------------");
                System.out.println(myStack + " " + end);
                continue;
            }
            if(!marked[i][j]){ //check if marked
                marked[i][j] = true;

                //check bottom
                if(i + 1 < N){
                    //check if marked
                    if(!marked[i + 1][j] && matrix[i + 1][j] == 0){
                        justVisited = vertex;
                        Node temp = new Node(i + 1, j);

                        //check if temp is in forbidden list
                        if(!vertex.forbidden.contains(temp)){
                            myStack.push(temp);
                            continue;
                        }
                    }
                }

                //check right
                if(j + 1 < N){
                    //check if marked
                    if(!marked[i][j + 1] && matrix[i][j + 1] == 0){
                        justVisited = vertex;
                        Node temp = new Node(i, j + 1);

                        //check if temp is in forbidden list
                        if(!vertex.forbidden.contains(temp)){
                            myStack.push(temp);
                            continue;
                        }
                    }
                }

                //check top
                if(i - 1 >= 0){
                    //check if marked
                    if(!marked[i - 1][j] && matrix[i - 1][j] == 0){
                        justVisited = vertex;
                        Node temp = new Node(i - 1, j);

                        //check if temp is in forbidden list
                        if(!vertex.forbidden.contains(temp)){
                            myStack.push(temp);
                            continue;
                        }
                    }
                }

                //check left
                if(j - 1 >= 0){
                    //check if marked
                    if(!marked[i][j - 1] && matrix[i][j - 1] == 0){
                        justVisited = vertex;
                        Node temp = new Node(i, j - 1);

                        //check if temp is in forbidden list
                        if(!vertex.forbidden.contains(temp)){
                            myStack.push(temp);
                            continue;
                        }
                    }
                }

            }
            //System.out.println("Cannot proceed -- backtracking...");

            Node popped = myStack.pop();
            justVisited = popped;

            //marks peek as false so that it can enter if statement and check again
            if(!vertex.equals(start)){
                Node peeked = myStack.peek();
                clearVertex(peeked.row, peeked.col);
                peeked.forbidden.add(popped);
            }
        }


        return count;
    }

    private void clearVertex(int i, int j){
        marked[i][j] = false;
    }
}



    //FIXME once you backtrack, mark others as false but don't traverse it
