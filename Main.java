import java.util.Stack;

public class Main {
	public static void main(String[] args) {
	
		
		LinkedListNode head = new LinkedListNode(1);
		LinkedListNode node1 = new LinkedListNode(2);
		LinkedListNode node2 = new LinkedListNode(3);
		LinkedListNode node3 = new LinkedListNode(4);
		LinkedListNode node4 = new LinkedListNode(5);
		LinkedListNode node5 = new LinkedListNode(6);
		LinkedListNode node6 = new LinkedListNode(7);

		head.next = node1;
		node1.next = node2;
		node2.next = null;
		node3.next = null;
		node4.next = node5;
		node5.next = node6;
		node6.next = null;
		
		//System.out.println(addLists(head, node4));
		
		
		
		
	}
	
	private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
		if (l1 == null && l2 == null && carry == 0) {
             return null;
		}
		
		LinkedListNode result = new LinkedListNode(0);
		int value = carry;
		if (l1 != null) {
			value += l1.data;
		}
		if (l2 != null) {
			value += l2.data;
		}
		result.data = value % 10;
		if (l1 != null || l2 != null) {
			LinkedListNode more = addLists(l1 == null ? null : l1.next, 
										   l2 == null ? null : l2.next, 
										   value >= 10 ? 1 : 0);
			//result.setNext(more);
		}
		return result;
	}
	
	public static int linkedListToInt(LinkedListNode node) {
		int value = 0;
		System.out.println(node.data);
		if (node.next != null) {
			value = 10 * linkedListToInt(node.next);
		}
		return value + node.data;
	}	
	
}

/*


//Cracking Code 2.8 - Detect circular linked list & return starting node
		
		while(current.next != null && runner.next != null && runner.next.next !=null){
			current = current.next;
			runner = runner.next.next;
			if(!(current == runner)){
				continue;
			}else{
				break;
			}
		}
		current = head;
		while(current != runner){
			
			runner = runner.next;
			current = current.next;
		}
		//return current - this is the start of the circle
		System.out.println(current.data);
		
		
//Reverse LL w/o extra node
		LinkedListNode prev = null;
		LinkedListNode current = head;
		LinkedListNode next = null;
		while(current!=null){
			// 1 --> 2 --> 3 --> 4 --> /0 : 4 --> 3 --> 2 --> 1 --> /0
			next = current.next; //document what comes next
			current.next = prev; //prev value is next
			prev = current; //update prev to current val
			current = next; //current becomes next value in LL
		}
		
		head = prev;
		while(head!= null){
			System.out.println(head.data);
			head = head.next;
		}
		

		
		
		//Another way to reverse LL with adding new nodes
		LinkedListNode head2 = null;
		LinkedListNode node = head;
		while(node != null){
			LinkedListNode n = new LinkedListNode(node.data);
			n.next = head2;
			head2 = n;
			node = node.next;
		}
		while(head2!=null){
			System.out.println(head2.data);
			head2 = head2.next;
		}



  //Remove middle element of LL
		LinkedListNode nodeA = head;
		LinkedListNode nodeB = head;
		LinkedListNode prev1 = head;
		LinkedListNode headHolder = head;

		//Note i use nodeB.next & nodeB.next.next in case odd or even o.w null pointer exception
		while(nodeA.next != null &&  nodeB.next != null && nodeB.next.next!=null){
			prev1 = nodeA;
			nodeA = nodeA.next;
			nodeB = nodeB.next.next;
			
			if(nodeA.next == null || nodeB.next == null || nodeB.next.next == null){
				break;
			}
		}
		nodeA = nodeA.next;
		prev1.next = nodeA;

		while(headHolder!= null){
			System.out.println(headHolder.data);
			headHolder = headHolder.next;
		}
 

//Insert # to sorted LL

LinkedListNode node = head;
LinkedListNode newNode = new LinkedListNode(6);
LinkedListNode starter = node;
LinkedListNode previous = null;
int data = 0;

while(node.next != null){
	if(newNode.data > node.data){
		//System.out.println(node.data);
		previous = node;
		node = node.next;
	}else{
		newNode.next = node;
		previous.next = newNode;
		node = previous;
		break;
	}
}

while(starter != null){
	System.out.println(starter.data);
	starter = starter.next;
}*/