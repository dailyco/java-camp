package drawboard;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar implements ActionListener{
	JLabel openImage = new JLabel();

	JFileChooser fileChooser = new JFileChooser();

	JMenu fileMenu = new JMenu("파일");
	JMenuItem save = new JMenuItem("저장");
	JMenuItem open = new JMenuItem("불러오기");

	MenuBar() {
		save.addActionListener(this);
		open.addActionListener(this);

		fileMenu.add(save);
		fileMenu.add(open);

		add(fileMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem click = (JMenuItem)e.getSource();

		if(click == save) {
//			if(Buttons.canvas.getSelectedComponent().getName().equals("메모장")) {
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT Files","txt");
//				fileChooser.setFileFilter(filter);
//				String str = Buttons.canvas.getSelectedComponent().memo.getText()
//				
//				int ret = fileChooser.showSaveDialog(null);
//
//				if(ret != JFileChooser.APPROVE_OPTION){
//					JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
//					return;
//				}
//			}
//			else {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images","jpg");
				fileChooser.setFileFilter(filter);
				BufferedImage image = new BufferedImage(Buttons.canvas.getSelectedComponent().getWidth(), Buttons.canvas.getSelectedComponent().getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				Buttons.canvas.getSelectedComponent().print(g);
				g.dispose();

				int ret = fileChooser.showSaveDialog(null);

				if(ret != JFileChooser.APPROVE_OPTION){
					JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					ImageIO.write(image, "jpg", new File(fileChooser.getSelectedFile().toPath() + ".jpg"));
				} catch (IOException ox) {
					// TODO: handle exception
					ox.printStackTrace();
				}
//			}

		}

		if (click == open) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&TXT Files","jpg", "txt");
			fileChooser.setFileFilter(filter);

			int ret = fileChooser.showOpenDialog(null);

			if(ret != JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if(fileChooser.getSelectedFile().getName().endsWith("jpg")) {
				String filePath = fileChooser.getSelectedFile().getPath();
				openImage.setIcon(new ImageIcon(filePath));
				Buttons.canvas.add(fileChooser.getSelectedFile().getName(), openImage);
				Buttons.canvas.add(fileChooser.getSelectedFile().getName(), openImage);
			} else if(fileChooser.getSelectedFile().getName().endsWith("txt")) {
				TextBoard textFile = new TextBoard();

				String filePath = fileChooser.getSelectedFile().getPath();
				File openFile = new File(filePath);
				try {
					int i = 0;

					FileReader reader = new FileReader(openFile);

					try {
						while((i = reader.read())!= -1) {
							textFile.memo.append(String.valueOf((char)i));
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

}