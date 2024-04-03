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
    int count = 0;

    public void add(T element) {
        Node<T> newNode = new Node<T>();
        newNode.value = element;
        newNode.next = null;
        if (first == null) {
            first = newNode;
        } else {
            Node<T> lastNode = goToLastNode(first);
            lastNode.next = newNode;
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

    public T get(int pos)

    {
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

    public int size() {
        return count;
    }
}
