package graphics;

import javax.swing.*; 

import java.awt.*; 

public class WallGraphic extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
				Dimension d = getSize(); 
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null); 

				super.paintComponent(g); 
			} 
		};

		panel.setOpaque( false ); 
		panel.setSize( new Dimension(1000, 1000) ); 
		getContentPane().add( panel ); 
		setResizable(false);
	} 
}