package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class DebugInterface extends JFrame{

	JPanel panel;

	public DebugInterface() {

		super("Debug Interface");


		panel = new JPanel();
		panel.setSize(600, 400);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		panel.setBackground(Color.black);

		JScrollPane scrollpane = new JScrollPane(panel);
		scrollpane.setBackground(Color.gray);
		scrollpane.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		scrollpane.setAutoscrolls(true);
		getContentPane().add(scrollpane, BorderLayout.CENTER);


		print("**********************************************");
		print("*                                                                  *");
		print("*           SYSTEM DEBUG INTERFACE          *");
		print("*                                                                  *");
		print("**********************************************");

		print(" ");
		print(" ");
		print(" ");
		
		setVisible(true);
	}


	public void print(String s){

		JLabel newString = new JLabel();
		newString.setText(s);
		newString.setForeground(Color.green);
		panel.add(newString);

		newString.setVisible(true);
		panel.repaint();
	}
}
