package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StationGraphic extends JLabel{
	

	public StationGraphic(){
		
		super(newCarImage("radio.gif"));

		setBounds(0, 0, 80, 200);

	}
	
	
	private static ImageIcon newCarImage(String name){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(80, 200,
				Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);

		return imageIcon;
	}


}
