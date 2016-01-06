import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Make_Info extends JFrame {
	static int num=1;
	Image help = Toolkit.getDefaultToolkit().getImage("make_info.jpg");
	public Make_Info() {
		super("∏∏µÁ¿Ã");
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
