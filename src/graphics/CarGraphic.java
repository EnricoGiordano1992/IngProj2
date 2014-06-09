package graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CarGraphic{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ID;
	int xPos;
	int yPos;
	final int init_xPos = 720;
	final int init_yPos = 235;

	final int dimX = 120;
	final int dimY = 20;

	JLabel car;
	JLabel display;
	Color myColor;
	
	public CarGraphic(int ID, Color myColor){

		car= new JLabel(newCarImage("car.png"));

		this.ID = ID;

		xPos = init_xPos;
		yPos = init_yPos;

		car.setBounds(xPos, yPos, 176, 88);

		display = new JLabel();

		display.setOpaque(true);

		this.myColor = myColor;

		display.setBackground(myColor);
		display.setForeground(Color.WHITE);
		display.setText("ID:" + ID);
		display.setBorder(BorderFactory.createEtchedBorder());

		display.setBounds(xPos, yPos, dimX, dimY);
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}

	public JLabel getCar(){
		return car;
	}

	public JLabel getDisplay(){
		return display;
	}

	public void setDisplay(String s){
		display.setText(s);
	}

	public static ImageIcon newCarImage(String name){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(50, 30,
				Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);

		return imageIcon;
	}

}
