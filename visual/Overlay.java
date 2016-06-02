package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Overlay extends JFrame {

	// https://github.com/The-Nutty/CSGo-In-game-Bomb-Timer/blob/master/src/com/tomhazell/csgo/ingametimer/Overlay.java

	private static final long serialVersionUID = 1L;

	private static int length;

	private static int labelHeight;

	public Overlay() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setSize(width.intValue(), height.intValue());
		this.setPreferredSize(new Dimension(width.intValue(), height.intValue()));
		this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public static int getLabelHeight() {
		return labelHeight;
	}

	public static void setLabelHeight(int height) {
		Overlay.labelHeight = height;
	}

	public static int getLength() {
		return length;
	}

	public static void setLength(int data) {
		length = data;
	}
}
