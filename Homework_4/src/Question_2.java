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
        int[] original = new int[N];
        for(int i = 0; i < N; i++){
            data[i] = Integer.parseInt(dataString[i]);
            original[i] = Integer.parseInt(dataString[i]);
        }

        HashMap map;
        int sumSize = N * N;
        if( sumSize > 16){
            map = new HashMap(sumSize + (int) (0.75 * sumSize));
            // create a new HashMap with a larger capacity
            // 75 % of N_squared to decrease the load factor
        }else{
            map = new HashMap();
        }

        int maxSum = data[0] + data[1];
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){ // O(n^2)
                int sum = data[i] + data[j];
                if(sum > maxSum){
                    maxSum = sum;
                }
                Value tempValue = new Value(sum, i, j); // add possible sums to hashmap
                map.put(tempValue.getSum(), tempValue);
            }
        }

        System.out.println(maxSum);



        /*


        //FIXMe print num of trios
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

        //FIXME make a bigger hash table

        //FIXME still do the while loop, or make new hash map every time?


        for(int i = 1; i < N; i++){
            int index = i;
            while(index > 0 && data[index - 1] > data[index]){ //sort input O(n^2)
                int tempSort = data[index - 1];
                data[index - 1] = data[index];
                data[index] = tempSort;
                index--;
            }
        }

        //FIXMe add fix to array size check
        Number maxTrio = new Number(data[N - 1], data[N - 2], data[N -3]);

        int[] multiplesOfM = new int[maxTrio.sum()/M];
        for(int i = 0; i < multiplesOfM.length; i++){
            multiplesOfM[i] = M * (i + 1);
        }

        ArrayList<Number> sums = new ArrayList<>();

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){  //determine possible sums O(n^2)
                sums.add(new Number(i, j));
            }
        }


        for(int i = 0; i < sums.size(); i++){
            for(int j = 0; j < multiplesOfM.length; j++){
                int testValue = multiplesOfM[j];
                Number num = sums.get(i);
                int sum = original[num.getIndex1()] + original[num.getIndex2()];
                Integer tempAnswer = (Integer) map.get(testValue - sum);
                boolean notFound = false;
                if(tempAnswer != null && tempAnswer != num.getIndex1()) {
                    System.out.println("[" + num.getIndex1() + ", " + num.getIndex2() + ", " + tempAnswer + "]" + " == " + testValue);
                }
            }
        }

        //FIXMe factorial unorder?

        */
    }
}

class Value{
    private int sum;
    private int index1;
    private int index2;

    public Value(int sum, int index1, int index2){
        this.sum = sum;
        this.index1 = index1;
        this.index2 = index2;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getSum() {
        return sum;
    }
}


class Number{
    private int index1;
    private int index2;
    private int index3;

    public Number(int index1, int index2, int index3){
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    public Number(int index1, int index2){
        this.index1 = index1;
        this.index2 = index2;
    }

    public int getIndex1(){
        return index1;
    }

    public int getIndex2(){
        return index2;
    }

    public int sum(){
        return index1 + index2 + index3;
    }

    public String toString(){
        return "[" + index1 + ", " + index2 + ", " + index3 + "]";
    }

}
