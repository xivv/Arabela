package data.datamanagement;

import java.util.List;

import control.Loader;
import data.Match;

public class MatchPackage {

	
	private static List<Match> matchList;
	
	public static void update(){
		 matchList = new Loader().getMatches(HTMLPackage.getLoungePackage());
	}
	
	public static List<Match> getMatchList(){
		return matchList;
	}
}
