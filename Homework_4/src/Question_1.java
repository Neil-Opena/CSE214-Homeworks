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
            map = new HashMap(N + (int) (0.75 * N));
            // create a new HashMap with a larger capacity
            // 75% of N to decrease the load factor
        }else{
            map = new HashMap();
        }

        //store in hash map
        for(int i = 0; i < N; i++){
            map.put(data[i], i); // O(1) * n = 0(n)
        }

        boolean sum = false;
        for(int i = 0; i < N; i++){
            int key = T - data[i]; // look for a value that satisfies this key
            Integer tempAnswer = (Integer) map.get(key); // O(1) * n = O(n)
            if(tempAnswer!=null && (i != tempAnswer)){   // create an object if it finds a value
                //May assume that each input would have exactly one solution
                System.out.print("[" + i + ", " + tempAnswer + "]");
                sum = true;
                break;
            }
        }

        if(!sum){
            System.out.print("[-1, -1]");
        }

        //FIXme continue testing
        //FIXme 4 10 -- 5 5 5 1
    }
}


