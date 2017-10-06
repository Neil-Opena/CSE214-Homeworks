public class LinkedList<T> {
    private Node head;

    private class Node<T>{
        private T data;
        private Node next;
        private Node previous;

        public Node(){

        }

        public Node(T data, Node next, Node previous){
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    public LinkedList(){

    }

    public LinkedList(T data){
        this.head = new Node<T>(data, null, null);
    }

    public Node getHead(){
        return this.head;
    }

    public void printList(){
        Node current = head;
        int count = 0;
        while(current!=null){
            System.out.print(++count + "=" + current.data + " ");
            current = current.next;
        }
    }

    public void addToStart(T data){
        Node temp = new Node<T>(data, null, null);
        temp.next = head;
        head = temp;
    }

}
