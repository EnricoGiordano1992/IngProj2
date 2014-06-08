package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StationGraphic extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int width = 180;
	private static int heigth = 300;

	public StationGraphic(){
		
		super(newCarImage("radio.gif"));

		setBounds(300, -10, width, heigth);
		
		setBounds(300, -10, width, heigth);
		

	}
	
	
	private static ImageIcon newCarImage(String name){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(width, heigth,
				Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);

		return imageIcon;
	}


}
