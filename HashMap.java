// Time Complexity : worse case O{100) which is nothing but O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : unable to use 2D bool vector as tought in class

/*
Leetcode : https://leetcode.com/problems/design-hashmap/)

Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
*/

// Your code here along with comments explaining your approach

/*
Code explanation : 
Linear Chaining Technique usede below
Hash Map stores key and value pair. One key can have only one value.
Node structure can be used to store both key and value.

Functions put(key,value) , get(key) , remove(key). For all these functions we need to iterate and search for the key. 
So search operation is common in all three functions.

Primary array will store pointers to each linked list.
for 0 to 1,000,000 data, we can have primary array into 10,000 elements and 
seconday array of 100 elements.

As secondary array is linked list, we need to iterate each element from head to find the key.
Therefore, it is better to use smaller elements in linked list. Hence, we are not using
primary array of 1000 elements and secondary array of 1000 elements as we have used in hashset.

Hashfunct gives index to primary array. Function is key % 10000

We need to implement additional search function which will be used by all add, delete, find functions
We are using single linked list and not doubly link list; therefore we need to get
pointer to previous node from search function.

Example of put(3,12) with hash function we will be calculating the index I would have to put this key. 
Hash function is  working on the key not on the value. so key = 3 that is 3 % 10000 = 3. We will go to index 3 and 
see do I have any linked list at index 3. The default value which would be present in primary array would be null. 
The reason for it being null is because it carries reference. Data structure in a data structure is always a reference.
As I see there is not linked list at the particular value, I will initiate a LL and insert a node with 3,12 into LL.
Next time put comes for same index and iterate and add it.
If same key comes in linked list , update the node with new value. 
For get(key, value), calculate the hash function that will give index 3. I will check if index 3 is null, then go homee
return -1. But if it is not null, iterate on the nodes of LL. IF we found it return the value against the key in the node. 
If we are not able to find particular key in LL the pointer would reach null; that means that particular node is not present
we will be returning -1.
 For remove(key, value) we will also search in same way and make use of curr & prev pointers to help remove the node
search function would be returning a reference to the node previous to the one which needs to be deleted. 
Also if the previous pointer ends at tail node it means the  value which need to be searched or deleted does not exist.

Try above using the double hashing technique
*/


class HashMap {
    class Node{
        int key;
        int val;
        Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private Node[] storage; // type of primary array is array  of Nodes ; as it is storing references of Nodes.

    public HashMap(){
        this.storage = new Node[10000]; // initialize the primary array.
    }

    private int hash(int key){
        return key % 10000;
    }

    private Node search(Node head, int key){ // seach f
        Node prev = null;
        Node curr = head; // current node is pointing to the head of the LL

        while(curr != null && curr.key != key){
            prev = curr;
            curr = curr.next;
        }
        return prev;  
    }

    public void put(int key, int value){
        int idx = hash(key); // Call the hash function
        if (storage[idx]  == null){ // this means LL is not initiated, we would have to initiate with dummy node
            storage[idx] = new Node(-1, -1); // dummy node
        }
        Node prev = search(storage[idx], key);
        if(prev.next == null){  // This indicates a fresh node needs to be created
            prev.next = new Node(key, value); // fresh node as it did not existed and added. 
        }else{
            prev.next.val = value;  // update the existing node
        }
    }


    public int get(int key){
        int idx = hash(key);
        if(storage[idx] == null) return -1; // LL does not exist 
        Node prev = search(storage[idx], key);
        if(prev.next == null) return -1; // LL is present but node is not there  ; this means prev is the tail node , and the node is not existing and we return -1
        return prev.next.val;
    }

    public void remove(int key){
        int idx = hash(key);
        if(storage[idx] == null) return; // LL does not exist 
        Node prev = search(storage[idx], key);

        if (prev.next == null) return; // reached at tail node and nothing to delete.
        Node temp = prev.next;
        prev.next = prev.next.next;
        temp.next = null;
    }
}