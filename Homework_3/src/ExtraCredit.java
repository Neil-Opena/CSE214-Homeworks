import java.util.*;
public class ExtraCredit {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int numOperations = input.nextInt();
        input.nextLine();
        TwoThreeFourTree tree = new TwoThreeFourTree();
        for(int i = 0; i < numOperations; i++){
            String[] line = input.nextLine().split(" ");
            int key = 0;
            if(line.length > 1){
                key = Integer.parseInt(line[1]);
            }
            int operation = Integer.parseInt(line[0]);

            switch(operation){
                case 1 :
                    System.out.println("Insert");
                    tree.insert(key);

                    break;
                case 2 :
                    System.out.println("Delete");
                    break;
                case 3 :
                    System.out.println("Search");
                    break;
                case 4 :
                    System.out.println("Inorder");
                    break;
                case 5 :
                    System.out.println("Preorder");
                    break;
                case 6 :
                    System.out.println("Postorder");
                    break;
            }
        }
    }
}

class TwoThreeFourTree{

    private Node root;

    private class Node{
        private int numItems;
        private int maxItems;
        private int[] data;
        private boolean isLeaf;
        private Node[] children;
        private int numChildren;
        private int maxChildren;

        public Node(){
            maxItems = 3;
            data = new int[maxItems];
            maxChildren = 4;
            children = new Node[4];
        }

        public Node(int item, boolean isLeaf){
            this();
            this.isLeaf = isLeaf;
            data[numItems] = item;
            numItems = 1;
        }


        public Node(int[] data, Node[] children){
            this();
            this.data = data;
            numItems = data.length;
            this.children = children;
            numChildren = children.length;
        }

        public void printData(){
            for(int item : data){
                System.out.print(item + " ");
            }
        }
    }

    public void insert(int item){
        insert(item, root);
    }

    public void insert(int item, Node node){
        if(isEmpty()){
            root = new Node(item, true);
            root.printData();
        }else{
            Node current = node;

            int insertIndex = current.numItems;

            System.out.println("inserting at data index " + insertIndex);
            for(int i = 0; i < current.data.length; i++){
                if(current.data[i] > item){
                    insertIndex = i;
                    break;
                }
            }


            if(current.numItems < current.maxItems && current.isLeaf){
                System.out.println("Current is leaf: " + current.isLeaf);
                for(int i = current.data.length - 2; i >= insertIndex; i--){
                    current.data[i + 1] = current.data[i];
                }

                //shift then insert


                current.data[insertIndex] = item;
                current.numItems++;

                //FIXMe delete later
                current.printData();
            }else{
                if(current.numItems >= current.maxItems) {
                    //split current node
                    System.out.println(current.isLeaf);
                    if (current == root) {
                        root = new Node(current.data[1], false);
                        boolean isLeaf;
                        if (current.numChildren > 0) {
                            isLeaf = false;
                        } else {
                            isLeaf = true;
                        }
                        root.children[0] = new Node(current.data[0], isLeaf);
                        root.children[1] = new Node(current.data[2], isLeaf);
                        insertIndex /= 2;
                        current = root;
                    }
                }
                //split child
                if(current.children[insertIndex].numItems >= current.maxItems){
                    int mid = current.children[insertIndex].data[1];
                    //bring mid to current node
                    int tempIndex = current.numItems;
                    for(int i = 0; i < current.data.length; i++){
                        if(current.data[i] > item){
                            tempIndex = i;
                            break;
                        }
                    }
                    for(int i = current.data.length - 2; i >= tempIndex; i--){
                        current.data[i + 1] = current.data[i];
                    }
                    //shift then insert
                    current.data[insertIndex] = item;
                    current.numItems++;

                    //FIXME split node
                }
                insert(item, current.children[insertIndex]);
            }
        }
    }

    public void inorder(){
        inorder(root);
    }

    public void inorder(Node node){

    }

    public boolean isEmpty(){
        return root == null;
    }
}