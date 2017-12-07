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
        private ArrayList<Node> neighborsVisited;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            neighborsVisited = new ArrayList<>();
        }

        public String toString() {

            return ("[" + (char) ('a' + row) + "," + col + "] ");
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


        return count;
    }
}



    //FIXME once you backtrack, mark others as false but don't traverse it
