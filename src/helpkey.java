import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class helpkey extends JFrame {
	Image help = Toolkit.getDefaultToolkit().getImage("helpkey.jpg");
	public helpkey() {
		super("조작방법");
		help_pan h = new help_pan();
		this.add(h);
		this.setResizable(false);
		this.setBounds(200, 200, 608, 413);
	}
	
	class help_pan extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(help, 0, 0, 600, 380, this);
		}
	}
}
