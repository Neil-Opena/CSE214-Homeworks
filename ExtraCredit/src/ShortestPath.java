import java.util.*;
import java.io.*;
public class ShortestPath {
    public static void main(String[] args){
        try{
            File file = new File("in1.txt");
            Scanner input = new Scanner(file);

            int T = Integer.parseInt(input.nextLine());

            //System.out.println(T);
            for(int caseI = 0; caseI < T; caseI++){
                int N = Integer.parseInt(input.nextLine());

                String[] sourceAndDestination = input.nextLine().split(" ");
                int source = Integer.parseInt(sourceAndDestination[0]);
                int destination = Integer.parseInt(sourceAndDestination[1]);

                String[][] matrix = new String[N][N];
                for(int i = 0; i < N; i++){
                    matrix[i] = input.nextLine().split(" ");
                }

                TestCase case1 = new TestCase(N, source, destination, matrix);

                //System.out.println(N);
                //System.out.println(source + " " + destination);
                //case1.printMatrix();

                case1.shortPathAlgorithm();
                System.out.println(case1.getDistance());
                case1.printPath();

                //System.out.println("-------------------------------------");
            }

        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in1.txt\" in the directory");
        }
    }
}

class TestCase {
    private int N;
    private int source;
    private int destination;
    private int[][] matrix;
    private ArrayList<Node> visited;
    private ArrayList<Node> remaining;
    private Node[] vertices;

    private class Node {
        private int index;
        private int distance;
        private Node parent;

        public Node(int index) {
            this.index = index;
        }

        public String toString() {
            return "{" + index + "}";
        }
    }

    public TestCase(int N, int source, int destination, String[][] matrix) {
        this.N = N;
        this.source = source;
        this.destination = destination;

        this.remaining = new ArrayList<Node>();
        this.vertices = new Node[N];
        for (int i = 0; i < N; i++) {
            Node temp = new Node(i);
            remaining.add(temp);
            vertices[i] = temp;
        }
        vertices[source].parent = null;

        this.visited = new ArrayList<Node>();

        this.matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.matrix[i][j] = Integer.parseInt(matrix[i][j]);
            }
        }

        int infinity = 101; //0 <= w <= 100
        for (int i = 0; i < remaining.size(); i++) {
            remaining.get(i).distance = infinity;
        }

        remaining.get(source).distance = 0;

    }

    public void printMatrix() {
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public void shortPathAlgorithm() {
        //Dijkstra's Shortest Path Algorithm

        for (int i = 0; i < N - 1; i++) {
            Node next = findMinNode();
            visited.add(next); //S Union next
            remaining.remove(next);

            /*
            System.out.println("Next = " + next);
            System.out.println("V = " + visited);
            System.out.println("V - S = " + remaining);
            */

            for (int j = 0; j < remaining.size(); j++) { //for each vertex v in V - S that is neighbor of next

                if (matrix[next.index][remaining.get(j).index] != 0) { // check if it is a neighbor

                    Node test = remaining.get(j);
                    int weight = matrix[next.index][test.index];
                    if(next.distance + weight < test.distance){
                        test.distance = next.distance + weight;
                        test.parent = next;
                        //System.out.println("Parent of " + test + " is " + next);
                    }
                }

            }
            //printDistances();
            //System.out.println("---------------");
        }
        //printDistances();
    }

    public int getDistance(){
        return vertices[destination].distance;
    }

    public void printPath(){
        Node temp = vertices[destination];
        Stack<Integer> stack = new Stack<>();
        while(temp!=null){
            stack.push(temp.index);
            temp = temp.parent;
        }
        while(stack.size() > 1){
            System.out.print(stack.pop() + " -> ");
        }
        System.out.println(stack.pop());

    }

    private Node findMinNode(){
        Node minNode = remaining.get(0);
        for(int i = 0; i < remaining.size(); i++){ //traverse V - S
            if(remaining.get(i).distance < minNode.distance){
                minNode = remaining.get(i);
            }
        }
        return minNode;
    }

    private void printDistances(){
        System.out.print("Distances = [");
        for(int i = 0; i < N; i++){
            System.out.print(vertices[i].distance + ", ");
        }
        System.out.println("]");
    }

}
