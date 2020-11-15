package drawboard;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextBoard {
	JPanel memoBoard = new JPanel();
	JTextArea memo = new JTextArea(" ");
	
	TextBoard() {
		JScrollPane scroll = new JScrollPane(memo);
		
		memoBoard.setBackground(Color.white);
		memoBoard.add(memo);
		Buttons.canvas.add("메모장",memoBoard);
	}
	
}