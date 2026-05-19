package minesweeper;

public class CoordinateQueue {
	
	public static class CoordinateNode {
		
		int row;
		int col;
		CoordinateNode next;
		
		public  CoordinateNode(int row, int col) {
			this.row = row;
			this.col = col;
			this.next = null;
		}
	}
	
	private CoordinateNode front;
	private CoordinateNode rear;
	
	public CoordinateQueue() {
		front = null;
		rear = null;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void enqueue(int row, int col) {
		
		CoordinateNode newNode = new CoordinateNode(row, col);
		
		if(rear == null) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
	}

	public CoordinateNode dequeue() {
		
		
		if(isEmpty()) {
			return null;
		}
		
		CoordinateNode temp = front;
		front = front.next;
		
		if(front == null) {
			rear = null;
		}
		
		return temp;
	}
	
}
