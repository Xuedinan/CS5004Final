// import java.util.Iterator;

import java.util.function.Predicate;

public class DoublyLinkedList<T> {

    // node inner class to store value
    static class  Node<T> {
        Node<T> prev; // previous node
        T people; // people
        Node<T> next; // next node

        public Node(DoublyLinkedList.Node<T> prev, T people, DoublyLinkedList.Node<T> next) {
            this.prev = prev;
            this.people = people;
            this.next = next;
        }
    }
    
    protected Node<T> llHead; // head sentinel of linked list
    protected Node<T> llTail; // tail sentinel of linked list

    // constructor for empty linked list
    public DoublyLinkedList(){
        llHead = new Node<T>(null, null, null);
        llTail = new Node<T>(null, null, null);
        // connect head and tail
        llHead.next = llTail;
        llTail.prev = llHead;
    }

    // find index for input people name by recursion

    public int findIndexByPeople(People target) {
        // start searching
        int index = recursiveFindIndexByPeople(target, llHead.next, 0);
        if (index == -1) {
            System.out.println("Person not found.");
        }
        return index;
    }

    private int recursiveFindIndexByPeople(People target, Node<T> currentNode, int currentIndex) {
        // base case when reach to tail
        if (currentNode == llTail) {
            return -1; // return to -1 when not find
        }
        // check if name is matching
        if (((People) currentNode.people).getName().trim().replaceAll("\\s+", " ").equalsIgnoreCase(target.getName().trim().replaceAll("\\s+", " "))) {
            return currentIndex;
        }
        // recursive check next
        return recursiveFindIndexByPeople(target, currentNode.next, currentIndex + 1);
    }

    // find node by index
    private Node<T> findNode(int index){
        // starting from -1, because we have head sentinel
        int i = -1;
        for (Node<T> p = llHead; p != llTail; p =p.next, i++) {
            if (i == index) {
                // return when find index
                return p;
            }
        }
        System.out.println("Can't find the node. \n");
        return null;
    }

    // insert node by index
    public void insert(int index, T people){
        // get prev and next node of insert position
        Node<T> prevNode = findNode(index - 1);
        // handle exception
        if (prevNode == null) {
           throw illegalIndex(index);
        }
        Node<T> nextNode = prevNode.next;
        // create inserted node
        Node<T> inserted = new Node<T>(prevNode, people, nextNode);
        // connect the pointer of inserted node
        prevNode.next = inserted;
        nextNode.prev = inserted;
    }

    // add people to head of linked list
    public void addFirst(T people){
        insert(0, people);
    }

    // add people to the end of linked list
    public void addLast(T employee){
        // get the original last node
        Node<T> last = llTail.prev;
        Node<T> added = new Node<T>(last, employee, llTail);
        // connect original last to new new node then maintain tail
        last.next = added;
        llTail.prev = added;
    }

    // remove people by index
    public void remove(int index){
        // get prev and next node of removed position
        Node<T> prevNode = findNode(index - 1);
        if (prevNode == null) {
            throw illegalIndex(index);
        }
        // check if it's tail sentinel
        Node<T> removeNode = prevNode.next;
        if (removeNode == llTail) {
            throw illegalIndex(index);   
        }
        Node<T> nextNode = removeNode.next;
        // connect the left nodes
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // helper method to throw exception
    private IllegalArgumentException illegalIndex(int index){
        return new IllegalArgumentException(String.format("index[%d] is invalid", index));
    }

    public void printList() {
        Node<T> current = llHead.next;
        while (current != llTail) {
            // only print out non-null people
            if (current.people != null) {
                System.out.println(current.people);
            }
             // Using toString() for printing
            current = current.next;
        }
    }

    // filter list by provided predicate
    public DoublyLinkedList<T> filterByPredicate(Predicate<T> predicate) {
        DoublyLinkedList<T> filteredList = new DoublyLinkedList<>();
        Node<T> currentNode = llHead.next;
        while (currentNode != llTail) { // Iterate through the list
            if (predicate.test(currentNode.people)) { // Check if the current node matches the predicate
                filteredList.addLast(currentNode.people); // Add matching elements to the new list
            }
            currentNode = currentNode.next; // Move to the next node
        }
        return filteredList;
    }
    // count total nodes number
    public int countNodes() {
        int count = 0;
        Node<T> current = llHead.next; // Start with the first element after the head sentinel
        while (current != llTail) { // Iterate until the tail sentinel is reached
            count++; // Increment the count for each node
            current = current.next; // Move to the next node
        }
        return count; // Return the total count
    }
    // count people by predicate
    public int countNodesByPredicate(Predicate<T> predicate) {
        int count = 0;
        Node<T> current = llHead.next; // Start from the first actual element
        while (current != llTail) { // Continue until the tail sentinel is reached
            if (predicate.test(current.people)) { // Check if the current node matches the predicate
                count++; // Increment the count for matching nodes
            }
            current = current.next; // Move to the next node
        }
        return count; // Return the total count of matching nodes
    }
}
    