package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class DebugInterface extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel panel;
	boolean pause;
	boolean watch;
	JMenuBar menuBar;
	JMenu menu;
	JScrollPane scrollpane;
	JScrollBar vertical;

	JTextArea text;

	String display = "";
	String oldDisplay = "";
	int maxDisplay = 0;


	public DebugInterface(){

		super("Debug Interface");

		menuBar  = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		JMenuItem pauseItem = new JMenuItem("Pause",
				KeyEvent.VK_T);
		pauseItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		pauseItem.getAccessibleContext().setAccessibleDescription(
				"Pause");
		pauseItem.addActionListener(this);
		menu.add(pauseItem);

		JMenuItem resumeItem = new JMenuItem("Resume",
				KeyEvent.VK_T);
		resumeItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		resumeItem.getAccessibleContext().setAccessibleDescription(
				"Pause");
		resumeItem.addActionListener(this);
		menu.add(resumeItem);

		JMenuItem watchItem = new JMenuItem("Watch",
				KeyEvent.VK_T);
		watchItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_3, ActionEvent.ALT_MASK));
		watchItem.getAccessibleContext().setAccessibleDescription(
				"Watch");
		watchItem.addActionListener(this);
		menu.add(watchItem);


		setJMenuBar(menuBar);

		panel = new JPanel();
		panel.setSize(600, 400);
		//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(null);
		panel.setOpaque(true);
		panel.setBackground(Color.black);


		text = new JTextArea();
		//		text.setContentType("text/html");
		text.setOpaque(true);
		text.setBackground(Color.black);
		text.setForeground(Color.green);

		panel.add(text);

		scrollpane = new JScrollPane(text);
		scrollpane.setBackground(Color.gray);
		scrollpane.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

		vertical = scrollpane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );

		getContentPane().add(scrollpane, BorderLayout.CENTER);


		pack();

		print("**********************************************");
		print("**********************************************");
		print("*********** SYSTEM DEBUG INTERFACE ***********");
		print("**********************************************");
		print("**********************************************");

		print(" ");
		print(" ");
		print(" ");

		setVisible(true);
	}

	public void print(String s){

		if(!pause){
			text.append("\n" + s);
		}
	}

	public void print(String s, Color c){

		if(!pause){

			print(s);
			if(!watch){
				vertical = scrollpane.getVerticalScrollBar();
				vertical.setValue( vertical.getMaximum() );
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		if(source.getText().equals("Pause"))
			pause = true;

		else if(source.getText().equals("Resume")){
			pause = false;
			watch = false;
		}
		else if(source.getText().equals("Watch"))
			watch = !watch;
	}
}