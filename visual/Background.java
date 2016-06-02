package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import config.Options;

public class Background extends JDialog {

	private static final long serialVersionUID = 1L;

	public Background() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setSize(width.intValue(), height.intValue());
		this.setPreferredSize(new Dimension(width.intValue(), height.intValue()));
		this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
		this.setLayout(null);
		this.setOpacity(0.5f);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		
		g.setColor(Options.backgroundColor);
		g.fillRect(3, 425, (int) (Overlay.getLength() * 7.5), (int) (Overlay.getLabelHeight() * 20.5));

	}

}
