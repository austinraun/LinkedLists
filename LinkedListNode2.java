
public class LinkedListNode2 {
		int data;	
		LinkedListNode next;
		
		
		public LinkedListNode2(int data){
			this.data = data;
			next = null;
		}
		
		public void appendToTail(int d){
			LinkedListNode end = new LinkedListNode(d);
			LinkedListNode n = this;
			while(n.next != null){
				n = n.next;
			}
			n.next = end;
		}
		
		//More
		LinkedListNode2 deleteNode(LinkedListNode head, int d){
			LinkedListNode n = head;
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
}
