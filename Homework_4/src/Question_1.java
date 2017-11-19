import java.util.*;
public class Question_1 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String line1 = input.nextLine();
        String line2 = input.nextLine();
        String[] temp = line1.split(" ");
        String[] dataString = line2.split(" ");

        int N = Integer.parseInt(temp[0]);
        int T = Integer.parseInt(temp[1]);

        int[] data = new int[N];
        for(int i = 0; i < N; i++){
            data[i] = Integer.parseInt(dataString[i]);
        }

        HashMap map;
        if(N > 16){
            map = new HashMap(N + (0.75 * N);
            // create a new HashMap with a larger capacity
            // 75% of N to decrease the load factor
        }else{
            map = new HashMap();
        }

        //store in hash map
        for(int i = 0; i < N; i++){
            map.put(data[i], data[i]); // O(1) * n = 0(n)
        }


        ArrayList<Answer> answers = new ArrayList();



        for(int i = 0; i < N; i++){
            int key = T - data[i]; // look for a value that satisfies this key
            Integer answer = (Integer) map.get(key); // O(1) * n = O(n)
            if(answer!=null){   // create an object if it finds a value

            }
        }
        //FIXme try for negative numbers
    }
}

class Number{
    private int number;
    private int index;

    public Number(int number, int index){
        this.number = number;
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public int getNumber(){
        return this.number;
    }
}

class Answer{
    private int index1;
    private int index2;

    public Answer(int index1, int index2){
        this.index1 = index1;
        this.index2 = index2;
    }

    public String toString(){
        return "[" + index1 + ", " + index2 + "]";
    }

}
