/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        // noinspection unchecked
        arr = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == arr.length) resize(arr.length * 2);
        arr[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        int randomIndex = StdRandom.uniform(size);
        Item item = arr[randomIndex];
        arr[randomIndex] = arr[size - 1];
        arr[--size] = null;
        if (size > 0 && size == arr.length / 4) resize(arr.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return arr[StdRandom.uniform(size)];
    }


    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = size;
        private final int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for (int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[order[--i]];
        }
    }

    private void resize(int capacity) {
        // noinspection unchecked
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("hello");
        randomizedQueue.enqueue("there");
        randomizedQueue.enqueue("angel");
        randomizedQueue.enqueue("from");
        randomizedQueue.enqueue("my");
        randomizedQueue.enqueue("nightmare");

        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println("Size: " + randomizedQueue.size());
        StdOut.println("Empty: " + randomizedQueue.isEmpty());

        randomizedQueue.enqueue("hello again");

        for (String s : randomizedQueue) {
            StdOut.print(s + " ");
        }

        StdOut.println(randomizedQueue.sample());

        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
