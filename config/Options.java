package config;

import java.awt.Color;
import java.util.regex.Matcher;

import data.datamanagement.StringPattern;

public class Options {

	public static boolean showEventName = true;

	public static boolean showGamesComming = true;

	public static boolean showGamesLive = true;

	public static boolean showGamesOver = true;

	public static boolean showAdvancedScoreLive = true;

	public static boolean showAdvancedScoreOver = true;

	public static boolean showOnlyPositiveScore = true;

	public static boolean showLineUpInConsole = true;

	public static boolean showTimeLeft = true;

	public static boolean showBackground = true;

	public static long updateTime = 120000;

	public static int fontSize = 12;

	public static Color liveColor = new Color(0, 0, 0);

	public static Color gameOverColor = new Color(0, 0, 0);

	public static Color onComingColor = new Color(0, 0, 0);

	public static Color drawColor = new Color(0, 0, 0);

	public static Color warningColor = new Color(0, 0, 0);

	public static Color postponedColor = new Color(0, 0, 0);

	public static Color backgroundColor = new Color(0, 0, 0);

	public static void updateColors() {
		
		String data = new Reader().getColors();

		Matcher matcher = StringPattern.colorPattern.matcher(data);

		matcher.find();

		liveColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		gameOverColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		onComingColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		drawColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		warningColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		postponedColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));

		matcher.find();

		backgroundColor = new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));
	}

	public static void updateConfig() {

		String data = new Reader().getConfig();

		Matcher matcher = StringPattern.configPatter.matcher(data);

		matcher.find();

		showEventName = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showGamesComming = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showGamesLive = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showGamesOver = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showAdvancedScoreLive = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showAdvancedScoreOver = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showOnlyPositiveScore = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showLineUpInConsole = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showTimeLeft = Boolean.valueOf(matcher.group(2));

		matcher.find();

		showBackground = Boolean.valueOf(matcher.group(2));

		matcher.find();

		updateTime = Long.parseLong(matcher.group(2));

		matcher.find();

		fontSize = Integer.parseInt(matcher.group(2));
	}

}
