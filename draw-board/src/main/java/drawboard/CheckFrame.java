package drawboard;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckFrame extends JFrame implements ActionListener {
	private JLabel message = new JLabel("         이 캔버스를 정말로 삭제하시겠습니까?");
	private JButton yes = new JButton("YES");
	private  JButton no = new JButton("NO");
	boolean answer;

	public CheckFrame() {
		setLayout(new BorderLayout());
		setTitle("Notice");
		setSize(270, 70);
		setLocation(580, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		yes.addActionListener(this);
		no.addActionListener(this);

		add("North", message);
		add("West", yes);
		add("East", no);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();

		if(button == yes) {
			Buttons.canvas.remove(Buttons.canvas.getSelectedComponent());

			Memory.memory.clear();
			Memory.colorMemory.clear();
			Memory.thicknessMemory.clear();

			Memory.redoMemory.clear();
			Memory.redoColorMemory.clear();
			Memory.redoThicknessMemory.clear();
		}

		this.dispose();
	}
}