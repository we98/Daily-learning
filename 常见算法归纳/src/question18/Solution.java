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
	 * 得到两个链表相交的主函数，该函数分为以下几个步骤：
	 * ①判断两个链表有环无环的情况，一个有环和一个无环的节点不可能相交；
	 * ②得到两个无环链表的相交节点；
	 * ③得到两个有环链表的相交节。
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
		//如果都是无环链表，将进入无环链表求相交节点的函数。
		if(loopNode1 == null && loopNode2 == null) {
			return noLoop(head1, head2);
		}
		//如果都是有环链表，将进入有环链表求相交节点的函数。
		if(loopNode1 != null && loopNode2 != null) {
			return bothLoop(head1, head2);
		}
		//如果一个有环一个无环，直接返回null，因为不存在相交的可能性。
		return null;
	}
	/**
	 * 判断一个链表是否有环，如果有环返回入环节点，如果无环，返回null。
	 * 步骤：
	 * ①准备两个快慢指针，一个一次走一步，一个一次走两步，如果有环，两个指针肯定会相遇，如果快指针走到了null，则代表无环；
	 * ②当两个指针相遇后，将快指针指向头节点，然后两个指针每次都同时走一步，当两个指针相等时，就到了链表的入环节点，返回即可。
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
		//达到这一步之后，两个指针相遇
		
		//接着将快指针指向头节点
		fast = head;
		//二者同时走相同的步数，肯定会在入环节点处相遇，相遇后返回即可
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
	/**
	 * 返回两个无环链表的第一个相交节点。
	 * 步骤：
	 * ①遍历两个链表，分别得到长度和最后一个节点length1,end1;length2,end2；
	 * ②如果两个链表相交，end一定相同，否则，不相交，直接返回null。
	 * ③确定两个链表长度的差值，让长的链表先走差值这么大的步数，然后两个链表同时走，相遇的时候就是第一个相交节点。
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
		//这里得到的length1和length2并不是真实的长度，因为长度没有意义，要得到的是二者的差值
		int difference = Math.abs(length1-length2);
		
//		让长度长的先走一段距离
//		for(int i = 0; i < difference; i++) {
//			if(length1 > length2) {
//				head1 = head1.next;
//			}
//			else {
//				head2 = head2.next;
//			}
//		}
//		
//		共同走一段距离
//		while(head1 != head2) {
//			head1 = head1.next;
//			head2 = head2.next;
//		}
//		return head1;
		//以上注释的一段代码跟以下的代码等效，下面的代码复用了end1和end2，使用end1来代表长的，end2来代表短的，这样就不用在循环中判断了
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
	 * 返回两个有环链表的第一个相交节点。
	 * 步骤：
	 * ①判断两种有环链表的相交情况，可能有不相交，第一种相交，第二种相交（在相应的图上）；
	 * ②如果两个链表的入环节点相等，则为第一种相交情况；
	 * ③针对第一种相交情况，其实与无环链表相交理论基本上一致，只不过计算长度的时候无环是到end，有环则是到入环节点；
	 * ④如果不相等，则为不相交或者第二种相交情况；
	 * ⑤此时把loopNode1一直往下走，如果在回到自己之前，没有遇到loopNode2，则为两个链表不相交，
	 * 否则相交，直接返回loopNode1或loopNode2即可，因为这两个都是第一个相交节点，只不过是针对不同链表而言的。
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
			//这里得到的length1和length2并不是到end的长度，而是到loopNode的长度
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