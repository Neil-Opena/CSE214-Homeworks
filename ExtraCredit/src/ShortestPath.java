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

    public TestCase(int N, int source, int destination, String[][] matrix){
        this.N = N;
        this.source = source;
        this.destination = destination;

        this.matrix = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                this.matrix[i][j] = Integer.parseInt(matrix[i][j]);
            }
        }
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

    }

}
