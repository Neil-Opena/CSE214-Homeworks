import java.util.*;
public class Question_2 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        String[] temp = line1.split(" ");
        String[] dataString = line2.split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[] data = new int[N];
        for(int i = 0; i < N; i++){
            data[i] = Integer.parseInt(dataString[i]);
        }

        /*

        int[][] matrix = new int[N][N]; // create a matrix of possible sums of numbers

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){ // O(n^2)
                if(i == j){
                    matrix[i][j] = data[i];
                }else{
                    matrix[i][j] = data[i] + data[j];
                }
            }
        } //FIXMe might not need to initialize matrix

        HashMap map;

        if(N > 16){
            map = new HashMap(N + (int) (0.75 * N));
            // create a new HashMap with a larger capacity
            // 75% of N to decrease the load factor
        }else{
            map = new HashMap();
        }

        for(int i = 0; i < N; i++){
            map.put(data[i],i);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j){
                    continue;
                }
                int tempKey;

                tempKey = (matrix[i][j] + M);
                int productOfM = tempKey - (tempKey % M);
                int key = productOfM - matrix[i][j];


                Integer tempAnswer = (Integer) map.get(key);
                if(tempAnswer != null && tempAnswer != i){
                    Answer a = new Answer(i,j,tempAnswer);
                    System.out.println(a);
                }

            }
        } */

        //FIXMe print num of trios

        for(int i = 1; i < N; i++){
            int index = i;
            while(index > 0 && data[index - 1] > data[index]){ //sort input
                int tempSort = data[index - 1];
                data[index - 1] = data[index];
                data[index] = tempSort;
                index--;
            }
        }

        //FIXMe add fix to array size check
        Answer maxTrio = new Answer(data[N - 1], data[N - 2], data[N -3]);

        int[] multiplesOfM = new int[maxTrio.sum()/M];
        for(int i = 0; i < multiplesOfM.length; i++){
            multiplesOfM[i] = M * (i + 1);
            System.out.print(multiplesOfM[i] + " ");
        }



    }
}


class Answer{
    private int index1;
    private int index2;
    private int index3;

    public Answer(int index1, int index2, int index3){
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    public int sum(){
        return index1 + index2 + index3;
    }

    public String toString(){
        return "[" + index1 + ", " + index2 + ", " + index3 + "]";
    }

}
