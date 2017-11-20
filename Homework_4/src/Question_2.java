import java.lang.reflect.Array;
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
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(dataString[i]);
        }

        HashMap map = new HashMap(N * N);
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                //instead of one value stored, perhaps an array

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
        ArrayList<Answer> answerList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            //look for value st.
            key = (data[i] % M);
            if (key != 0) {
                key = M - key;
            }

            ArrayList<Value> retrievedList = (ArrayList<Value>) map.get(key);

            if (retrievedList != null) {
                for (int j = 0; j < retrievedList.size(); j++) {
                    Value temporaryValue = retrievedList.get(j);
                    if (temporaryValue.getIndex2() != i && temporaryValue.getIndex1() != i) {
                        Answer answer = new Answer(temporaryValue, i);
                        answer.sortTrio();
                        System.out.println(answer);
                    }
                }
            }
        }
    }

}


class Answer {
    private Value value;
    private int index;

    public Answer(Value value, int index){
        this.value = value;
        this.index = index;
    }

    public Value getValue(){
        return this.value;
    }

    public String getString(){
        return "" + value.getIndex1() + value.getIndex2() + index;
    }

    public String toString(){
        return ("[" + value.getIndex1() + ", " + value.getIndex2() + ", " + index + "]");
    }

    public void sortTrio(){
        if(value.getIndex1() <= value.getIndex2() && value.getIndex1() <= index){
            //min = index1
            //no swap necessary for index1
        }else if(value.getIndex2() <= value.getIndex1() && value.getIndex2() <= index){
            //min = index2
            int temp = value.getIndex1();
            value.setIndex1(value.getIndex2());
            value.setIndex2(temp);

        }else{
            //min = index
            int temp = value.getIndex1();
            value.setIndex1(index);
            index = temp;
        }

        if(value.getIndex2() <= index){
            //no swap necessary
        }else{
            int temp = value.getIndex2();
            value.setIndex2(index);
            index = temp;
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

