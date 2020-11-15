package imageprocessing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonsPanel extends JPanel implements ChangeListener{
	JPanel magnifierPanel = new JPanel();
	JPanel mosaicPanel = new JPanel();
	
	JSpinner spinnerB = new JSpinner();
	JSpinner spinnerM = new JSpinner();
	SpinnerNumberModel model = new SpinnerNumberModel(1,1,5,1);
	SpinnerNumberModel model2 = new SpinnerNumberModel(1,1,5,1);
	
	JButton[] function = new JButton[8];
	
	int scale = 1;
	int mosaicV = 1;
	static boolean enlargement;
	static boolean mosaic;
	boolean resize;

	ButtonsPanel() {
		setBounds(0, 605, 1440, 150);
		setLayout(new GridLayout(1, 8));
		setBackground(Color.white);
		
		spinnerB.setModel(model);
		spinnerB.addChangeListener(this);
		spinnerB.setSize(50, 50);
		spinnerB.setFont(new Font(null, Font.PLAIN, 20));
		
		spinnerM.setModel(model2);
		spinnerM.addChangeListener(this);
		spinnerM.setFont(new Font(null, Font.PLAIN, 20));
		
		magnifierPanel.setLayout(new GridLayout(1,2));
		mosaicPanel.setLayout(new GridLayout(1,2));
		
		magnifierPanel.setBackground(Color.white);
		mosaicPanel.setBackground(Color.white);

		for(int i = 0; i < function.length; i++) {
			function[i] = new JButton(new ImageIcon("icon/" + i + ".png"));
			function[i].setBorderPainted(false);
		}
		
		magnifierPanel.add(function[5]);
		magnifierPanel.add(spinnerB);
		
		mosaicPanel.add("East", function[4]);
		mosaicPanel.add("West", spinnerM);
		
		for(int i = 0; i < 4; i++)
			add(function[i]);
		add(mosaicPanel);
		add(magnifierPanel);
		add(function[6]);
		add(function[7]);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		scale = (Integer)spinnerB.getValue();
		mosaicV = (Integer)spinnerM.getValue();
	}

}