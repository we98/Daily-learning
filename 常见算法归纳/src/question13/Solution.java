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
 * ʹ����������ʵ��ջ
 * push��ʱ��������data��push
 * pop��ʱ�򣬽�data�е�Ԫ��pop��help�У�ֻʣ��һ����������һ�����У�ͬʱ����data��help
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
 * ʹ������ջʵ�ֶ���
 * add��ʱ��ֻ����pushջ��ӣ���pollֻ�ܴ�popջ��ȡ����popջΪ��ʱ���ٽ�pushջ�������Ԫ�ؼ���popջ�������κ�ʱ�䶼���ܽ�pushջ�������Ԫ�ؼ���popջ
 * ע���������ƣ����pushҪ�����ݣ�һ��һ�����ꣻ���⣬���popջ���ж�����pushջһ�����ܵ���ֻҪ�����������淶���ɡ�
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
