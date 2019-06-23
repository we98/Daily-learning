package question13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		StackByQueue stackByQueue = new StackByQueue();
		stackByQueue.push(1);
		stackByQueue.push(2);
		stackByQueue.pop();
		stackByQueue.push(3);
		System.out.println("============");
		QueueByStack queueByStack = new QueueByStack();
		queueByStack.add(1);
		queueByStack.add(2);
		System.out.println(queueByStack.poll());
		queueByStack.add(3);
		System.out.println(queueByStack.poll());
	}
}

/**
 * 使用两个队列实现栈
 * push的时候总是向data中push
 * pop的时候，将data中的元素pop到help中，只剩下一个，返回这一个就行，同时交换data和help
 * 
 * @author CGWEI
 *
 */
class StackByQueue{
	private Queue<Integer> data;
	private Queue<Integer> help;
	public StackByQueue() {
		data = new LinkedList<>();
		help = new LinkedList<>();
	}
	public void push(int value) {
		data.add(value);
		System.out.println(this);
	}
	public int pop() {
		if(data.size() == 0) {
			throw new RuntimeException("Stack is empty.");
		}
		while(data.size() != 1) {
			help.add(data.poll());
		}
		swap();
		System.out.println(this);
		return help.poll();
	}
	public int peek() {
		if(data.size() == 0) {
			throw new RuntimeException("Stack is empty.");
		}
		return data.peek();
	}
	private void swap() {
		Queue<Integer> temp = data;
		data = help;
		help = temp;
	}
	@Override
	public String toString() {
		return data.toString();
	}
}

/**
 * 使用两个栈实现队列
 * add的时候只能往push栈里加，而poll只能从pop栈里取，当pop栈为空时，再将push栈里的所有元素加入pop栈，其余任何时间都不能将push栈里的所有元素加入pop栈
 * 注意两个限制，如果push要倒数据，一次一定倒完；另外，如果pop栈里有东西，push栈一定不能倒，只要满足这连个规范即可。
 * 
 * @author HP
 *
 */
class QueueByStack{
	private Stack<Integer> push;
	private Stack<Integer> pop;
	public QueueByStack() {
		push = new Stack<>();
		pop = new Stack<>();
	}
	public void add(int value) {
		push.push(value);
	}
	public int poll() {
		if(push.isEmpty() && pop.isEmpty()) {
			throw new RuntimeException("Queue is empty.");
		}
		if(pop.isEmpty()) {
			while(!push.isEmpty()) {
				pop.push(push.pop());
			}	
		}
		return pop.pop();
	}
}
