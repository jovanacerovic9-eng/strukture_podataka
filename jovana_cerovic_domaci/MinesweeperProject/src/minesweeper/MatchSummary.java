package minesweeper;

public class MatchSummary implements Comparable<MatchSummary> {
	
	private int matchId;
	private String botType;
	private String result;
	private long timeMs;
	private int totalClicks;
	
	public MatchSummary(int MatchId, String botType,
			String result, long timeMs, int totalClicks) {
		
		this.matchId = matchId;
		this.botType = botType;
		this.result = result;
		this.timeMs = timeMs;
		this.totalClicks = totalClicks;
	}
	
	public int getMatchId() {
		return matchId;
		
	}
	
	public String getBotType() {
		return botType;
	}
	
	public String getResult() {
		return result;
	}
	
	public long getTimeMs() {
		return timeMs;
	}
	
	public int getTotalClicks() {
		return totalClicks;
	}
	
	@Override
	public int compareTo(MatchSummary other) {
		return Long.compare(this.timeMs, other.timeMs);
	}
	
	@Override
	public String toString() {
		return "Match " + matchId +
				" | Result: " + result +
				" | Time: " + timeMs +
				"ms | Clicks: " + totalClicks;
	}

}
