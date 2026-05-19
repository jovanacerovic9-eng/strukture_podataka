package minesweeper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GamesSimulator {
	
	public static Player createBot(Board board) {
		return new Player(board);
	}
	
	public static void main(String[] args) {
		
		int numberOfMatches = 1000;
		int boardSize = 8;
		int numberOfMines = 6;
		
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("matches.csv"));
			
			writer.println("MatchId,BotType,Result,TimeMs,TotalClicks");
			
			for( int matchId = 1; matchId <= numberOfMatches; matchId++) {
				
				Board board = new Board(boardSize, numberOfMines);
				Player player = createBot(board);
				
				long startTime = System.currentTimeMillis();
				
				GameOutcome result = GameOutcome.IN_PROGRESS;
				
				while(result == GameOutcome.IN_PROGRESS) {
					result = player.playTurn();
				}
				
				long endTime = System.currentTimeMillis();
				long timeMs = endTime - startTime;
				
				int totalClicks = countMoves(player.getMoveHistory());
				
				writer.println(matchId + ",RandomBot," + result + "," + timeMs + "," + totalClicks);
			}
			
			writer.close();
			
			System.out.println("Simulation finished.");
			System.out.println("matches.csv file created.");
			
		} catch (IOException e) {
			System.out.println("Error while writing CSV file");
		}
	}
	
	private static int countMoves(MyLinkedList list) {
		
		int count = 0;
		NodeMove current = list.getHead();
		
		while(current != null) {
			count++;
			current = current.next;
		}
		
		return count;
	}
}
