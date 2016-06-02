package control;

import java.awt.event.KeyEvent;

import jhook.Keyboard;
import jhook.KeyboardListener;
import visual.Background;
import visual.Overlay;

public class KeyControl {

	private static Overlay overlay;

	private static Background back;

	public static void initOverlay(Overlay visualComponent, Background backComponent) {
		overlay = visualComponent;
		back = backComponent;
	}

	public static void listenForKeys() {

		Keyboard kb = new Keyboard();
		kb.addListener(new KeyboardListener() {

			@Override
			public void keyPressed(boolean keydown, int vk) {
				if (vk == KeyEvent.VK_NUMPAD0 && keydown) {
					if (overlay.isVisible()) {
						if (back != null)
							back.setVisible(false);
						overlay.setVisible(false);
					} else {
						if (back != null)
							back.setVisible(true);
						overlay.setVisible(true);
					}
				}
				if (vk == KeyEvent.VK_NUMPAD9 && keydown) {
					System.exit(0);
				}

			}

		});
	}

	public static Overlay getOverlay() {
		return overlay;
	}

}
