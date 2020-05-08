/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }


    public Deque() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        if (isEmpty()) {
            first = new Node(item);
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        if (isEmpty()) {
            last = new Node(item);
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node(item);
            last.prev = oldLast;
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        else {
            last = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        size--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException("Method not supported");
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("Stack underflow");
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {
        Deque<String> stringDeque = new Deque<>();
        System.out.println("size: " + stringDeque.size());
        System.out.println("Is empty: " + stringDeque.isEmpty());
        stringDeque.addFirst("B");
        stringDeque.addFirst("A");
        stringDeque.addLast("Y");
        stringDeque.addLast("Z");
        for (String s : stringDeque) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("size: " + stringDeque.size());
        System.out.println("Is empty: " + stringDeque.isEmpty());
        System.out.println(stringDeque.removeLast());
        System.out.println(stringDeque.removeLast());
        System.out.println(stringDeque.removeLast());
        System.out.println(stringDeque.removeFirst());
        System.out.println("size: " + stringDeque.size());
        System.out.println("Is empty: " + stringDeque.isEmpty());
    }
}

