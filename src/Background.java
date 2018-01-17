 


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Background extends JPanel {
	private Image img;
	public Background(){
		try {
			img = ImageIO.read(new File("Resources/background.jpg"));
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error Code 000.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);


	}
}
