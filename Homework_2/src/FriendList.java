import java.util.*;
import java.io.*;
public class FriendList {
    public static void main(String[] args){
        try{
            File file = new File("in1.txt");
            Scanner input = new Scanner(file);

            int numTests = Integer.parseInt(input.nextLine());

            for(int i = 0; i < numTests; i++){
                FriendListTestCase case1 = new FriendListTestCase(input.nextLine(),input.nextLine(),input.nextLine());
                Bucket[] buckets = case1.getBuckets();
                //print contents of buckets - delete later
                for(int j = 0; j < buckets.length; j++){
                    System.out.print("Bucket " + j + ": ");
                    buckets[j].printFriends();
                    System.out.println("Size = " + buckets[j].getSize() + " min = " + buckets[j].getMinMF() + " max = " + buckets[j].getMaxMF());
                }
                System.out.println("-------------------------------------------");
                case1.printBucketizedList();
            }
        }catch(FileNotFoundException e){
            System.out.println("File does not exist. Please place a file named \"in3.txt\" in the " +
                    "same directory as the src directory, and try again.");
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("Some error occurred...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("File is not formatted correctly. Please format it such that the first line " +
                    "contains the number of test cases. Next line consists of two space separated integers N and K. " +
                    "Next line consists of N strings. Next line " +
                    "consists of integers representing the number of mutual friends.");
        }
    }

}
class FriendListTestCase{
    private int N;
    private int K;
    private Friend[] friendsList;
    private Bucket[] buckets;
    private Bucket initial;

    public FriendListTestCase(String friends, String namesString, String mutualFriendsString){
        String[] friendsInts = friends.split(" ");
        N = Integer.parseInt(friendsInts[0]);
        K = Integer.parseInt(friendsInts[1]);
        String[] names = namesString.split(" ");
        String[] mutualFriends = mutualFriendsString.split(" ");

        friendsList = new Friend[N];
        initial = new Bucket();
        for(int i = 0; i < N; i++){
            friendsList[i] = new Friend(names[i],Integer.parseInt(mutualFriends[i]),i);
        }

        sortMutualFriends(friendsList);
        for(int i = 0; i < N; i++){
            initial.addFriend(friendsList[i]);
        }
        int minMF = friendsList[0].getMutualFriends();
        int maxMF = friendsList[friendsList.length - 1].getMutualFriends();
        friendsList= null; //delete friendsList
        int key = N / K;

        buckets = new Bucket[K];
        for(int i = 0; i < K; i++){
            if(i == K - 1){
                buckets[i] = new Bucket(minMF, maxMF);
            }else{
                buckets[i] = new Bucket(minMF, minMF+=(key - 1));
            }
            minMF++;
        }

        while(!initial.isEmpty()){
            Friend friend = initial.removeFriend();
            for(int j = 0; j < K; j++){
                int MF = friend.getMutualFriends();
                int bucketMin = buckets[j].getMinMF();
                int bucketMax = buckets[j].getMaxMF();

                if((MF >= bucketMin) && (MF <= bucketMax)){
                    buckets[j].addFriend(friend);
                    continue;
                }
            }
        }

    }

    public Bucket[] getBuckets() {
        return buckets;
    }

    private void sortMutualFriends(Friend[] friendsList){
        for(int j = 1; j < friendsList.length; j++){
            Friend key = friendsList[j];
            int i = j - 1;
            while(i >= 0 && (key.getMutualFriends() < friendsList[i].getMutualFriends())){
                //shift
                friendsList[i + 1] = friendsList[i];
                i--;
            }
            friendsList[i + 1] = key;
        }
    }

    private void sortInitialPosition(Friend[] friendsList){
        for(int j = 1; j < friendsList.length; j++){
            Friend key = friendsList[j];
            int i = j - 1;
            while(i >= 0 && (key.getInitialPosition() < friendsList[i].getInitialPosition())){
                //shift
                friendsList[i + 1] = friendsList[i];
                i--;
            }
            friendsList[i + 1] = key;
        }
    }

    public int getN(){
        return this.N;
    }

    public int getK(){
        return this.K;
    }

    public void printBucketizedList(){
        for(int i = 0; i < buckets.length; i++){
            buckets[i].removeFriends(K);
            initial.addFriend(buckets[i].removeFriend());
        }

        Friend[] finalList = initial.array();
        sortInitialPosition(finalList);
        for(Friend friend : finalList){
            System.out.print(friend.getName() + " ");
        }
        System.out.println();
    }

}

class Friend{
    private String name;
    private int mutualFriends;
    private int initialPosition;

    public Friend(String name, int mutualFriends, int initialPosition){
        this.name = name;
        this.mutualFriends = mutualFriends;
        this.initialPosition = initialPosition;
    }

    public String getName() {
        return name;
    }

    public int getMutualFriends(){
        return mutualFriends;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public String toString(){
        return "[" + name + " - " + mutualFriends + "]";
    }
}

class Bucket{
    private Node head;
    private Node tail;
    private int minMF;
    private int maxMF;

    private class Node{
        private Friend friend;
        private Node next;

        public Node(Friend friend){
            this.friend = friend;
        }

    }

    public Bucket(){

    }

    public Bucket(int minMF, int maxMF){
        this.minMF = minMF;
        this.maxMF = maxMF;
    }

    public int getMaxMF() {
        return maxMF;
    }

    public int getMinMF(){
        return minMF;
    }

    public void addFriend(Friend friend){
        if(friend == null) return;
        if(isEmpty()){
            head = new Node(friend);
            tail = head;
        }else{
            Node temp = new Node(friend);
            tail.next = temp;
            tail = temp;
            tail.next = head; //circular
        }
    }

    public Friend removeFriend(){
        if(!isEmpty()){
            Friend friend = head.friend;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                tail.next = head;
            }
            //change pointer of tail
            return friend;
        }
        return null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void printFriends(){
        if(isEmpty()){
            return;
        }
        Node current = head;
        while(current!=tail){
            System.out.print(current.friend + " ");
            current = current.next;
        }
        System.out.print(tail.friend);
        System.out.println();
    }

    public Friend[] array(){
        Friend[] temp = new Friend[getSize()];
        for(int i = 0; i < temp.length; i++){
            temp[i] = removeFriend();
        }
        return temp;
    }

    public int getSize(){
        Node current = head;
        int count = 0;
        if(current == tail){
            return 1;
        }
        while(current!=null){
            count++;
            current = current.next;
            if(current == tail){
                count++;
                break;
            }
        }
        return count;
    }

    public void removeFriends(int K){
        while(getSize()!=1){
            for(int i = 1; i < K; i++){
                addFriend(removeFriend());
            }
            removeFriend();
        }
    }
}

