package minesweeper;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MatchDataset {
	
	private SortedSet<MatchSummary> matches;
	
	public MatchDataset() {
		matches = new TreeSet<>();
	}
	
	public void addMatch(MatchSummary match) {
		matches.add(match);
	}
	
	public Double getAverageClicksByResult(String result) {
		
		return matches.stream()
				.filter(m -> m.getResult().equals(result))
				.mapToInt(MatchSummary::getTotalClicks)
				.average()
				.orElse(0.0);
	}
	
	public MatchSummary getMatchWithHighestClickRate() {

	    MatchSummary bestMatch = null;

	    double bestRate = -1;

	    for (MatchSummary m : matches) {

	        double time = m.getTimeMs();

	        if (time == 0) {
	            time = 1;
	        }

	        double rate = (double) m.getTotalClicks() / time;

	        if (rate > bestRate) {
	            bestRate = rate;
	            bestMatch = m;
	        }
	    }

	    return bestMatch;
	}
	}
