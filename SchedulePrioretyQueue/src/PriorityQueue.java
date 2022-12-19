import java.awt.*;

/**
 * @author Spencer Potter
 * a class that creates a leftest heap priority queue
 *
 * @param <E> E is a generic and will be used to make the priority queue leftest heap work
 */
public class PriorityQueue<E extends Comparable<? super E>> {

    private Node<E> root;
    //consturctor
    public PriorityQueue(){
        root = null;
    }

    //insert code

    /**
     * @author Spencer Potter
     * this method is used to insert a node into the queue. but really all it does is merges that node witht the queue
     *
     * @param item - a generic item that will be turned into a node to go into the queue
     */
    public void insert(E item){
        Node<E> nodeToInsert = new Node<>(item);
        //literally just merging the new Node "tree" with the already made tree
        root = mergeRoot(nodeToInsert, root);
    }


    //Merge code

    /**
     * @author Spencer Potter
     * the first method  in mergeing a tree to another tree
     *
     *
     * @param mergeTree another priority queue tree with the same generic as this tree
     */
    public void merge(PriorityQueue<E> mergeTree){
        //makes sure its not the same tree
        if(this==mergeTree){
            return;
        }
        root = mergeRoot(root, mergeTree.root);
        mergeTree.root=null;

    }

    /**
     * @author Spencer Potter
     * this method starts by comparing the two root nodes of each tree then merges the smaller one to the greater one
     *
     * @param originalRoot a Node with the generic E,
     * @param mergeRoot a Node with the generic E that we wish to merge to the original root
     * @return This returns the new root of the tree once both trees are merged
     */
    private Node<E> mergeRoot(Node<E> originalRoot, Node<E> mergeRoot){
        //check to see if either of the roots are null
        if(originalRoot == null){
            return mergeRoot;
        }
        if(mergeRoot == null){
            return originalRoot;
        }

        //compare the two roots and have the smaller root merge to the larger one
        if(originalRoot.element.compareTo(mergeRoot.element)<0){
            return mergeNodes(originalRoot,mergeRoot);
        }
        return mergeNodes(mergeRoot, originalRoot);
    }


    /**
     * @author Spencer Potter
     * this is a recursive method that merges the children of the root node and also swaps the children if the right is
     * heavier then the left side of the tree
     *
     * @param originalNode a Node with the generic E,
     * @param mergeNode a Node with the generic E that we wish to merge to the original Node
     * @return returns the node after the two nodes are merged
     */
    private Node<E> mergeNodes(Node<E> originalNode, Node<E> mergeNode){
        if(originalNode.left == null){
            originalNode.left = mergeNode;
        }
        else{
            originalNode.right = mergeRoot(originalNode.right, mergeNode);
            //swap children if the right child has the longer path to null
            if(originalNode.left.depthToNull < originalNode.right.depthToNull){
                Node<E> temp = originalNode.left;
                originalNode.left = originalNode.right;
                originalNode.right = temp;
            }
            originalNode.depthToNull = originalNode.right.depthToNull+1;
        }
        return originalNode;
    }

    //delete the minimun node
    /**
     * @author Spencer Potter
     * deletes the minimum node
     *
     * @return returns the item that was in the minimun node
     */
    public E deleteMin(){
        E minElement = root.element;
        root = mergeRoot(root.left, root.right);
        return minElement;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * @author Spencer Potter
     * this is the node class for the priority queue.
     *
     * @param <E> a generic that can be any type
     */
    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        int depthToNull = 0;

        Node(E element){
            this(element, null, null);
        }
        Node(E element, Node<E> left, Node<E> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}


