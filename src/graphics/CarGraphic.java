package graphics;

import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CarGraphic extends JLabel implements Runnable{

	int ID;
	int xPos = 720;
	int yPos = 190;

	public CarGraphic(int ID){

		super(newCarImage("car.png"));
		this.ID = ID;

		setBounds(xPos, yPos, 176, 88);
	}


	private static ImageIcon newCarImage(String name){

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
	
	public void run(){
			move();
	}

	public void move(){

		int sleep = 10;
		

		while(true)
		{

			
			/*
			 * entra nella pista
			 * 
			 */
			while(xPos >= 636)
			{
				setBounds(xPos--, yPos, 176, 88);
				
				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();

			}
			/*
			 * comincia a girare
			 * 
			 */

			while(xPos >= 580)
			{
				setBounds(xPos--, yPos-= 2, 176, 88);

				try{
					Thread.sleep(sleep+10);
				}catch(Exception e){}
				repaint();

			}

			while(xPos >= 400){
				if(xPos % 2 == 0)
					yPos--;
				setBounds(xPos--, yPos, 176, 88);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();

			}					
			while(xPos >= 200)
			{
				setBounds(xPos--, yPos, 176, 88);

				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();
			}

			while(xPos >= 10){
				if(xPos % 2 == 0)
					yPos++;
				setBounds(xPos--, yPos, 176, 88);


				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();

			}
			while(xPos >= -35)
			{
				setBounds(xPos--, yPos+= 2, 176, 88);

				try{
					Thread.sleep(sleep+10);
				}catch(Exception e){}
				repaint();

			}

			/*
			 * 
			 * ora torna indietro
			 */
			setIcon(newCarImage("car2.png"));
			while(xPos <= 30)
			{
				setBounds(xPos++, yPos+= 2, 176, 88);


				try{
					Thread.sleep(sleep+10);
				}catch(Exception e){}
				repaint();

			}


			while(xPos <= 200){
				if(xPos % 2 == 0)
					yPos++;
				setBounds(xPos++, yPos, 176, 88);


				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();


			}

			while(xPos <= 400 )
			{
				setBounds(xPos++, yPos, 176, 88);


				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();

			}
			
			
			while(xPos <= 580 )
			{
				if(xPos % 2 == 0)
					yPos--;
				setBounds(xPos++, yPos, 176, 88);


				try{
					Thread.sleep(sleep);
				}catch(Exception e){}
				repaint();

			}

			while(xPos <= 640)
			{
				setBounds(xPos++, yPos-= 2, 176, 88);

				try{
					Thread.sleep(sleep+10);
				}catch(Exception e){}
				repaint();

			}

			xPos = 636;
			yPos = 190;
			setBounds(xPos, yPos, 176, 88);

			setIcon(newCarImage("car.png"));
			
			repaint();



		}
	}
}
