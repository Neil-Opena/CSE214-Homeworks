import java.util.*;
public class ConcertTickets {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String[] line1 = input.nextLine().split(" ");
        String[] line2 = input.nextLine().split(" ");
        int M = Integer.parseInt(line1[0]);
        int N = Integer.parseInt(line1[1]);
        if(M != line2.length){
            System.out.println("Values for M and number of empty seats not matching");
            System.exit(0);
        }

        //number of person greater
        PriorityQueue prices = new PriorityQueue(M);
        for(int i = 0; i < M; i++){
            int temp = Integer.parseInt(line2[i]);
            if(temp < 1 || temp > 1000){
                System.out.println("Value for vacant seat is not within constraints. Please input 1 <= X[i] <= 1000");
                System.exit(0);
            }
            prices.enqueue(temp);
        }
        int total = 0;
        for(int i = 0; i < N; i++){
            int price = prices.dequeue();
            if(price > 0){
                total += price;
                prices.enqueue(price - 1);
            }else{
                prices.enqueue(price);
            }
        }
        System.out.println(total);

    }
}

class PriorityQueue{
    private int[] data;
    private int M;
    private int numElements;

    public PriorityQueue(int M){
        this.M = M;
        data = new int[M];
    }

    public void enqueue(int emptySeat){
        data[numElements++] = emptySeat;
        int index = numElements - 1;
        while(index > 0 && (data[index] > data[(index - 1)/2])){
            int temp = data[index];
            data[index] = data[(index - 1)/2];
            data[(index - 1)/2] = temp;
            index = (index - 1)/2;
        }
    }

    public int dequeue(){
        int temp = data[0];
        data[0] = data[numElements - 1];
        data[numElements - 1] = 0;
        numElements--;
        fixHeap();
        return temp;
    }

    public void fixHeap(){
        int index = 0;
        while(index * 2 + 1 < numElements){
            //left child exists
            int childIndex = index * 2 + 1;
            if(childIndex + 1 < numElements && data[childIndex + 1] > data[childIndex]){
                childIndex++;
                //right child larger than left
            }
            if(data[childIndex] > data[index]){
                //swap if child greater than parent
                int temp = data[index];
                data[index] = data[childIndex];
                data[childIndex] = temp;

                index = childIndex;
            }else{
                return;
            }
        }
    }

    public void printPrices(){
        for(int price : data){
            System.out.print(price + " ");
        }
        System.out.println();
    }

}
