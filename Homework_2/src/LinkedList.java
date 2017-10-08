public class LinkedList<T> {
    private Node head;
    private Node tail;

    private class Node<T>{
        private T data;
        private Node<T> next;

        public Node(){

        }

        public Node(T data){
            this.data = data;
        }

        public String toString(){
            return data + "";
        }
    }

    public LinkedList(){

    }


    public Node getHead(){
        return this.head;
    }

    public Node getTail(){
        return this.tail;
    }

    public int getSize(){
        Node<T> current = head;
        int size = 0;
        while(current != null){
           size++;
           current = current.next;
        }
        return size;
    }



    public boolean clear(){
        head = null;
        tail = null;
        return true;
    }

    public void printList(){
        Node current = head;
        int count = 0;
        while(current!=null){
            System.out.print(++count + "=" + current + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void addToStart(T data){
        if(isEmpty()){
            //also create tail
            head = new Node<T>(data);
            tail = head;
        }else {
            Node temp = new Node<T>(data);
            temp.next = head;
            head = temp;
        }
    }

    public boolean deleteHead(){
        Node<T> current = head;
        if(current == null){
            return false;
        }
        head = head.next;
        return true;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void addToEnd(T data){
        if(isEmpty()){
            //also create head
            head = new Node<T>(data);
            tail = head;
        }else{
            tail.next = new Node<T>(data);
            tail = tail.next;
        }
    }

}
