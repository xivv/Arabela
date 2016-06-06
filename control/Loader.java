package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import data.Match;
import data.datamanagement.StringPattern;

public class Loader {

	// HLTV
	public String getLineupHLTV(String teamname) {
		String lineup_ = "";
		try {
			String lineup = getStrFromUrl("http://www.hltv.org/?pageid=152&query= " + teamname);

			lineup = lineup.split(">Players</td>")[1].split(">Team stats</td>")[0];

			Matcher matcher = StringPattern.lineupHLTVPattern.matcher(lineup);

			for (int i = 0; i < 5; i++) {
				matcher.find();
				lineup_ += matcher.group(1) + "|";
			}

		} catch (Exception e) {

			lineup_ = "No lineup information @powerd by hltv";

			lineup_ = getLineup(teamname);

		}

		return lineup_;
	}

	// BETA LIQUIDPEDIA @SLOW
	public String getLineup(String teamname) {

		String lineup = getStrFromUrl("http://wiki.teamliquid.net/counterstrike/ " + teamname);
		try {
			lineup = lineup.split("<th colspan=\"6\">Active Squad")[1].split("<th colspan=\"8\">Former Players")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			return "No Lineup information ( @powerd by liquidpedia )";
		}

		Matcher matcher = StringPattern.lineupPattern.matcher(lineup);

		String lineup_ = "";

		for (int i = 0; i < 5; i++) {
			matcher.find();
			lineup_ += matcher.group(1) + "|";
		}
		return lineup_;
	}

	public List<Match> getMatches(String source) {

		List<Match> matchList = new ArrayList<>();

		String[] matches = source.split("<div class=\"matchmain\">");
		matches[0] = "";
		matches[matches.length - 1] = "";

		for (int i = 0; i < matches.length; i++) {
			try {
				if (i != 0 && i != matches.length - 1) {

					
					// Comment
					
					Matcher matcher = StringPattern.csglCommentPattern.matcher(matches[i]);

					matcher.find();

					String matchComment = matcher.group(1);

					
					// Timeleft
					matcher = StringPattern.timePattern.matcher(matches[i]);

					matcher.find();

					String timeleft = matcher.group(1);

					// Eventname
					matcher = StringPattern.eventNamePattern.matcher(matches[i]);

					matcher.find();

					String eventName = matcher.group(1);

					// Eventformat

					matcher = StringPattern.formatPattern.matcher(matches[i]);

					matcher.find();

					String format = matcher.group(1);

					// EVENTLOGO

					matcher = StringPattern.eventLogoPattern.matcher(matches[i]);

					matcher.find();

					String eventLogo = matcher.group(1);

					// TEAM1
					matcher = StringPattern.teamNamePattern.matcher(matches[i]);

					matcher.find();

					String team1Name = matcher.group(1);

					matcher = StringPattern.teamPercentPattern.matcher(matches[i]);

					matcher.find();

					String team1Percent = matcher.group(1);

					// MATCH WON

					matcher = StringPattern.teamWonPattern.matcher(matches[i].split("teamtext")[0]);

					boolean team1Won = matcher.find();

					matcher = StringPattern.teamWonPattern.matcher(matches[i].split("teamtext")[1]);

					boolean team2Won = matcher.find();

					// TEAM2

					String[] team2info = matches[i].split("teamtext");

					matcher = StringPattern.teamNamePattern.matcher(team2info[team2info.length - 1]);

					matcher.find();

					String team2Name = matcher.group(1);

					String team2Percent = String.valueOf(100 - Integer.parseInt(team1Percent.split("%")[0])) + "%";

					matchList.add(new Match(timeleft, format, eventName, team1Name, team1Percent, team2Name,
							team2Percent, team1Won, team2Won, eventLogo,matchComment));
				}
			} catch (Exception e) {

			}
		}

		return matchList;

	}

public String getStrFromUrl(String surl) {

		final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";

		try {
			URL url = new URL(surl);
			URLConnection conn = url.openConnection();
			conn.addRequestProperty("User-Agent", userAgent);
			StringBuilder builder = new StringBuilder(1024);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String str;

				while ((str = in.readLine()) != null) {
					builder.append(str);
					builder.append("\n");
				}
				in.close();
			}

			return builder.toString();

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return "Error";
	}

}
