import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;



public class LinkedLists {

	public static void main(String[] args) {
		LinkedLists myClass = new LinkedLists();

		LinkedList ll = new LinkedList<LinkedListNode>();
		/*LinkedListNode head2 = new LinkedListNode(4);
		LinkedListNode node3 = new LinkedListNode(5);
		LinkedListNode node4 = new LinkedListNode(6);
		head2.next = node3;
		node3.next = node4;
		//LinkedList sumLists = myClass.sumLists(head, head2);
		for(int i = 0; i< sumLists.size(); i++){
			System.out.println(sumLists.get(i));
		}
		
		*/
		
		LinkedListNode head = new LinkedListNode(20);
		LinkedListNode node1 = new LinkedListNode(5);
		LinkedListNode node2 = new LinkedListNode(3);
		LinkedListNode node3 = new LinkedListNode(4);
		LinkedListNode node4 = new LinkedListNode(3);
		LinkedListNode node5 = new LinkedListNode(5);
		LinkedListNode node6 = new LinkedListNode(20);
		
		LinkedListNode head2 = new LinkedListNode(100);
		LinkedListNode node7 = new LinkedListNode(12);
		LinkedListNode node8 = new LinkedListNode(52);
		LinkedListNode node9 = new LinkedListNode(11);
		
		LinkedListNode node10 = new LinkedListNode(400);

		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		//new ll
		head2.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node8;
		
		
		node4.next = node5;
		node5.next = node6;
		node6.next = null;



		//node33.next = node7;
		
		//myClass.removeDuplicates(head);
		//myClass.printContents(head);
		//System.out.println();
		//LinkedListNode kthToLast = myClass.kthToLastElement(head, 2);
		//System.out.println(kthToLast.data);
		//LinkedListNode partitionedList = myClass.partition(head, 8);
		//myClass.printContents(partitionedList);
		
		//System.out.println(myClass.palindrome2(head));
		
		//LinkedListNode newNode = myClass.intersectingLLs(head, head2);
		LinkedListNode newNode = myClass.loopDetection(head2);
		int i = 10;
		while(newNode != null && i >0){
			System.out.println("Data: " + newNode.data);
			newNode = newNode.next;
			i--;
		}


		
		
	}
	
	//2.8 Loop Detection - Given a circular linked list implement algorithm that returns the beginning node
	//aka the node that starts the circle over
	//my implementation:
	public LinkedListNode loopDetection(LinkedListNode node){

		HashSet<LinkedListNode> hs = new HashSet<LinkedListNode>();
		
		
		while(node != null){
			if(hs.contains(node)){
				break;
			}
			hs.add(node);
			
			node = node.next;
		}
		return node;
	}
	
