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
        if(N < 0 || M < 0){
            System.out.println("N or M is negative");
            System.exit(1);
        }

        int[] data = new int[N];
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(dataString[i]);
        }

        HashMap map = new HashMap(N * N);
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){

                Value tempValue = new Value(data[i] + data[j], i, j);
                int key = tempValue.getSum() % M;

                ArrayList<Value> testList = (ArrayList<Value>) map.get(key);
                if(testList != null){
                    // get arrayList
                    testList.add(tempValue);
                    map.put(key, testList);
                }else{
                    ArrayList<Value> valueList = new ArrayList<>();
                    valueList.add(tempValue);
                    map.put(key, valueList);
                }
            }
        }


        int key;
        HashMap answerMap = new HashMap();

        for(int i = 0; i < N; i++) {
            if(data[i] % M == 0){
                key = 0;
            }else{
                key = (M - (data[i] % M));
            }

            ArrayList<Value> retrievedList = (ArrayList<Value>) map.get(key);

            if (retrievedList != null) {
                for (int j = 0; j < retrievedList.size(); j++) {

                    Value tempValue = retrievedList.get(j);
                    if(tempValue.getIndex1() != i && tempValue.getIndex2() != i){
                        //System.out.println(tempValue + " ===== data[" + i + "] .. " + data[i]);
                        Answer answer = new Answer(i, tempValue.getIndex1(), tempValue.getIndex2());
                        answer.sortTrio();
                        String testString = answer.getStringRepresentation();
                        answerMap.put(testString, answer);
                    }

                }
            }
        }

        //used for testing
        /*Object[] a = answerMap.values().toArray();
        for(Object i : a){
            System.out.println(i);
        }*/

        System.out.println(answerMap.size());

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

    public String toString(){
        return "sum=" + sum + ", index1=" + index1 +", index2=" + index2;
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

    public void setIndex1(int index1){
        this.index1 = index1;
    }

    public void setIndex2(int index2){
        this.index2 = index2;
    }
}

