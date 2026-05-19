package minesweeper;

import java.util.Random;

public class Player {
	
	private Board board;
	private MyLinkedList moveHistory;
	private Random random;
	
	public Player(Board board) {
		this.board = board;
		this.moveHistory = new MyLinkedList();
		this.random = new Random();
	}
	
	public GameOutcome playTurn() {
		
		int size = board.getSize();
		
		int row;
		int col;
		
		do {
			row = random.nextInt(size);
			col = random.nextInt(size);
		} while (board.isRevealed(row, col));
		
		boolean wasSafe = !board.isMine(row, col);
		
		board.revealCell(row, col);
		
		Move move = new Move(row, col, wasSafe);
		moveHistory.insert(move);
		
		return board.getGameState();
		}
	
	public MyLinkedList getMoveHistory() {
		return moveHistory;
	}
}
