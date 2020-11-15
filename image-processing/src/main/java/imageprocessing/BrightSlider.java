package imageprocessing;

import javax.swing.JSlider;

public class BrightSlider extends JSlider{
boolean click;

BrightSlider() {
	setMaximum(50);
	setMinimum(-50);
	setValue(0);
	setBounds(500, 550, 900, 40);
	setMajorTickSpacing(10);
	setMinorTickSpacing(1);
	setPaintTicks(true);
	setPaintLabels(true);
}

}