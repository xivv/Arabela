package visual;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;

import config.Options;
import control.Loader;
import data.Match;

public class MatchLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	private String scores;

	private Match match;

	public int getLength() {
		return this.getText().length();
	}

	public void scoreUpdate() {
		if (match.gameOver) {
			String scores_ = match.getScore();
			if (!scores_.equals(scores))
				scores = scores_;
		} else if (match.live) {
			String scores_ = match.getScore99();
			if (!scores_.equals(scores))
				scores = scores_;
		} else if (match.oncomming) {
			scores = "";
		}
	}

	// IMPROVED SHOW SCORE VERSION ??
	public String generateView() {

		String matchView = "";

		String eventName = match.eventName;

		String score = "";

		String lineup = "";

		String timeLeft = "";

		if (!Options.showEventName) {
			eventName = "";
		}
		if (match.gameOver) {
			setForeground(Options.gameOverColor);
			if (!Options.showGamesOver) {

				return "";
			}
			if (!Options.showAdvancedScoreOver) {
				score = match.getScore();
				if (score.equals("") && match.format.contains("BO1"))
					score = match.getBO1Score();
			} else {
				score = match.getScore99();
			}
		} else if (match.oncomming) {
			setForeground(Options.onComingColor);

			if (!Options.showGamesComming)
				return "";

			if (Options.showTimeLeft) {
				timeLeft = match.timeLeft.replace("from now", "");
			}

		} else if (match.live) {
			setForeground(Options.liveColor);
			if (!Options.showGamesLive) {

				return "";
			}
			if (!Options.showAdvancedScoreLive) {
				score = match.getScore();
			} else {
				score = match.getScore99();
			}

		}

		if (Options.showLineUpInConsole) {
			lineup = new Loader().getLineupHLTV(match.team1Name) + " < > "
					+ new Loader().getLineupHLTV(match.team2Name);
			System.out.println(lineup);
		}

		String team1Name = match.team1Name;
		String team2Name = match.team2Name;

		if (match.team1Won) {
			team1Name = "|" + team1Name + "|";

		}
		if (match.team2Won) {
			team2Name = "|" + team2Name + "|";

		}

		if (match.namesSwitched) {
			team1Name = match.team2Name;
			team2Name = match.team1Name;

			if (match.team2Won) {
				team1Name = "|" + team1Name + "|";

			}
			if (match.team1Won) {
				team2Name = "|" + team2Name + "|";

			}
		}

		if (match.matchComment.contains("15") || match.matchComment.contains("16")
				|| match.matchComment.contains("Rescheduled")) {
			this.match.live = false;
			this.match.gameOver = true;
			setForeground(Options.warningColor);
		} else if (match.matchComment.toLowerCase().contains("=")) {
			setForeground(Options.warningColor);
		}

		if (match.matchComment.toLowerCase().contains("draw") || match.matchComment.toLowerCase().contains("1-1")) {
			this.match.live = false;
			this.match.gameOver = true;
			score = "draw";
			setForeground(Options.drawColor);
		}

		if (match.matchComment.toLowerCase().contains("postponed")) {
			this.match.live = false;
			this.match.gameOver = true;
			setForeground(Options.postponedColor);
		}

		matchView = eventName + "> " + team1Name + " VS " + team2Name + " > " + score + timeLeft;

		this.setText(matchView);

		if (Options.showOnlyPositiveScore) {
			this.setText(this.getText().replace("00:00|", ""));
		}

		return matchView;
	}

	public MatchLabel(Match match) {
		this.setFont(new Font("Consolas", Font.BOLD, Options.fontSize));
		this.match = match;
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	}
}
