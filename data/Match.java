package data;

import java.util.Calendar;
import java.util.regex.Matcher;

import control.Loader;
import data.datamanagement.HTMLPackage;
import data.datamanagement.StringPattern;

public class Match {

	public String timeLeft;

	public String format;

	public String eventName;

	public String team1Name;

	public String team1Percent;

	public String team2Name;

	public String team2Percent;

	public boolean team1Won;

	public boolean team2Won;

	public boolean gameOver;

	public boolean oncomming;

	public String eventLogo;

	public Calendar calendar;

	public boolean live;

	public String matchURL;

	public String matchComment;

	public boolean namesSwitched;

	public Match() {

	}

	@Override
	public String toString() {
		return timeLeft + " * " + eventName + " * " + format + " * " + team1Name + " * " + team1Percent + " * "
				+ team2Name + " * " + team2Percent + " * " + team1Won + " * " + team2Won + " * " + eventLogo;
	}

	public Match(String timeLeft, String format, String eventName, String team1Name, String team1Percent,
			String team2Name, String team2Percent, boolean team1Won, boolean team2Won, String eventLogo,
			String matchComment) {
		super();
		this.timeLeft = timeLeft;
		this.format = format;
		this.eventName = eventName;
		this.team1Name = team1Name;
		this.team1Percent = team1Percent;
		this.team2Name = team2Name;
		this.team2Percent = team2Percent;
		this.team1Won = team1Won;
		this.team2Won = team2Won;
		this.eventLogo = eventLogo + ".jpg";
		this.gameOver = team1Won || team2Won;
		this.calendar = Calendar.getInstance();
		this.live = timeLeft.contains("ago"); // && !gameOver;
		this.matchURL = this.getMatchURLFinal();
		this.matchComment = matchComment;
		if (!live && !gameOver)
			this.oncomming = true;
	}

	public String getBO1Score() {

		if (matchURL.startsWith("No match")) {
			return "#";
		}

		String score = new Loader().getStrFromUrl(matchURL).split("<h2>Kommentare</h2>")[0].split("Matchdetails")[1]
				.replaceAll("\n", "");

		Matcher matcher = StringPattern.bo1ScorePattern.matcher(score);

		if (matcher.find())
			score = matcher.group(1) + "|";

		return score;
	}

	public String getScore() {

		if (matchURL.startsWith("No match")) {
			return "#";
		}
		String score = new Loader().getStrFromUrl(matchURL).split("<h2>Kommentare</h2>")[0].split("Matchdetails")[1]
				.replaceAll("\n", "");

		Matcher matcher = StringPattern.mapScorePattern.matcher(score);

		// @ NEED TO IMPLEMENT WRONG DIRECTION ERROR

		String score_ = "";

		while (matcher.find()) {
			score_ += matcher.group(2).trim() + "| ";
		}

		return score_;
	}

	public String getScore99() {

		if (matchURL.startsWith("No match")) {
			return "#";
		}

		String score = new Loader().getStrFromUrl(matchURL).split("<h2>Kommentare</h2>")[0].split("Matchdetails")[1]
				.replaceAll("\n", "");

		String score_ = "";

		Matcher matcher = StringPattern.mapScore99Pattern.matcher(score);

		while (matcher.find()) {

			score_ += matcher.group(2).trim() + ":" + matcher.group(4).trim() + "| ";

		}

		return score_;

	}

	public String getMatchURLFinal() {

		String data = "";
		try {
			data = HTMLPackage.getDamagePackage().split("<div class=\"title\">Matches</div>")[1]
					.split("<ul class=\"widget-matches-timequick\">")[0];
		} catch (Exception e) {
			return "No match Information found. Informant may be down.";
		}
		// CHECK FOR NAMECORRECTION
		String team1Name = this.team1Name;
		String team2Name = this.team2Name;

		if (Constant.teamAlias.containsKey(team1Name)) {
			team1Name = Constant.teamAlias.get(team1Name);
		}
		if (Constant.teamAlias.containsKey(team2Name)) {
			team2Name = Constant.teamAlias.get(team2Name);
		}

		// SEARCH FOR MATCHES
		Matcher matcher = StringPattern.url99Pattern.matcher(data);

		while (matcher.find()) {
			if (matcher.group(2).contains(team1Name.toLowerCase())
					&& matcher.group(3).contains(team2Name.toLowerCase())) {
				return "http://csgo.99damage.de/de/matches/" + matcher.group(1) + "-" + matcher.group(2) + "-vs-"
						+ matcher.group(3);
			}
		}

		// Reinitialise matcher

		matcher = null;
		matcher = StringPattern.url99Pattern.matcher(data);

		// CHECK FOR WRONG DIRECTION

		while (matcher.find()) {
			if (matcher.group(2).contains(team2Name.toLowerCase())
					&& matcher.group(3).contains(team1Name.toLowerCase())) {
				this.namesSwitched = true;
				return "http://csgo.99damage.de/de/matches/" + matcher.group(1) + "-" + matcher.group(2) + "-vs-"
						+ matcher.group(3);
			}
		}

		return "No match information found";
	}
}
