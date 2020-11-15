package drawboard;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main extends JFrame {
	MenuBar menubar = new MenuBar();
	ToolBar toolbar = new ToolBar();
	
	Main() {
		setTitle("그림판");
		setSize(1440,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setJMenuBar(menubar);
		add(toolbar, BorderLayout.NORTH);
		add(toolbar.buttons.canvas);

		setVisible(true);
	}
	
	public static void main(String args[]){
		new Main();
	}
}