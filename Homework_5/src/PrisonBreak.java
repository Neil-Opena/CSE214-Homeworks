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
            }

        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in1.txt\" in the directory");
        }
    }
}

class TestCase{
    private int N;
    private int[][] matrix;

    public TestCase(int N, int[][] matrix){
        this.N = N;
        this.matrix = matrix;
    }

    public void printMatrix(){
        for(int[] row : matrix){
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

}
