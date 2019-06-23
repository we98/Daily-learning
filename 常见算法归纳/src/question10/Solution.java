package question10;



/**
 * 用数组实现栈，只需要加入一个变量用来指示当前栈顶元素即可
 * 用数组实现队列，理论上使用一个first和一个last就能解决问题，但是只使用这两个变量的话队列中实际元素的数量计算太过于复杂
 * 因此引入实际元素的数量size，first只需要与size搭配使用，last也只需要与size交互，因此实现了first与last的解耦，逻辑更加清晰
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		MyQueue myQueue = new MyQueue(3);
		myQueue.push(1);
		myQueue.push(2);
		myQueue.push(3);
		myQueue.pop();
		myQueue.push(1);
		myQueue.pop();
		myQueue.push(5);
		System.out.println(myQueue);
	}
}
class MyStack{
	private int[] elements;
	private int index = -1;
	public MyStack(int initialSize) {
		if(initialSize <= 0) {
			throw new RuntimeException("wrong initial size.");
		}
		elements = new int[initialSize];
	}
	public void push(int value) {
		if(index+1 == elements.length) {
			throw new RuntimeException("stack is full.");
		}
		elements[++index] = value;
	}
	public int pop() {
		if(index == -1) {
			throw new RuntimeException("stack is empty.");
		}
		return elements[index--];
	}
	public int peek() {
		if(index == -1) {
			throw new RuntimeException("stack is empty.");
		}
		return elements[index];
	}
}

class MyQueue{
	int[] elements;
	int first;
	int last;
	int size;
	public MyQueue(int initialValue) {
		if(initialValue <= 0) {
			throw new RuntimeException("wrong initial size.");
		}
		elements = new int[initialValue];
	}
	public void push(int value) {
		if(size == elements.length) {
			throw new RuntimeException("queue is full.");
		}
		size++;
		elements[last] = value;
		last = (last+1 == elements.length ? 0 : last+1);
	}
	public int pop() {
		if(size == 0) {
			throw new RuntimeException("queue is empty.");
		}
		size--;
		int returnValue = elements[first];
		first = (first+1 == elements.length ? 0 : first+1);
		return returnValue;
	}
	@Override
	public String toString() {
		if(size == 0) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder(size*2);
		for(int i = 0; i < size; i++) {
			stringBuilder.append(elements[first]);
			stringBuilder.append('\t');
			first = (first+1 == elements.length ? 0 : first+1);
		}
		return stringBuilder.toString();
	}
}