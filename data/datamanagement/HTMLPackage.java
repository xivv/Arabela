package data.datamanagement;

import control.Loader;

public class HTMLPackage {

	private static String loungePackage;

	private static String damagePackage;

	public static void init(){
		setLoungePackage(new Loader().getStrFromUrl("https://csgolounge.com/"));
		setDamagePackage(new Loader().getStrFromUrl("http://csgo.99damage.de/de/start"));
	}
	
	public static boolean update() {

		new Thread() {

			@Override
			public void run() {
				setLoungePackage(new Loader().getStrFromUrl("https://csgolounge.com/"));
				setDamagePackage(new Loader().getStrFromUrl("http://csgo.99damage.de/de/start"));
			}

		}.start();

		return true;
	}

	public static String getDamagePackage() {
		return damagePackage;
	}

	public static void setDamagePackage(String damagePackage) {
		HTMLPackage.damagePackage = damagePackage;
	}

	public static String getLoungePackage() {
		return loungePackage;
	}

	public static void setLoungePackage(String loungePackage) {
		HTMLPackage.loungePackage = loungePackage;
	}
}
