import java.util.*;
import java.io.*;
public class ShortestPath {
    public static void main(String[] args){
        try{
            File file = new File("in1.txt");
            Scanner input = new Scanner(file);

            int T = Integer.parseInt(input.nextLine());

            //FIXME delete later
            System.out.println(T);
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

                //FIXME delete later
                System.out.println(N);
                System.out.println(source + " " + destination);
                case1.printMatrix();
                case1.printDistances();
                case1.printShortestPath();
                System.out.println("-------------------------------------");
            }

        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in1.txt\" in the directory");
        }
    }
}

class TestCase{
    private int N;
    private int source;
    private int destination;
    private int[][] matrix;
    private ArrayList<Integer> visited;
    private ArrayList<Integer> vertices;
    private int[] distances;

    public TestCase(int N, int source, int destination, String[][] matrix){
        this.N = N;
        this.source = source;
        this.destination = destination;

        this.vertices = new ArrayList<Integer>();
        for(int i = 0; i < N; i++){
            vertices.add(i);
        }

        this.visited = new ArrayList<Integer>();

        this.matrix = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                this.matrix[i][j] = Integer.parseInt(matrix[i][j]);
            }
        }

        int infinity = 101; //0 <= w <= 100
        this.distances = new int[N];
        for(int i = 0; i < N; i++){
            this.distances[i] = infinity;
        }

        distances[source] = 0;
    }

    public void printMatrix(){
        for(int[] row : matrix){
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public void printShortestPath(){
        //Dijkstra's Shortest Path Algorithm

        for(int i = 0; i < N - 1; i++){
            int next = findMin(vertices);
            visited.add(next); //S Union next
            vertices.remove((Object) next);
            //System.out.println("Next = " + next);
            //System.out.println("V = " + visited);
            //System.out.println("V - S = " + vertices);
            //System.out.print("Neighbors of " + next + " ===== ");
            for(int j = 0; j < vertices.size(); j++){ //for each vertex v in V - S that is neighbor of next
                if(matrix[next][vertices.get(j)] != 0){ // check if it is a neighbor
                    //System.out.print(vertices.get(j) + " ");
                    int index = vertices.get(j);
                    int weight = matrix[next][vertices.get(j)];
                    if(distances[next] + weight < distances[index]){
                        distances[index] = distances[next] + weight;
                    }
                }
            }
            System.out.println();
            printDistances();
            System.out.println("-----------------");
        }
    }

    private int findMin(ArrayList<Integer> list){
        int min = distances[list.get(0)];
        int minIndex = list.get(0);
        for(int i = 0; i < list.size(); i++){
            int val = distances[list.get(i)];
            if(val < min){
                min = distances[list.get(i)];
                minIndex = list.get(i);
            }
        }
        return minIndex;
    }

    private int findMinIndex(int[] arr){
        int min = arr[0];
        int minIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printDistances(){
        System.out.print("Distances = [");
        for(int i = 0; i < N; i++){
            System.out.print(distances[i] + ", ");
        }
        System.out.println("]");
    }

}
