

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Node {

    long item, height;
    Node left, right;

    Node(long d) {
        item = d;
        height = 1;
    }
}

// Tree class
class BinarySearch implements Runnable{

    Node root;
    int n;
    long[] array;

    public BinarySearch(Node root,int n,long[] array2){
        this.n=n;
        this.array=array2;
        // this.root=root;
    }
    public BinarySearch() {
    }
    @Override
    public void run() {
        for (int i=0;i<n;i++){
            root=insertNode(root,array[i]);
        }
    }

    long height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private int max(long height, long height2) {
        return 0;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor of a node
    long getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a node
    Node insertNode(Node node, long array2) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(array2));
        if (array2 < node.item)
            node.left = insertNode(node.left, array2);
        else if (array2 > node.item)
            node.right = insertNode(node.right, array2);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left),
                height(node.right));
        long balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 && array2< node.left.item) {
            return rightRotate(node);
        }

        if (balanceFactor < -1 && array2 > node.right.item) {
            return leftRotate(node);
        }
        if (balanceFactor > 1 && array2 > node.left.item) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balanceFactor < -1 && array2 < node.right.item) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    Node nodeWithMimumValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node
    Node findTheNode(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (item < root.item)
            root.left = findTheNode(root.left, item);
        else if (item > root.item)
            root.right = findTheNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = nodeWithMimumValue(root.right);
                root.item = temp.item;
                root.right = findTheNode(root.right, temp.item);
            }
        }
        if (root == null)
            return root;

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        long balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }
    long maxDepth(Node node)
    {
        if (node == null)
            return 0;
        else {
            /* compute the depth of each subtree */
            long lDepth = maxDepth(node.left);
            long rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    protected Node findTheNode(Node right, long item) {
        return null;
    }
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.item + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }



    // Driver code
    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("we are finding first index in array in the tree");
        System.out.println("Note: time is in ns");

        ArrayList<Integer> new_arr= new ArrayList<>();
        System.out.println("--------------Multithreading USING 2 thread---------------------");
        
        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("sample.txt"));
			String line = reader.readLine();

			while (line != null) {
				new_arr.add(Integer.parseInt(line));
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        int[] givenarray={0,0,0,0};

        for(int i=0;i<new_arr.size();i++){
            givenarray[i]=new_arr.get(i);
        }
        
        
        
        for (int jk=0;jk<new_arr.size();jk++){
            long[] array1=new long[givenarray[jk]/2];
            long[] array2=new long[givenarray[jk]/2];
            long findvalue=0;
            for (int i=0;i<givenarray[jk]/2;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array1[i]=ran;
            }
            for (int i=0;i<givenarray[jk]/2;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array2[i]=ran;
            }
            findvalue=array1[3];
            System.out.println("for "+givenarray[jk]);
            System.out.println();
            System.out.println("Multithreading");
            BinarySearch tree= new BinarySearch();
            BinarySearch lefttree = new BinarySearch(tree.root,givenarray[jk]/2,array1);
            BinarySearch righttree=new BinarySearch(lefttree.root,givenarray[jk]/2, array2);
            long starttime=System.nanoTime();
            Thread t1=new Thread(lefttree);
            Thread t_1= new Thread(righttree);
            t1.start();t_1.start();
            t1.join();t_1.join();
            long endtime=System.nanoTime();
            ArrayList<String> str=new ArrayList<>();
            str.add("Time taken to construct tree");
            str.add("Height of BBT");
            str.add("time taken to find node");

            Array<String> str_arr=new Array(3);
            for(int i=0;i<3;i++){
                str_arr.set(i, str.get(i));
            }

            Array<Integer> int_arr= new Array(3);
            int_arr.set(0,(endtime-starttime));
            int_arr.set(1,righttree.maxDepth(righttree.root));





            /////////////////////////////////////////////////////////////////////////////////////////////
            starttime=System.nanoTime();
            Findnode findnode1=new Findnode(lefttree,findvalue);
            Findnode findnode2=new Findnode(righttree,findvalue);
            Thread t2=new Thread(findnode1);
            Thread t_2=new Thread(findnode2);
            t2.start();t_2.start();
            t2.join();t_2.join();
            endtime=System.nanoTime();
            // System.out.println("Time taken to find the node : "+(endtime-starttime));
            int_arr.set(2,(endtime-starttime));

            System.out.println(str_arr);
            ///////////////////////////////////////////////Height////////////////////////////////////////
            System.out.println(int_arr);

            tree=righttree;
            System.out.println();
            System.out.println();
            /////////////////////////////////////without threading///////////////////////////////////
            System.out.println("Without Multithreading");
            long[] arraywithout=new long[givenarray[jk]];
            for (int i=0;i<givenarray[jk];i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                arraywithout[i]=ran;
            }
            BinarySearch treewithout= new BinarySearch();
            starttime=System.nanoTime();
            for (int i=0;i<givenarray[jk];i++){
                treewithout.root=treewithout.insertNode(treewithout.root,arraywithout[i]);
            }
            endtime=System.nanoTime();
            System.out.println("Time taken to construct tree : "+(endtime-starttime));
            ///////////////////////////////////////////Height///////////////////////////////////
            System.out.println("Height of BBT : "+righttree.maxDepth(righttree.root));
            ///////////////////////////////////////////find/////////////////////////////////////
            starttime=System.nanoTime();
            treewithout.root=treewithout.findTheNode(treewithout.root,findvalue);
            endtime=System.nanoTime();
            System.out.println("Time taken to find the node :"+(endtime-starttime));


            System.out.println("--------------------------------------------------------------"+(jk));
        }
        System.out.println("--------------Multithreading USING 4 thread---------------------");
        for (int jk=0;jk<4;jk++){
            long[] array1=new long[givenarray[jk]/4];
            long[] array2=new long[givenarray[jk]/4];
            long[] array3=new long[givenarray[jk]/4];
            long[] array4=new long[givenarray[jk]/4];
            long findvalue=0;
            for (int i=0;i<givenarray[jk]/4;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array1[i]=ran;
            }
            for (int i=0;i<givenarray[jk]/4;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array2[i]=ran;
            }
            for (int i=0;i<givenarray[jk]/4;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array3[i]=ran;
            }
            for (int i=0;i<givenarray[jk]/4;i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                array4[i]=ran;
            }
            findvalue=array1[1];
            System.out.println("for "+givenarray[jk]);
            System.out.println();
            System.out.println("Multithreading");
            BinarySearch tree= new BinarySearch();
            BinarySearch left2tree = new BinarySearch(tree.root,givenarray[jk]/4,array1);
            BinarySearch right2tree=new BinarySearch(left2tree.root,givenarray[jk]/4, array2);
            BinarySearch leftrighttree=new BinarySearch(right2tree.root,givenarray[jk]/4, array3);
            BinarySearch rightlefttree=new BinarySearch(leftrighttree.root,givenarray[jk]/4, array4);
            long starttime=System.nanoTime();
            Thread t21=new Thread(left2tree);
            Thread t_21= new Thread(right2tree);
            Thread t22=new Thread(leftrighttree);
            Thread t_22= new Thread(rightlefttree);
            t21.start();t_21.start();t22.start();t_22.start();
            t21.join();t_21.join();t22.join();t_22.join();
            long endtime=System.nanoTime();
            System.out.println("Time taken to construct tree : "+(endtime-starttime));
            ///////////////////////////////////////////////Height////////////////////////////////////////
            System.out.println("Height of BBT : "+right2tree.maxDepth(right2tree.root));

            /////////////////////////////////////////////////////////////////////////////////////////////
            starttime=System.nanoTime();
            Findnode findnode1=new Findnode(left2tree,findvalue);
            Findnode findnode2=new Findnode(right2tree,findvalue);
            Findnode findnode3=new Findnode(leftrighttree,findvalue);
            Findnode findnode4=new Findnode(rightlefttree,findvalue);
            Thread tt1=new Thread(findnode1);
            Thread tt2=new Thread(findnode2);
            Thread tt3=new Thread(findnode3);
            Thread tt4=new Thread(findnode4);
            tt1.start();tt2.start();tt3.start();tt4.start();
            tt1.join();tt2.join();tt3.join();tt4.join();;
            endtime=System.nanoTime();
            System.out.println("Time taken to find the node : "+(endtime-starttime));
            tree=right2tree;
            System.out.println();
            System.out.println();
            /////////////////////////////////////without threading///////////////////////////////////
            System.out.println("Without Multithreading");
            long[] arraywithout=new long[givenarray[jk]];
            for (int i=0;i<givenarray[jk];i++){
                long ran=(long) (Math.random()*(Math.pow(10,9)-Math.pow(10,-9)+1)+Math.pow(10,-9));
                arraywithout[i]=ran;
            }
            BinarySearch tree2without= new BinarySearch();
            starttime=System.nanoTime();
            for (int i=0;i<givenarray[jk];i++){
                tree2without.root=tree2without.insertNode(tree2without.root,arraywithout[i]);
            }
            endtime=System.nanoTime();
            System.out.println("Time taken to construct tree : "+(endtime-starttime));
            ///////////////////////////////////////////Height///////////////////////////////////
            System.out.println("Height of BBT : "+right2tree.maxDepth(right2tree.root));
            ///////////////////////////////////////////find/////////////////////////////////////
            starttime=System.nanoTime();
            tree2without.root=tree2without.findTheNode(tree2without.root,findvalue);
            endtime=System.nanoTime();
            System.out.println("Time taken to find the node :"+(endtime-starttime));


            System.out.println("--------------------------------------------------------------"+(jk));
        }




    }
}

class Array<E>{
    private final Object[] objectarray;
    public final int length;

    public Array(int length){
        objectarray= new Object [length];
        this.length=length;
    }

    public void set(int i, String string) {
        objectarray[i]=string;
    }

    void set(int i,long l){
        objectarray[i]=l;
    }

    void set2(int i,String l){
        objectarray[i]=l;
    }

    @Override
    public String toString() {
        return Arrays.toString(objectarray);
    }
}

class Findnode extends BinarySearch{
    Node root;
    BinarySearch tree;
    long find;
    Findnode(BinarySearch tree,long find){
        this.tree=tree;
        this.find=find;
    }
    @Override
    public void run() {
        tree.findTheNode(tree.root, find);
    }

}
