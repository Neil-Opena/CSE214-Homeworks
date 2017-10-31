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
                    //System.out.println("Insert");
                    tree.insert(key);
                    break;
                case 2 :
                    //System.out.println("Delete");
                    tree.delete(key);
                    break;
                case 3 :
                    //System.out.println("Search");
                    tree.search(key);
                    break;
                case 4 :
                    //System.out.println("Inorder");
                    tree.inorder();
                    break;
                case 5 :
                    //System.out.println("Preorder");
                    tree.preorder();
                    break;
                case 6 :
                    //System.out.println("Postorder");
                    tree.postorder();
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


        public void printData(){
            for(int item : data){
                System.out.print(item + " ");
            }
        }

        public int getNumChildren(){
            int temp = 0;
            for(Node child : children){
                if(child!=null) temp++;
            }
            return temp;
        }
    }

    public void search(int key){
        if(search(key, root) != null){
            System.out.println("successful");
            return;
        }
        System.out.println("failed");
    }

    public Node search(int key, Node node){
        if(isEmpty()){
            return null;
        }else if(node == null){
            return null;
        }
        Node current = node;
        int tempIndex = current.numItems;
        for(int i = 0; i < current.numItems; i++){
            if(current.data[i] == key){
                return current;
            }
            if(current.data[i] > key){
                tempIndex = i;
                break;
            }
        }
        return search(key,node.children[tempIndex]);
    }

    public void insert(int item){
        insert(item, root);
    }

    public void insert(int item, Node node){
        if(isEmpty()){
            root = new Node(item, true);
            //root.printData();
        }else{
            Node current = node;

            int insertIndex = current.numItems;

            //System.out.println("inserting at data index " + insertIndex);
            for(int i = 0; i < current.data.length; i++){
                if(current.data[i] > item){
                    insertIndex = i;
                    break;
                }
            }


            if(current.numItems < current.maxItems && current.isLeaf){
                for(int i = current.data.length - 2; i >= insertIndex; i--){
                    current.data[i + 1] = current.data[i];
                }

                //shift then insert


                current.data[insertIndex] = item;
                current.numItems++;

                //current.printData();
            }else{
                if(current.numItems >= current.maxItems) {
                    //split current node
                    if (current == root) {
                        root = new Node(current.data[1], false);
                        Node left, right;

                        if (current.getNumChildren() > 0) {
                            left = root.children[0] = new Node(current.data[0], false);
                            right = root.children[1] = new Node(current.data[2], false);
                            //transfer children

                            //copy
                            Node[] firstTwoChildren = new Node[current.maxChildren];
                            firstTwoChildren[0] = current.children[0];
                            firstTwoChildren[1] = current.children[1];


                            Node[] lastTwoChildren = new Node[current.maxChildren];
                            lastTwoChildren[0] = current.children[2];
                            lastTwoChildren[1] = current.children[3];

                            left.children = firstTwoChildren;
                            right.children = lastTwoChildren;


                        } else {
                            root.children[0] = new Node(current.data[0], true);
                            root.children[1] = new Node(current.data[2], true);
                        }
                        insertIndex /= 2;
                        current = root;
                    }
                }
                //split child
                Node child = current.children[insertIndex];
                if(child.numItems >= current.maxItems){
                    int mid = child.data[1];
                    //bring mid to current node
                    int tempIndex = current.numItems;
                    for(int i = 0; i < current.numItems; i++){
                        if(current.data[i] > item){
                            tempIndex = i;
                            break;
                        }
                    }//find index where to insert mid value of child

                    for(int i = current.data.length - 2; i >= tempIndex; i--){
                        current.data[i + 1] = current.data[i];
                    }
                    //shift then insert
                    current.data[insertIndex] = mid;
                    current.numItems++;

                    //FIXME split node
                    Node left, right;
                    if(child.getNumChildren() > 0){
                        //child has children

                        left = current.children[current.getNumChildren() - 1] = new Node(child.data[0], false);
                        right = current.children[current.getNumChildren()] = new Node(child.data[2], false);

                        Node[] firstTwoChildren = new Node[current.maxChildren];
                        firstTwoChildren[0] = child.children[0];
                        firstTwoChildren[1] = child.children[1];

                        Node[] lastTwoChildren = new Node[current.maxChildren];
                        lastTwoChildren[0] = child.children[2];
                        lastTwoChildren[1] = child.children[3];

                        left.children = firstTwoChildren;
                        right.children = lastTwoChildren;

                    }else {
                        current.children[current.getNumChildren() - 1] = new Node(child.data[0], true);
                        current.children[current.getNumChildren()] = new Node(child.data[2], true);
                    }
                }
                //System.out.println("PARENT" + " ");
                //current.printData();
                insert(item, current.children[insertIndex]);
            }
        }
    }

    public void inorder(){
        inorder(root);
        System.out.println();
    }

    public void inorder(Node node){
        //print left child, print data[0], print next child, print data[1]
        switch (node.getNumChildren()){
            case 0:
                //no children = leaf, print all data values
                for(int i = 0; i < node.numItems; i++){
                    System.out.print(node.data[i] + " ");
                }
                break;
            case 2 :
                inorder(node.children[0]);
                System.out.print(node.data[0] + " ");
                inorder(node.children[1]);
                //2 children --> 1 data value
                break;
            case 3 :
                //3 children --> 2 data values
                inorder(node.children[0]);
                System.out.print(node.data[0] + " ");
                inorder(node.children[1]);
                System.out.print(node.data[1] + " ");
                inorder(node.children[2]);
                break;
            case 4 :
                //4 children --> 3 data values
                inorder(node.children[0]);
                System.out.print(node.data[0] + " ");
                inorder(node.children[1]);
                System.out.print(node.data[1] + " ");
                inorder(node.children[2]);
                System.out.print(node.data[2] + " ");
                inorder(node.children[3]);
                break;
        }

    }

    public void delete(int key){
        delete(key, root);
    }

    public void delete(int key, Node node){
        //delete key
        if(isEmpty()){
            //tree has no nodes
            System.out.println("Tree is empty, cannot delete key");
            return;
        }
        Node current = search(key, node);
        if(current == null){
            System.out.println("Key does not exist");
            return;
        }

        int index = current.numItems - 1;
        for(int i = 0; i < current.numItems; i++){
            if(current.data[i] == key){
                index = i;
                break;
            }
        }

        if(current.isLeaf){
            for(int i = index; i < current.numItems - 1; i++){
                //shift to the left
                current.data[i] = current.data[i + 1];
            }
            current.numItems--;
            return;
        }

        int inorderSuccessor;
    }

    public void preorder(){
        preorder(root);
        System.out.println();
    }

    public void preorder(Node node){
        switch (node.getNumChildren()){
            case 0:
                //no children = leaf, print all data values
                for(int i = 0; i < node.numItems; i++){
                    System.out.print(node.data[i] + " ");
                }
                break;
            case 2 :
                System.out.print(node.data[0] + " ");
                preorder(node.children[0]);
                preorder(node.children[1]);
                //2 children --> 1 data value
                break;
            case 3 :
                //3 children --> 2 data values
                System.out.print(node.data[0] + " ");
                preorder(node.children[0]);
                System.out.print(node.data[1] + " ");
                preorder(node.children[1]);
                preorder(node.children[2]);
                break;
            case 4 :
                //4 children --> 3 data values
                System.out.print(node.data[0] + " ");
                preorder(node.children[0]);
                System.out.print(node.data[1] + " ");
                preorder(node.children[1]);
                System.out.print(node.data[2] + " ");
                preorder(node.children[2]);
                preorder(node.children[3]);
                break;
        }

    }

    public void postorder(){
        postorder(root);
        System.out.println();
    }

    public void postorder(Node node){
        switch(node.getNumChildren()){
            case 0:
                //no children = leaf, print all data values
                for(int i = 0; i < node.numItems; i++){
                    System.out.print(node.data[i] + " ");
                }
                break;
            case 2:
                postorder(node.children[0]);
                postorder(node.children[1]);
                System.out.print(node.data[0] + " ");
                //2 children --> 1 data value
                break;
            case 3:
                //3 children --> 2 data values
                postorder(node.children[0]);
                postorder(node.children[1]);
                System.out.print(node.data[0] + " ");
                postorder(node.children[2]);
                System.out.print(node.data[1] + " ");
                break;
            case 4:
                postorder(node.children[0]);
                postorder(node.children[1]);
                System.out.print(node.data[0] + " ");
                postorder(node.children[2]);
                System.out.print(node.data[1] + " ");
                postorder(node.children[3]);
                System.out.print(node.data[2] + " ");
                break;
        }
    }

    public boolean isEmpty(){
        return root == null;
    }
}