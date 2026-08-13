import java.util.*;

class LFUCache {

    class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeLast() {
            if (tail.prev == head)
                return null;

            Node node = tail.prev;
            remove(node);
            return node;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    int capacity;
    int size;
    int minFreq;

    HashMap<Integer, Node> nodes;
    HashMap<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;

        nodes = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!nodes.containsKey(key))
            return -1;

        Node node = nodes.get(key);
        increaseFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (nodes.containsKey(key)) {
            Node node = nodes.get(key);
            node.value = value;
            increaseFrequency(node);
            return;
        }

        if (size == capacity) {
            DoublyLinkedList list = freqMap.get(minFreq);
            Node removed = list.removeLast();

            nodes.remove(removed.key);
            size--;
        }

        Node node = new Node(key, value);

        nodes.put(key, node);

        freqMap.putIfAbsent(1, new DoublyLinkedList());
        freqMap.get(1).addFirst(node);

        minFreq = 1;
        size++;
    }

    private void increaseFrequency(Node node) {
        int oldFreq = node.freq;

        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;

        freqMap.putIfAbsent(node.freq, new DoublyLinkedList());
        freqMap.get(node.freq).addFirst(node);
    }
}