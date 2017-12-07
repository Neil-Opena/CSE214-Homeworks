import java.util.*;
import java.io.*;
public class PrisonBreak {
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

                TestCase case1 = new TestCase(N, matrix);

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

class TestCase{
    private int N;
    private int[][] matrix;
    private boolean[][] marked;
    private Node start;
    private Node end;

    private class Node{
        private int row;
        private int col;
        private ArrayList<Node> neighborsVisited;

        public Node(int row, int col){
            this.row = row;
            this.col = col;
            neighborsVisited = new ArrayList<>();
        }

        public String toString(){

            return ("[" + (char) ('a' + row) + "," + col + "] ");
        }
    }

    public TestCase(int N, int[][] matrix){
        this.N = N;
        this.matrix = matrix;
        this.marked = new boolean[N][N];

        start = new Node(0, 0);
        end = new Node(N - 1, N - 1);

        clearMarked();

    }

    public void printMatrix(){
        for(int[] row : matrix){
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }


    public int getNumEscapePaths() {
        int count = 0;

        //If the first cell [0,0] and the last cell [N-1,N-1] contain motion detectors
        //John can't break out of the prison


        if(matrix[start.row][start.col] == 1 && matrix[end.row][end.col] == 1){
            return count;
        }

        Stack<Node> myStack = new Stack<>(); //create stack for DFS
        myStack.push(start); //push starting cell

        Node vertex;
        Node justVisited = null;

        while(!myStack.isEmpty()){
            vertex = myStack.peek();
            System.out.println("Current Vertex = " + vertex);
            System.out.println("Stack = " + myStack.toString());
            int i = vertex.row;
            int j = vertex.col;

            if(justVisited != null){
                //System.out.print(justVisited + " - justVisited   j.v nodes = {");

                for(int index = 0; index < justVisited.neighborsVisited.size(); index++){
                    Node temp = justVisited.neighborsVisited.get(index);
                    //System.out.print(temp + ", ");

                    //change marked of visited
                    int tempRow = temp.row;
                    int tempCol = temp.col;
                    marked[tempRow][tempCol] = false;
                }

                justVisited.neighborsVisited.clear();

                //System.out.println("}");
            }


            if(i == N - 1 && j == N - 1){
                //path reached
                marked[i][j] = true;
                count++;

                Node popped = myStack.pop();

                vertex = myStack.peek();
                System.out.println("Current Vertex = " + vertex);
                i = vertex.row;
                j = vertex.col;
                marked[i][j] = false;
                System.out.println("__________________________");
            }

            //FIXMe

            if(!marked[i][j]){
                marked[i][j] = true;
                //System.out.println(vertex);
                Node bottom = checkBottom(myStack, i, j);
                if(bottom != null){
                    vertex.neighborsVisited.add(bottom);
                    continue;
                }

                Node right = checkRight(myStack, i, j);
                if(right != null){
                    vertex.neighborsVisited.add(right);
                    continue;
                }

                Node top = checkTop(myStack, i, j);
                if(top != null){
                    vertex.neighborsVisited.add(top);
                    continue;
                }

                Node left = checkLeft(myStack, i, j);
                if(left != null){
                    vertex.neighborsVisited.add(left);
                    continue;
                }


            }

            System.out.print("cannot go further = ");
            Node popped = myStack.pop();
            System.out.println("Popped " + popped);
            //FIXME equals method?

            justVisited = popped;

            if(popped.row == 0 && popped.col == 0){
                continue;
            }

            Node peeked = myStack.peek();
            marked[peeked.row][peeked.col] = false;


        }

        return count;
    }


    //FIXME once you backtrack, mark others as false but don't traverse it

    //FIXME mark nodes as traversing so there are no loops (done by turning marked into true)
    //FIXME unmark current cell and backtrack --> look for other paths


    //FIXME backtrack but don't visit it

    /*
    public int countPaths(){
        int count = 0;
        //FIXME standard check if first and last cell have 1

        return DFS(start);
    }

    private int DFS(Node vertex){
        int i = vertex.row;
        int j = vertex.col;
        marked[i][j] = true;
        System.out.print(vertex + " ");
        if(vertex.equals(end)){
            System.out.println("END");
            //all marked = false?
            return 0;
        }

        //checkBottom
        if((i + 1) < N){
            if((matrix[i + 1][j] == 0) && (!marked[i + 1][j])){
                DFS(new Node(i + 1, j));
            }
        }

        //checkRight
        if((j + 1) < N){
            if((matrix[i][j + 1] == 0) && (!marked[i][j + 1])){
                DFS(new Node(i, j + 1));
            }
        }

        //checkTop
        if((i - 1) >= 0){
            if((matrix[i - 1][j] == 0) && (!marked[i - 1][j])){
                DFS(new Node(i - 1, j));
            }
        }

        //checkLeft
        if((j - 1) >= 0){
            if((matrix[i][j - 1] == 0) && (!marked[i][j - 1])){
                DFS(new Node(i, j - 1));
            }
        }

        System.out.print(vertex + "* ");
        //refix marked? 4 ?
        return 0;
    }
    */

    private void clearMarked(){
        //initialize marked matrix where 1 = false
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    marked[i][j] = true;
                }else{
                    marked[i][j] = false;
                }
            }
        }
    }

    private Node checkBottom(Stack<Node> stack, int i, int j){
        if((i + 1) < N){
            if((matrix[i + 1][j] == 0) && (!marked[i + 1][j])){
                Node temp = new Node(i + 1, j);
                stack.push(temp);
                return temp;
            }
        }
        return null;
    }

    private Node checkRight(Stack<Node> stack, int i, int j){
        if((j + 1) < N){
            if((matrix[i][j + 1] == 0) && (!marked[i][j + 1])){
                Node temp = new Node(i, j + 1);
                stack.push(temp);
                return temp;
            }
        }
        return null;
    }

    private Node checkTop(Stack<Node> stack, int i, int j){
        if((i - 1) >= 0){
            if((matrix[i - 1][j] == 0) && (!marked[i - 1][j])){
                Node temp = new Node(i - 1, j);
                stack.push(temp);
                return temp;
            }
        }
        return null;
    }

    private Node checkLeft(Stack<Node> stack, int i, int j){
       if((j - 1) >= 0){
           if((matrix[i][j - 1] == 0) && (!marked[i][j - 1])){
                Node temp = new Node(i, j - 1);
                stack.push(temp);
                return temp;
           }
        }
        return null;
    }

}
