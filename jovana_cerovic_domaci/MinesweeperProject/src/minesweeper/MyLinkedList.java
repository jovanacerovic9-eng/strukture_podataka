package minesweeper;

public class MyLinkedList {
	
	private NodeMove head;
	
	public MyLinkedList() {
		head = null;
	}
	
	public void insert(Move move) {
		
		NodeMove newNode = new NodeMove(move);
		
		if(head == null) {
			head = newNode;
			return;
		}
		
		NodeMove current = head;
		
		while(current.next != null) {
			current = current.next;
		}
		
		current.next = newNode; 
	}
	
	public NodeMove getHead() {
		return head;
	}

}
