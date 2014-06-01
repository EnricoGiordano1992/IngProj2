package graphics;

import java.awt.event.*; 
import javax.swing.*; 

import java.awt.*; 
import javax.swing.*;

public class WallGraphic extends JFrame 
{
	ImageIcon icon; 
	Image image; 

	public WallGraphic() 
	{ 
		getContentPane().setSize(1000, 1000);
		icon = new ImageIcon("wall.png"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel() 
		{ 
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) 
			{ 
				g.drawImage(icon.getImage(), 0, 0, null); 

				// Scale image to size of component 
				//	 Dimension d = getSize(); 
				//	 g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null); 

				// Fix the image position in the scroll pane 
				//	 Point p = scrollPane.getViewport().getViewPosition... 
				//	 g.drawImage(icon.getImage(), p.x, p.y, null); 

				super.paintComponent(g); 
			} 
		}; 
		panel.setOpaque( false ); 
		panel.setSize( new Dimension(1000, 1000) ); 
		getContentPane().add( panel ); 

	} 


}