/*Time Complexity
 push:  O(1)
 pop:   O(1) avg
        worst case is O(N) that is when output stack is empty and we need to transfer all the elements in out stack.
 peek:   O(1) avg
 empty: O(1)
*/

/*Space Complexity
push:   O(n) as the number of elements each will occupy a space in memory
pop:    O(1)
peek:   O(1)
empty:  O(1)
*/

/*
Leetcode : https://leetcode.com/problems/implement-queue-using-stacks/

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
Implement the MyQueue class:
void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
*/

/* /
Code explanation:
Queue is FIFO and Stack is LIFO

We will need two stacks. One for the incoming data and other to store as outgoing data.
IF the incoming stack is popped out and pushed to outgoing stack element by element, the top of ougoing stack is the first element entered into the queue 
which is also coming out , therefore behaving as a queue.

We need to move all data from incoming into outgoing stack, if the out stack is empty. If the out stack is not empty, the pop operation
is simply performed on it. The time complexity involved with pop and peek in worst case is O(elements in stack), as we push
one element at a time from input to output stack, however as this only happens when stack is empty so the avg time complexity is O(1).
 */

import java.util.Stack;

class MyQueue{
    private Stack<Integer> in;
    private Stack<Integer> out;

    public MyQueue(){
        this.in = new Stack<>();
        this.out = new Stack<>();
    }

    public void push (int x){
        in.push(x);
    }

    public int pop(){
        peek();
        return out.pop();
    }

    public int peek(){
        if(out.isEmpty()){
            while(!in.isEmpty()){
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    public boolean empty(){
        return in.isEmpty() && out.isEmpty();
    }
}