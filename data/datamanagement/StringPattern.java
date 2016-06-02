package data.datamanagement;

import java.util.regex.Pattern;

public class StringPattern {

	public static Pattern timePattern = Pattern.compile("\"whenm\">(.*?)<span");
	public static Pattern formatPattern = Pattern.compile("\"format\">(.*?)</span");
	public static Pattern eventNamePattern = Pattern.compile("\"eventm\">(.*?)</div");
	public static Pattern teamNamePattern = Pattern.compile("<b>(.*?)</b>");
	public static Pattern teamPercentPattern = Pattern.compile("<i>(.*?)</i>");
	public static Pattern teamWonPattern = Pattern.compile("csgolounge.com/img/won.png");
	public static Pattern eventLogoPattern = Pattern.compile("events/(.*?).jpg");
	public static Pattern lineupPattern = Pattern.compile("pre\"><a href=\"/counterstrike/(.*?)\" title");
	public static Pattern lineupHLTVPattern = Pattern.compile("\">(.*?)</a></td>");
	public static Pattern urlPattern = Pattern.compile("matches/(.*?)vs(.*?)\">");
	public static Pattern mapScorePattern = Pattern.compile("sum(.*?)>(.*?)</div>");
	public static Pattern bo1ScorePattern = Pattern.compile("<div class=\"score\">(.*?)</div>");
	public static Pattern csglCommentPattern = Pattern.compile("#D12121\">(.*?)</span>");
	public static Pattern configPatter = Pattern.compile("#(.*?) = '(.*?)'");
	public static Pattern url99Pattern = Pattern.compile("http://csgo.99damage.de/de/matches/(.*?)-(.*?)-vs-(.*?)\">");
	public static Pattern mapScore99Pattern = Pattern.compile("<span class=\"score\">(.*?)>(.*?)</font>:<font color=\"(.*?)>(.*?)<");
	public static Pattern aliasPattern = Pattern.compile("#(.*?) <(.*?)>");
	public static Pattern colorPattern = Pattern.compile("<(.*?),(.*?),(.*?)>");

}
