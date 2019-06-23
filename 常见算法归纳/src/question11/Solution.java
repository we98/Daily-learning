package question11;

import java.util.Stack;

/**
 * ��Сջ
 * ʹ������ջʵ�֣�һ����ʵ����������һ������С��
 * ���ʵ������push����Ϊ4,5,6,3,7,2������С����push����Ϊ4,4,4,3,3,2
 * pop�Ĺ�������ջͬ�����ɣ�����Ҫ���κ��߼��ж�
 * @author CGWEI
 *
 */

public class Solution {
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(7);
		minStack.push(3);
		minStack.push(10);
		minStack.push(8);
		minStack.push(-1);
		minStack.push(0);
		minStack.push(9);
		minStack.push(2);
		minStack.push(4);
		System.out.println("=========");
		minStack.pop();
		minStack.pop();
		minStack.pop();
		minStack.pop();
		minStack.pop();
		minStack.pop();
		minStack.pop();
		minStack.pop();
	}
}
class MinStack{
	Stack<Integer> data;
	Stack<Integer> min;
	public MinStack() {
		data = new Stack<>();
		min = new Stack<>();
	}
	public void push(int value) {
		if(data.size() == 0) {
			min.push(value);
		}
		else {
			int currentMin = min.peek();
			if(value < currentMin) {
				min.push(value);
			}
			else {
				min.push(currentMin);
			}
		}
		data.push(value);
		System.out.println(getMin());
	}
	public int pop() {
		min.pop();
		System.out.println(getMin());
		return data.pop();
	}
	public int getMin() {
		return min.peek();
	}
}