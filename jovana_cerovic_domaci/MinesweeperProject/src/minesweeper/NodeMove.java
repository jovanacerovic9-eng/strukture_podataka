package minesweeper;

public class NodeMove {
	
	Move data;
	NodeMove next;
	
	public NodeMove(Move data) {
		this.data = data;
		this.next = null;
	}

}
