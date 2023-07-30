package com.epam.rd.autocode.hashtableopen816;

public class HashTable implements HashtableOpen8to16{
    private HashNode[] hashArray;
    private int size;
    private int capacity;
    private final static int INITIAL_CAPACITY = 8;
    private final static int MAX_CAPACITY = 16;


    public HashTable() {
        this.hashArray = new HashNode[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    public int hashFunc(int key) {
        return Math.abs(key) % capacity;
    }

    public boolean isEmptyArrayCells() {
        return size < capacity;
    }

    @Override
    public void insert(int key, Object value) {
        int index = hashFunc(key);
        HashNode newNode = new HashNode(key, value);
        if(search(key) == null) {
            while(hashArray[index] != null) {
                if(!isEmptyArrayCells()) {
                    increaseCapacity();
                    index = hashFunc(key);
                } else {
                    ++index;
                    if(index == capacity){
                        index = 0;
                    }
                    if(size == MAX_CAPACITY) {
                        throw new IllegalStateException();
                    }
                }
            }
            hashArray[index] = newNode;
            size++;
        }

    }

    @Override
    public Object search(int key) {
        int index = hashFunc(key);
        for (int i = 0; i < capacity; i++) {
            if (hashArray[index] != null) {
                if (hashArray[index].getKey() == key) {
                    return hashArray[index].getValue();
                } else {
                    ++index;
                }
            } else {
                ++index;
            }
            if (index == capacity) {
                index = 0;
            }
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int index = hashFunc(key);
        for (int i = 0; i < capacity + 1; i++) {
            if(hashArray[index] != null){
                if(hashArray[index].getKey() == key) {
                    hashArray[index] = null;
                    size--;
                    break;
                } else {
                    ++index;
                }
            } else {
                ++index;
            }
            if (index == capacity) {
                index = 0;
            }
            if(i == capacity) {
                break;
            }
        }
        decreaseCapacity();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[hashArray.length];
        for (int i = 0; i < hashArray.length; i++) {
            if(hashArray[i] != null) {
                keys[i] = hashArray[i].getKey();
            }
            else {
                keys[i] = 0;
            }
        }
        return keys;
    }

    private void increaseCapacity() {
        HashNode[] temp = new HashNode[capacity];
        System.arraycopy(hashArray, 0, temp, 0, hashArray.length);
        capacity = capacity * 2;
        hashArray = new HashNode[capacity];
        size = 0;
        for (HashNode hashNode : temp) {
            if (hashNode != null) {
                insert(hashNode.getKey(), hashNode.getValue());
            }
        }
    }

    private void decreaseCapacity() {
        if(size > 0 && size == (capacity / 4)) {
            HashNode[] temp = new HashNode[(capacity)];
            System.arraycopy(hashArray, 0, temp, 0, hashArray.length);
            capacity = capacity / 2;
            hashArray = new HashNode[capacity];
            size = 0;
            for (HashNode hashNode : temp) {
                if (hashNode != null) {
                    insert(hashNode.getKey(), hashNode.getValue());
                }
            }
        }
    }
}