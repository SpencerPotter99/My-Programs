/**
 * @author spencerpotter
 *
 * This class creates a queue style linked list for generic types
 * @param <E> this will be a generic
 */

public class Queue<E> {

    /**
     * sets up an empty queue
     */
    public Queue() {
        head = tail = null;
    }

    /**
     * adds a node of a generic value to the queue
     * @param value will be the same type as the queue that it is being enqueued too
     */
    public void enqueue(E value) {
        if (tail != null) {
            tail.next = new Node(value, null);
            tail = tail.next;
        } else {
            tail = new Node(value, null);
            head = tail;
        }
    }

    /**
     * pops the first Node in the queue
     * @return will return the value of the first Node in the queue
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue - Queue is empty");
        }

        E value = head.value;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return value;
    }


    /**
     * checks to see if the queue is empty
     * @return a boolean that represents if the queue is empty
     */
    public boolean isEmpty() {
        if(head == null) {
            return true;
        }
        return false;
    }


    /**
     * this method prints all the values in the queue
     */
    public void print(){
        Node c = head;

        while (c != null){
            System.out.println(c.value.toString());
            c = c.next;
        }
    }

    /**
     * this method prints the first N values in the queue
     * @param N and integer that represents the amount of values the user wishes to print
     */
    public void print(int N){
        Node c = head;
        for( int x = 0; x < N; x++){
            if(c==null){
                return;
            }
            System.out.println(c.value.toString());
            c = c.next;
        }
    }

    /**
     * @author spencerpotter
     *
     * This class creates a Node with generic values
     * @param <E> this will be a generic
     */
    private class Node<E> {
        E value;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
}
