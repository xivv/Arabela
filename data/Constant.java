package data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import config.Reader;
import data.datamanagement.StringPattern;

public class Constant {

	public static Map<String, String> teamAlias = new HashMap<>();

	public static void init() {
		Constant.buildTeamAlias();
		Matcher matcher = StringPattern.aliasPattern.matcher(Reader.getAlias());

		while (matcher.find()) {
			if (teamAlias.containsKey(matcher.group(1))) {
				System.out.println(
						"TEAM ADD FAILED | KEY: " + matcher.group(1) + " already registerd with " + teamAlias.get(matcher.group(1)));
			} else {
				System.out.println("TEAM ADDED | KEY: " + matcher.group(1) + " VALUE: " + matcher.group(2));
				teamAlias.put(matcher.group(1), matcher.group(2));
			}
		}
	}

	public static void buildTeamAlias() {
		teamAlias.put("LG", "Luminosity");
		teamAlias.put("HR", "Hellraisers");
		teamAlias.put("NiP", "Ninjas");
		teamAlias.put("nV", "EnVyUs");
		teamAlias.put("NaVi", "Natus");
		teamAlias.put("Na'Vi", "Natus");
		teamAlias.put("ATN", "aTTax");
		teamAlias.put("C9", "Cloud9");
		teamAlias.put("CSGL", "Lounge");
		teamAlias.put("CSGL", "Dobry"); // EX CSGL -> Maybe Change later
		teamAlias.put("VP", "Virtus");
		teamAlias.put("coL", "Complexity");
		teamAlias.put("TSM", "SoloMid");
		teamAlias.put("CLG", "Logic");
		teamAlias.put("WaR", "Roof");
		teamAlias.put("CW", "Copenhagen");
		teamAlias.put("FSid3", "Flipsid3");
		teamAlias.put("TStorm", "Tempo");
		teamAlias.put("mouz", "mouse");
	}

}
