package minesweeper;

import java.util.Random;

public class Board {
	
	private Cell[][] grid;
	private int size;
	private int numMines;
	
	public Board(int size, int numMines) {
		
		if(size <= 0 || numMines < 0 || numMines >= size * size) {
			throw new IllegalArgumentException("Invalid board.");
		}
		
		this.size = size;
		this.numMines = numMines;
		
		grid = new Cell[size][size];
		
		initializeBoard();
		placeMines();
		calculateAdjacentMines();
	}
	
	private void initializeBoard() {
		
		for(int row = 0; row < size; row++) {
			
			for(int col = 0; col < size; col++) {
				
				grid[row][col] = new Cell();
			}
		}
	}
	
	private void placeMines() {
		
		Random random = new Random();
		
		int placed = 0;
		
		while(placed < numMines) {
			
			int row = random.nextInt(size);
			int col = random.nextInt(size);
			
			if(!grid[row][col].isMine()) {
				
				grid[row][col].setMine(true);
				placed++;
			}
		}
	}
	
	private void calculateAdjacentMines() {
		
		int[] dx = {-1,-1,-1,0,0,1,1,1};
		int[] dy = {-1,0,1,-1,1,-1,0,1};
		
		for(int row = 0; row < size; row++) {
			
			
			for(int col = 0; col < size; col++) {
				
				if(grid[row][col].isMine()) {
					continue;
				}
				
				int count = 0;
				
				for(int i = 0; i < 8; i++) {
					
					int newRow = row + dx[i];
					int newCol = col + dy[i];
					
					if(isValid(newRow, newCol)
							&& grid[newRow][newCol].isMine()) {
						
						
						count++;
					}
				}
				
				grid[row][col].setAdjacentMines(count);
				
				
			}
		}
	}
	
	private boolean isValid(int row, int col) {
		
		return row >= 0 
				&& row < size
				&& col >= 0
				&& col < size;
	}
	
	public void revealCell(int row, int col) {
		
		if(!isValid(row, col)) {
			return;
		}
		
		Cell cell = grid[row][col];
		
		if (cell.getState() == CellState.REVEALED
				|| cell.getState() == CellState.FLAGGED) {
			return;
		}
		
		cell.setState(CellState.REVEALED);
		
		if(cell.isMine()) {
			return;
		}
		
		if(cell.getAdjacentMines() == 0) {
			revealCascade(row, col);
		}
	}
	
	private void revealCascade(int row, int col) {
		
		CoordinateQueue queue = new CoordinateQueue();
		queue.enqueue(row, col);
		
		int[] dx = {-1,-1,-1,0,0,1,1,1};
		int[] dy = {-1,0,1,-1,1,-1,0,1};
		
		while(!queue.isEmpty()) {
			
			CoordinateQueue.CoordinateNode current = queue.dequeue();
			
			int currentRow = current.row;
			int currentCol = current.col;
			
			for ( int i = 0; i < 8; i++) {
				
				int newRow = currentRow + dx[i];
				int newCol = currentCol + dy[i];
				
				if (isValid(newRow, newCol)) {
					
					Cell neighbor = grid[newRow][newCol];
					
					if (!neighbor.isMine()
							&& neighbor.getState() ==  CellState.HIDDEN) {
						
						neighbor.setState(CellState.REVEALED);
						
						if(neighbor.getAdjacentMines() == 0) {
							queue.enqueue(newRow, newCol);
						}
					}
				}
			}
		}
	}
	
	public GameOutcome getGameState() {
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				
				Cell cell = grid[row][col];
				
				if(cell.isMine()
						&& cell.getState() == CellState.REVEALED) {
					return GameOutcome.DEFEAT;
				}
			}
		}
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				
				Cell cell = grid[row][col];
				
				if(!cell.isMine()
						&& cell.getState() != CellState.REVEALED) {
					return GameOutcome.IN_PROGRESS;
				}
				
			}
		}
		
		return GameOutcome.VICTORY;
		
	}
	
	public boolean isRevealed(int row, int col) {
		return grid[row][col].getState() == CellState.REVEALED;
	}
	
	public boolean isMine(int row, int col) {
		return grid[row][col].isMine();
	}
	
	public int getSize() {
		return size;
	}
}