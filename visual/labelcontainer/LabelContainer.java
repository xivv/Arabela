package visual.labelcontainer;

import config.Options;
import data.Match;
import data.datamanagement.MatchPackage;
import visual.Background;
import visual.MatchLabel;
import visual.Overlay;

public class LabelContainer {

	private static Overlay overlay;

	private static Background back;

	public static void init(Overlay visualComponent, Background backComponent) {
		overlay = visualComponent;
		back = backComponent;
	}


	public static void generateMatchLabels() {
		int y = -100;
		for (Match match : MatchPackage.getMatchList()) {
			MatchLabel label = new MatchLabel(match);
			label.scoreUpdate();
			label.generateView();

			// 7.5 pixels per Char
			if (label.getLength() >= Overlay.getLength())
				Overlay.setLength(label.getLength());

			if (label.getText() != "")
				Overlay.setLabelHeight(Overlay.getLabelHeight() + 1);

			label.setLocation(10, y);
			overlay.add(label);
			if (!label.generateView().equals("")) {
				y += 20;
			}

		}
		if (Options.showBackground) {
			back.repaint();
		}
		overlay.repaint();

	}
}
