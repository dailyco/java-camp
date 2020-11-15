package imageprocessing;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	JMenu fileMenu = new JMenu("파일");
	JMenuItem save = new JMenuItem("저장");
	JMenuItem open = new JMenuItem("불러오기");

	MenuBar() {
		fileMenu.add(save);
		fileMenu.add(open);

		add(fileMenu);
	}
}