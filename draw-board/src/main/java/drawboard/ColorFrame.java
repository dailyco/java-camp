package drawboard;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorFrame extends JFrame implements ChangeListener{
	JColorChooser colorChooser = new JColorChooser();
	
	ColorSelectionModel model = colorChooser.getSelectionModel();
	static Color color = Color.black;
	static boolean colorChange;
	
	ColorFrame() {
		setTitle("색상");
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		colorChooser.getSelectionModel().addChangeListener(this);

		add(colorChooser);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		color = colorChooser.getColor();
		colorChange = true;
	}
}