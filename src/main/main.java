package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class main extends JFrame{
	// private MenuBar menuBar;
	private ToolBar toolBar;
	private Canvas canvas;
	private MenuBar menuBar;
	
	public main() {
		toolBar = new ToolBar();
		canvas = Canvas.getInstance();
		menuBar = new MenuBar();
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(toolBar, BorderLayout.WEST);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		setTitle("OOAD Hackathon UML editor by 109522104");
		setSize(669,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();
	}

}
