package schooldatabase.model;

interface Comparable<T> {
    int compareTo(T o);
}

public class GenericList<T extends Comparable<T>> {

    @SuppressWarnings("hiding")
    private class Node<T> {
        T value;
        Node<T> next;
    }

    private Node<T> first = null;
    private Node<T> last = null;
    int count = 0;

    public void add(T element) {
        Node<T> newNode = new Node<T>();
        newNode.value = element;
        newNode.next = null;
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        count++;
    }

    public void add(T element, int pos) {
        Node<T> newNode = new Node<T>();
        newNode.value = element;
        newNode.next = null;
        if (first == null) {
            first = newNode;
        } else {
            Node<T> Nodeptr = first;
            Node<T> prevNode = null;
            int hopCount = 0;
            // stops at the before the position to insert the new node
            while (hopCount < count && hopCount < pos) {
                if (Nodeptr != null) {
                    prevNode = Nodeptr;
                    Nodeptr = Nodeptr.next;
                }
                hopCount++;
            }
            if (prevNode == null) {
                newNode.next = first;
                first = newNode;
            } else {
                prevNode.next = newNode;
                newNode.next = Nodeptr;
            }
        }
        count++;
    }

    private Node<T> goToLastNode(Node<T> nodePointer) {
        if (nodePointer == null) {
            return nodePointer;

        } else {
            if (nodePointer.next == null) {
                return nodePointer;
            }

            else {
                return goToLastNode(nodePointer.next);
            }

        }

    }

    public T get(int pos) {
        Node<T> Nodeptr = first;
        int hopCount = 0;
        while (hopCount < count && hopCount < pos) {
            if (Nodeptr != null) {
                Nodeptr = Nodeptr.next;
            }
            hopCount++;
        }
        return Nodeptr.value;

    }

    public void delete(int i) {
        if (i == 0) {
            first = first.next;
        } else {
            Node<T> Nodeptr = first;
            Node<T> prevNode = null;
            int hopCount = 0;
            while (hopCount < count && hopCount < i) {
                if (Nodeptr != null) {
                    prevNode = Nodeptr;
                    Nodeptr = Nodeptr.next;
                }
                hopCount++;
            }
            if (Nodeptr != null) {
                prevNode.next = Nodeptr.next;
            }
        }
        count--;
    }

    public int size() {
        return count;
    }
}
