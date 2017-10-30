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

        PriorityQueue prices = new PriorityQueue(M);
        for(int i = 0; i < M; i++){
            prices.enqueue(Integer.parseInt(line2[i]));
            prices.printPrices();
        }

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

    public void printPrices(){
        for(int price : data){
            System.out.print(price + " ");
        }
        System.out.println();
    }

}
