package terminal;

import config.Options;
import control.KeyControl;
import data.Constant;
import data.datamanagement.HTMLPackage;
import data.datamanagement.MatchPackage;
import visual.Background;
import visual.Overlay;
import visual.labelcontainer.LabelContainer;

public class Terminal {

	public static void main(String[] args) {

		launchArabela();
	}

	public static void launchArabela() {

		Options.updateConfig();
		Options.updateColors();
		Constant.init();

		HTMLPackage.init();
		MatchPackage.update();

		Background back = null;

		if (Options.showBackground) {
			back = new Background();
		}

		Overlay overlay = new Overlay();

		LabelContainer.init(overlay, back);
		KeyControl.initOverlay(overlay, back);

		KeyControl.listenForKeys();

		while (true) {
			LabelContainer.generateMatchLabels();
			try {
				Thread.sleep(Options.updateTime);
				HTMLPackage.update();
				MatchPackage.update();
				Overlay.setLabelHeight(0);
				overlay.getContentPane().removeAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
