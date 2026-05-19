package minesweeper;

public class TestMatchesAnalysis {

	public static void main(String[] args) {

        MatchDataset dataset = MatchFactory.parseCSV("matches.csv");

        System.out.println("===== MINESWEEPER TELEMETRY REPORT =====");

        System.out.println("Average clicks for VICTORY: "
                + dataset.getAverageClicksByResult("VICTORY"));

        System.out.println("Average clicks for DEFEAT: "
                + dataset.getAverageClicksByResult("DEFEAT"));

        MatchSummary fastestClickRate = dataset.getMatchWithHighestClickRate();

        System.out.println("Match with highest click rate:");
        System.out.println(fastestClickRate);

        System.out.println("========================================");
    }
}