	//Their implementation: using fast runner & slow runner
	public LinkedListNode loopDetection2(LinkedListNode head){
		LinkedListNode slow = head;
		LinkedListNode fast = head;
		//Find meeting point aka LOOP_SIZE - k steps into the linked list
		while(fast != null && slow != null){

			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast){
				break;
			}
			
		}
		if(fast == null || fast.next == null){
			return null;
		}
		slow = head; //fast is now k steps from loop start and slow si now k steps from start
		//when both fast & slow reach loop start (aka k steps away) we can return that value
		while(slow != fast){
			slow = slow.next;
			fast = fast.next;
		}
		
		
		return fast;
	}
	
	//2.7 Intersection - Given 2 LL see if two lists intersect. Return intersecting node.
	//Aka if the nth node is the same exact node as the mth node return it
	public LinkedListNode intersectingLLs(LinkedListNode node1, LinkedListNode node2){
		LinkedListNode node = new LinkedListNode(1);
		LinkedListNode node1Copy = node1;
		LinkedListNode node2Copy = node2;
		int whichNode = 0;
		int len1 = 0 ;
		int len2 = 0;
		while(node1 != null){
			len1++;
			node1 = node1.next;
		}
		while(node2 != null){
			len2++;
			node2 = node2.next;
		}
		int difference = 0;
		if(len1 > len2){
			whichNode = 1;
			difference = len1 - len2;
		}else{
			whichNode = 2;
			difference = len2 - len1;
		}
		while(difference > 0){
			if(whichNode == 1){
				node1Copy = node1Copy.next;
				difference--;
			}else{
				node2Copy = node2Copy.next;
				difference--;
			}
		}
		while(node1Copy != null && node2Copy != null){
			if(node1Copy == node2Copy){
				return node1Copy;
				
			}else{
				node1Copy = node1Copy.next;
				node2Copy = node2Copy.next;
			}
		}
		
		
		
		return node;
	}
	
	
	//Misc  - Reverse & Clone
	public LinkedListNode reverseLL(LinkedListNode node){
		LinkedListNode head = null;
		while(node!= null){
			LinkedListNode n = new LinkedListNode(node.data); //Clone
			n.next = head;
			head = n;
			
			node = node.next;
			
			
		}
		
		return head;
	}
	
	
	//2.6 Palindrome - same forwards and backwards
	public boolean palindrome2(LinkedListNode head){
		Stack<Integer> stack = new Stack<Integer>();
		LinkedListNode fast = head;
		LinkedListNode slow = head;
		

		int count = 0;
		while(fast != null && fast.next != null){
			//System.out.println(n1.data);
			stack.push(slow.data);
			slow = slow.next;
			
			fast = fast.next.next;
			count++;
			

		}
		System.out.println(stack.size());

		if(count % 2 != 0){
			slow = slow.next;
		}
		System.out.println(stack.size());
		while(slow != null){
			int popped = stack.pop();
			if(popped != slow.data){
				return false;
			}
			slow = slow.next;
		}
		System.out.println("hello");
		
		//if(stack.pop() != slow.data){
		//	System.out.println(slow.data);
		//	return false;
		//}

		return true;
	}
	
	
	
	//2.6 Palindrome - same forwards and backwards
	public boolean palindrome(LinkedListNode node){

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		if(node == null){
			return false;
		}
		int count = 0;
		while(node != null){
			
			hm.put(count, node.data);
			node = node.next;
			count++;
			
		}
		if(count == 1){
			return true;
		}
		
		int middle = count /2;
		count--;
		for(int i = 0; i <= middle; i++){
			
			if(hm.get(i) != hm.get(count)){
				System.out.println(hm.get(i) + " DNE " + hm.get(count) );
				return false;
			}
			count--;
		}
		return true;
	}
	
	
	
	
	//2.4 Partition
	public LinkedListNode partition(LinkedListNode node, int partitionInt){
		LinkedListNode beforeStart = null;
		LinkedListNode beforeEnd = null;
		LinkedListNode afterStart = null;
		LinkedListNode afterEnd = null;
		while(node != null){
			LinkedListNode next = node.next;//key - forgot
			node.next = null;//key - forgot
			if(node.data < partitionInt){
				
				if(beforeStart == null){
					beforeStart = node;
					beforeEnd = beforeStart;
				}else{
					beforeEnd.next = node;
					beforeEnd = node;
					
				}
			}else{
				if(afterStart == null){
					afterStart = node;
					afterEnd = afterStart;
				}else{
					
					afterEnd.next = node;
					afterEnd = node;
				}
			}
			node = next;
		}
		if(beforeStart == null){
			return afterStart;
		}
		beforeEnd.next = afterStart; //merge
		return beforeStart;
	}
	
	
	
	
	
	
	//2.5 Sum Lists
	
	public LinkedList sumLists(LinkedListNode head, LinkedListNode head2){
		
		HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
		HashMap<Integer, Integer> hm2 = new HashMap<Integer,Integer>();
		int count1 = 0;
		StringBuilder sbx = new StringBuilder();
		System.out.println("here");
		while(head != null){
			hm.put(count1, head.data);
			sbx.append(head.data);
			count1++;
		
			head = head.next;
		}
		int int1 = Integer.parseInt(sbx.toString());

		String s1 = sbx.toString();
		
		StringBuilder sbz = new StringBuilder();
		for(int i = sbx.length() - 1; i >= 0; i--){
			sbz.append(sbx.charAt(i));
		}
		
		int intz = Integer.parseInt(sbz.toString());
		System.out.println(intz);
		
		
		int count2 = 0;
		StringBuilder sbx2 = new StringBuilder();
		System.out.println("here");
		while(head2 != null){
			hm2.put(count2, head2.data);
			sbx2.append(head2.data);
			count2++;
			
			head2 = head2.next;
		}
		int int2 = Integer.parseInt(sbx2.toString());

		String s2 = sbx2.toString();
		
		StringBuilder sbz2 = new StringBuilder();
		for(int i = sbx2.length() - 1; i >= 0; i--){
			sbz2.append(sbx2.charAt(i));
		}
		
		int intz2 = Integer.parseInt(sbz2.toString());
		System.out.println(intz2);
		
		int total = intz + intz2;
		String sTotal = String.valueOf(total);
		System.out.println("total:" + total);

		int k = sTotal.length() - 1;
		
		LinkedList ll = new LinkedList<Integer>();
		while(k >=0){
			
			String s = sTotal.charAt(k) + ""; 
			int node = Integer.parseInt(s);

			ll.add(node);

			k--;
		}
		
		return ll;
	}
	
	
	//2.3 Implement an algorithm to delete a node somewhere in the middle of a singly
	//linked list given only access to that node.
	public boolean deleteNode(LinkedListNode node){
		
		
		if(node == null || node.next == null){
			return false;
		}
		LinkedListNode next = node.next; //We have to use same node because o/w lose previous connection
		node.data = next.data; //Each Node has 'data'
		node.next = next.next; //and a pointer to next 'Node'

		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	//2.2 Implement an algorithm to find the kth to last element of a singly linked list
	public static LinkedListNode kthToLastElement(LinkedListNode head, int k) {
		if (k <= 0)
			return null;
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		if (p2 == null)
			return null;
		
		for (int i = 0; i < k - 1; i++) {
			if (p2 == null) //in case user inputs a k > # of elements
				return null;
			p2 = p2.next;
		}
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
	
	
	
	
	
	
	
	
	//2.1 Remove duplicates from an unsorted linked list
	public void removeDuplicates(LinkedListNode n){
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while(n!= null){
			if(set.contains(n.data)){
				previous.next = n.next;
			}else{
				set.add(n.data);
				previous = n;
			}
			n = n.next;
		}
	}
	
	public void printContents(LinkedListNode n){

		LinkedListNode previous = null;

		while(n != null){
			System.out.println(n.data);
			previous = n;
			n= n.next;
		
			
		}
		
	}
}
	/* 
	//REMEMBER IN INTERVIEW TO UNDERSTAND WHETHER IT IS SINGLY-LINKED-LIST OR A DOUBLY-LINKED-LIST
	//Implement basic singly linked list
	Class Node{
		Node next = null;
		int data;
		
		public Node(int d){
			data = d;
		}
		
		void appendToTail(int d){
			Node end = new Node(d);
			Node n = this;
			while(n.next != null){
				n = n.next;
			}
			n.next = end;
			}
		}
		
		//More
		Node deleteNode(Node head, int d){
			Node n = head;
			if(n.data == d){
				return head.next; //moved head
			}
			
			while(n.next != null){
				if(n.next.data == d){
					n.next = n.next.next;
					return head; //head didnt change
				}
				n = n.next;
			}
			return head;
		}
		
		//Can also add a "runner" where you have 2 pointers iterating simultaneously (one ahead of other)
		//Could have p1 move every 2 elements for every move p2 moves 1 element
		//This way, when p1 reaches end, p2 is the midpoint
	*/
	

