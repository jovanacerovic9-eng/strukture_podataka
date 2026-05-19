package minesweeper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatchFactory {
	
	public static MatchSummary parseMatch(String line) {
		
		String[] parts = line.split(",");
		
		int matchId = Integer.parseInt(parts[0]);
		String botType = parts[1];
		String result = parts[2];
		long timeMs = Long.parseLong(parts[3]);
		int totalClicks = Integer.parseInt(parts[4]);
		
		return new MatchSummary(matchId, botType, result, timeMs, totalClicks);
	 }

    public static MatchDataset parseCSV(String fileName) {

        MatchDataset dataset = new MatchDataset();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                MatchSummary match = parseMatch(line);
                dataset.addMatch(match);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error while reading CSV file.");
        }

        return dataset;
    }
}

