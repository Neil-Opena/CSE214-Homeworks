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
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(dataString[i]);
            original[i] = Integer.parseInt(dataString[i]);
        }

        ArrayList<Value> listSums = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){ // O(n^2)
                Value tempValue = new Value(original[i] + original[j], i, j); // create ArrayList of possible sums
                listSums.add(tempValue);
            }
        }

        HashMap map;
        if(N > 16){
            map = new HashMap(N + (int) (0.75 * N));
            // create a new HashMap with a larger capacity
            // 75% of N to decrease the load factor
        }else{
            map = new HashMap();
        }

        for(int i = 0; i < N; i++){
            map.put(original[i],i); // put original array into hashMap
        }

        int maxTrio = findMaxTrio(data);
        int[] multiplesOfM = getMultiplesOfM(maxTrio, M);
        HashMap answerMap = new HashMap(listSums.size());

        for(int i = 0; i < listSums.size(); i++){
            Value testValue = listSums.remove(i);
            for(int j = 0; j < multiplesOfM.length; j++){ // O(n^2)
                int key = multiplesOfM[j] - testValue.getSum();
                if(key < 0) continue;
                Integer retrievedValue = (Integer) map.get(key);
                if(retrievedValue != null && (retrievedValue != testValue.getIndex1()) && (retrievedValue != testValue.getIndex2())){

                    Answer tempAnswer = new Answer(testValue.getIndex1(), testValue.getIndex2(), retrievedValue);
                    tempAnswer.sortTrio();
                    String test = tempAnswer.getStringRepresentation();

                    //FIXME remove?

                    answerMap.putIfAbsent(test, tempAnswer);
                }
                //FIXMe other sums
            }
        }

        Object[] answers = answerMap.values().toArray();
        System.out.println(answers.length);

        //For testing purposes only
        Arrays.sort(answers);
        for(int i = 0; i < answers.length; i++){
            System.out.println(answers[i]);
        }

        //FIXMe add fix to array size check


    }

    //if hashmap.get answer is null --> put value in hashmap

    public static int findMaxTrio(int[] arr){
        // sorts array to find max Trio
        for(int i = 1; i < arr.length; i++){
            int index = i;
            while(index > 0 && arr[index - 1] > arr[index]){ //sort input
                int tempSort = arr[index - 1];
                arr[index - 1] = arr[index];
                arr[index] = tempSort;
                index--;
            }
        }
        return arr[arr.length - 1] + arr[arr.length - 2] + arr[arr.length - 3];
    }

    public static int[] getMultiplesOfM(int maxValue, int M){
        int[] temp = new int[maxValue / M];
        for(int i = 0; i < temp.length; i++){
            temp[i] = M * (i + 1);
        }

        return temp;
    }

}

class Answer implements Comparable{
    private int index1;
    private int index2;
    private int index3;

    public Answer(int index1, int index2, int index3){
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    public int getIndex3() {
        return index3;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex1() {
        return index1;
    }

    public String getStringRepresentation() {
        return "" + index1 + index2 + index3;
    }

    public String toString(){
        return ("[" + index1 + ", " + index2 + ", " + index3 + "]");
    }

    public void sortTrio(){
        if(index1 <= index2 && index1 <= index3){
            //min = index1
            //no swap necessary for index1
        }else if(index2 <= index1 && index2 <= index3){
            //min = index2
            int temp = index1;
            index1 = index2;
            index2 = temp;

        }else{
            //min = index3
            int temp = index1;
            index1 = index3;
            index3 = temp;
        }

        if(index2 <= index3){
            //no swap necessary
        }else{
            int temp = index2;
            index2 = index3;
            index3 = temp;
        }
    }

    public int compareTo(Object answer){
        return Integer.compare(Integer.parseInt(getStringRepresentation()), Integer.parseInt(((Answer) answer).getStringRepresentation()));
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

    public int getSum(){
        return sum;
    }

    public int getIndex1(){
        return index1;
    }

    public int getIndex2(){
        return index2;
    }
}

