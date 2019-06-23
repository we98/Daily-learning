package question18;


/**

 * @author CGWEI
 *
 */
public class Solution {
	
	public static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * �õ����������ཻ�����������ú�����Ϊ���¼������裺
	 * ���ж����������л��޻��������һ���л���һ���޻��Ľڵ㲻�����ཻ��
	 * �ڵõ������޻�������ཻ�ڵ㣻
	 * �۵õ������л�������ཻ�ڡ�
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static Node getIntersectNode(Node head1, Node head2) {
		if(head1 == null || head2 == null) {
			return null;
		}
		Node loopNode1 = getLoopNode(head1);
		Node loopNode2 = getLoopNode(head2);
		//��������޻������������޻��������ཻ�ڵ�ĺ�����
		if(loopNode1 == null && loopNode2 == null) {
			return noLoop(head1, head2);
		}
		//��������л������������л��������ཻ�ڵ�ĺ�����
		if(loopNode1 != null && loopNode2 != null) {
			return bothLoop(head1, head2);
		}
		//���һ���л�һ���޻���ֱ�ӷ���null����Ϊ�������ཻ�Ŀ����ԡ�
		return null;
	}
	/**
	 * �ж�һ�������Ƿ��л�������л������뻷�ڵ㣬����޻�������null��
	 * ���裺
	 * ��׼����������ָ�룬һ��һ����һ����һ��һ��������������л�������ָ��϶��������������ָ���ߵ���null��������޻���
	 * �ڵ�����ָ�������󣬽���ָ��ָ��ͷ�ڵ㣬Ȼ������ָ��ÿ�ζ�ͬʱ��һ����������ָ�����ʱ���͵���������뻷�ڵ㣬���ؼ��ɡ�
	 * @param head
	 * @return
	 */
	private static Node getLoopNode(Node head) {
		if(head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while(slow != fast) {
			if(fast.next == null || fast.next.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		//�ﵽ��һ��֮������ָ������
		
		//���Ž���ָ��ָ��ͷ�ڵ�
		fast = head;
		//����ͬʱ����ͬ�Ĳ������϶������뻷�ڵ㴦�����������󷵻ؼ���
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
	/**
	 * ���������޻�����ĵ�һ���ཻ�ڵ㡣
	 * ���裺
	 * �ٱ������������ֱ�õ����Ⱥ����һ���ڵ�length1,end1;length2,end2��
	 * ��������������ཻ��endһ����ͬ�����򣬲��ཻ��ֱ�ӷ���null��
	 * ��ȷ�����������ȵĲ�ֵ���ó����������߲�ֵ��ô��Ĳ�����Ȼ����������ͬʱ�ߣ�������ʱ����ǵ�һ���ཻ�ڵ㡣
	 * @param head1
	 * @param head2
	 * @return
	 */
	private static Node noLoop(Node head1, Node head2) {
		int length1 = 0;
		int length2 = 0;
		Node end1 = head1;
		Node end2 = head2;
		while(end1.next != null) {
			length1++;
			end1 = end1.next;
		}
		while (end2.next != null) {
			length2++;
			end2 = end2.next;
		}
		//����õ���length1��length2��������ʵ�ĳ��ȣ���Ϊ����û�����壬Ҫ�õ����Ƕ��ߵĲ�ֵ
		int difference = Math.abs(length1-length2);
		
//		�ó��ȳ�������һ�ξ���
//		for(int i = 0; i < difference; i++) {
//			if(length1 > length2) {
//				head1 = head1.next;
//			}
//			else {
//				head2 = head2.next;
//			}
//		}
//		
//		��ͬ��һ�ξ���
//		while(head1 != head2) {
//			head1 = head1.next;
//			head2 = head2.next;
//		}
//		return head1;
		//����ע�͵�һ�δ�������µĴ����Ч������Ĵ��븴����end1��end2��ʹ��end1�������ģ�end2������̵ģ������Ͳ�����ѭ�����ж���
		end1 = length1 > length2 ? head1 : head2;
		end2 = end1 == head1 ? head2 : head1;
		for(int i = 0; i < difference; i++) {
			end1 = end1.next;
		}
		while(end1 != end2) {
			end1 = end1.next;
			end2 = end2.next;
		}
		return end1;
	}
	/**
	 * ���������л�����ĵ�һ���ཻ�ڵ㡣
	 * ���裺
	 * ���ж������л�������ཻ����������в��ཻ����һ���ཻ���ڶ����ཻ������Ӧ��ͼ�ϣ���
	 * ���������������뻷�ڵ���ȣ���Ϊ��һ���ཻ�����
	 * ����Ե�һ���ཻ�������ʵ���޻������ཻ���ۻ�����һ�£�ֻ�������㳤�ȵ�ʱ���޻��ǵ�end���л����ǵ��뻷�ڵ㣻
	 * ���������ȣ���Ϊ���ཻ���ߵڶ����ཻ�����
	 * �ݴ�ʱ��loopNode1һֱ�����ߣ�����ڻص��Լ�֮ǰ��û������loopNode2����Ϊ���������ཻ��
	 * �����ཻ��ֱ�ӷ���loopNode1��loopNode2���ɣ���Ϊ���������ǵ�һ���ཻ�ڵ㣬ֻ��������Բ�ͬ������Եġ�
	 * @param head1
	 * @param head2
	 * @return
	 */
	private static Node bothLoop(Node head1, Node head2) {
		Node loopNode1 = getLoopNode(head1);
		Node loopNode2 = getLoopNode(head2);
		if(loopNode1 == loopNode2) {
			int length1 = 0;
			int length2 = 0;
			Node end1 = head1;
			Node end2 = head2;
			while(end1.next != loopNode1) {
				length1++;
				end1 = end1.next;
			}
			while (end2.next != loopNode2) {
				length2++;
				end2 = end2.next;
			}
			//����õ���length1��length2�����ǵ�end�ĳ��ȣ����ǵ�loopNode�ĳ���
			int difference = Math.abs(length1-length2);
			end1 = length1 > length2 ? head1 : head2;
			end2 = end1 == head1 ? head2 : head1;
			for(int i = 0; i < difference; i++) {
				end1 = end1.next;
			}
			while(end1 != end2) {
				end1 = end1.next;
				end2 = end2.next;
			}
			return end1;
		}
		else {
			Node temp = loopNode1.next;
			while(temp != loopNode1) {
				if(temp == loopNode2) {
					return loopNode1;
					//or return loopNode2;
				}
				temp = temp.next;
			}
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		
		// 1->2->3->4->5->6->7->null
				Node head1 = new Node(1);
				head1.next = new Node(2);
				head1.next.next = new Node(3);
				head1.next.next.next = new Node(4);
				head1.next.next.next.next = new Node(5);
				head1.next.next.next.next.next = new Node(6);
				head1.next.next.next.next.next.next = new Node(7);

				// 0->9->8->6->7->null
				Node head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next.next.next.next.next; // 8->6
				System.out.println(getIntersectNode(head1, head2).value);

				// 1->2->3->4->5->6->7->4...
				head1 = new Node(1);
				head1.next = new Node(2);
				head1.next.next = new Node(3);
				head1.next.next.next = new Node(4);
				head1.next.next.next.next = new Node(5);
				head1.next.next.next.next.next = new Node(6);
				head1.next.next.next.next.next.next = new Node(7);
				head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

				// 0->9->8->2...
				head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next; // 8->2
				System.out.println(getIntersectNode(head1, head2).value);

				// 0->9->8->6->4->5->6..
				head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next.next.next.next.next; // 8->6
				System.out.println(getIntersectNode(head1, head2).value);
	}
}