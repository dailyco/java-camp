package imageprocessing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener, ChangeListener, MouseListener, MouseMotionListener{
	JFileChooser fileChooser = new JFileChooser();
	JLabel rgbLabel = new JLabel();
	JTextField text = new JTextField();

	Memory memory = new Memory();

	MenuBar menubar = new MenuBar();
	ButtonsPanel buttonPanel = new ButtonsPanel();
	ImagePanel imagePanel = new ImagePanel();
	BrightSlider slider = new BrightSlider();

	Main() {
		setTitle("Image Processing");
		setSize(1440, 800);
		setBackground(Color.white);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menubar.open.addActionListener(this);
		menubar.save.addActionListener(this);
		slider.addChangeListener(this);
		slider.addMouseListener(this);
		imagePanel.addMouseListener(this);
		imagePanel.addMouseMotionListener(this);
		for(int i = 0 ; i < buttonPanel.function.length ; i++)
			buttonPanel.function[i].addActionListener(this);

		rgbLabel.setBackground(Color.pink);
		rgbLabel.setBounds(20, 550, 400, 40);
		rgbLabel.setFont(new Font(null, Font.PLAIN, 20));
		rgbLabel.setOpaque(true);

		setJMenuBar(menubar);
		add(buttonPanel);
		add(imagePanel);
		add(rgbLabel);

		setVisible(true);
	}

	public static void main(String args[]){
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == menubar.open)
			openFile();

		if(e.getSource() == menubar.save)
			saveFile();

		if (e.getSource() == buttonPanel.function[0])
			undo();

		if (e.getSource() == buttonPanel.function[1])
			redo();

		if (e.getSource() == buttonPanel.function[2])
			grayscale();

		if (e.getSource() == buttonPanel.function[3])
			brigtnessSet();

		if (e.getSource() == buttonPanel.function[4])
			mosaic();

		if (e.getSource() == buttonPanel.function[5])
			enlargementSet();

		if (e.getSource() == buttonPanel.function[6])
			edgeDetection();

		if (e.getSource() == buttonPanel.function[7])
			clear();

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider jSlider = (JSlider)e.getSource();

		imagePanel.outputimage = deepCopy(memory.memory.peek());

		for (int i = 0; i < imagePanel.height; i++) {
			for (int j = 0; j < imagePanel.width; j++) {
				int red, green, blue;

				if (jSlider.getValue() < 0 ) {
					red = imagePanel.currentColor[j][i].getRed() + imagePanel.currentColor[j][i].getRed()*jSlider.getValue()/50;
					green = imagePanel.currentColor[j][i].getGreen() + imagePanel.currentColor[j][i].getGreen()*jSlider.getValue()/50;
					blue = imagePanel.currentColor[j][i].getBlue() + imagePanel.currentColor[j][i].getBlue()*jSlider.getValue()/50;
				}
				else {
					red = imagePanel.currentColor[j][i].getRed() + (255-imagePanel.currentColor[j][i].getRed())*jSlider.getValue()/50;
					green = imagePanel.currentColor[j][i].getGreen() + (255-imagePanel.currentColor[j][i].getGreen())*jSlider.getValue()/50;
					blue = imagePanel.currentColor[j][i].getBlue() + (255-imagePanel.currentColor[j][i].getBlue())*jSlider.getValue()/50;
				}

				Color nColor = new Color(red, green, blue);
				imagePanel.outputimage.setRGB(j, i, nColor.getRGB());
			}
		}

		imagePanel.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if(slider.click == true) {
			Color color[][] = new Color[imagePanel.width][imagePanel.height];

			for (int i = 0; i < imagePanel.height; i++)
				for (int j = 0; j < imagePanel.width; j++)
					color[j][i] = new Color(imagePanel.outputimage.getRGB(j,  i));

			memory.memory.push(deepCopy(imagePanel.outputimage));
			memory.colorMemory.push(color);
			memory.sliderMemory.push(slider.getValue());
		}

		if(buttonPanel.enlargement == true) {
			buttonPanel.enlargement = false;
			imagePanel.repaint();
		}
	}

	void openFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "jpeg", "jpg");
		fileChooser.setFileFilter(filter);

		int ret = fileChooser.showOpenDialog(null);

		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			System.out.println(fileChooser.getSelectedFile());
			imagePanel.inputimage = ImageIO.read(fileChooser.getSelectedFile());
			imagePanel.width = imagePanel.inputimage.getWidth();
			imagePanel.height = imagePanel.inputimage.getHeight();
			imagePanel.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imagePanel.currentColor = new Color[imagePanel.width][imagePanel.height];
		Color color[][] = new Color[imagePanel.width][imagePanel.height];

		for (int i = 0; i < imagePanel.height; i++)
			for (int j = 0; j < imagePanel.width; j++)
				color[j][i] = new Color(imagePanel.inputimage.getRGB(j,  i));

		memory.memory.push(imagePanel.inputimage);
		memory.colorMemory.push(color);
		memory.sliderMemory.push(0);

	}

	void saveFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg" , "jpg");
		fileChooser.setFileFilter(filter);
		int ret = fileChooser.showSaveDialog(null);

		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			ImageIO.write(imagePanel.outputimage, "jpg", new File(fileChooser.getSelectedFile().toPath()+ ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void undo() {
		if(memory.memory.size() <= 1)
			;
		else {
			memory.redoMemory.push(deepCopy(memory.memory.pop()));
			memory.redoColorMemory.push(memory.colorMemory.pop());
			imagePanel.outputimage = memory.memory.peek();
			imagePanel.currentColor = memory.colorMemory.peek();
			memory.redoSliderMemory.push(memory.sliderMemory.pop());
			slider.setValue(memory.sliderMemory.peek());
			imagePanel.repaint();
		}
	}

	void redo() {
		if(memory.redoMemory.isEmpty())
			;
		else {
			memory.memory.push(deepCopy(memory.redoMemory.pop()));
			memory.colorMemory.push(memory.redoColorMemory.pop());
			imagePanel.outputimage = memory.memory.peek();
			imagePanel.currentColor = memory.colorMemory.peek();
			memory.sliderMemory.push(memory.redoSliderMemory.pop());
			slider.setValue(memory.sliderMemory.peek());
			imagePanel.repaint();
		}
	}

	void grayscale() {
		imagePanel.outputimage = deepCopy(memory.memory.peek());

		for (int i = 0; i < imagePanel.height; i++) {
			for (int j = 0; j < imagePanel.width; j++) {
				Color color = new Color(imagePanel.outputimage.getRGB(j,  i));

				int red = (int)(color.getRed() * 0.3);
				int green = (int)(color.getGreen() * 0.6);
				int blue = (int)(color.getBlue() * 0.1);
				int c = red + green + blue;

				Color nColor = new Color(c, c, c);
				imagePanel.outputimage.setRGB(j, i, nColor.getRGB());
			}
		}

		imagePanel.repaint();

		Color color[][] = new Color[imagePanel.width][imagePanel.height];

		for (int i = 0; i < imagePanel.height; i++)
			for (int j = 0; j < imagePanel.width; j++)
				color[j][i] = new Color(imagePanel.outputimage.getRGB(j,  i));

		imagePanel.currentColor = color;
		memory.memory.push(deepCopy(imagePanel.outputimage));
		memory.colorMemory.push(color);
		memory.sliderMemory.push(0);
	}

	void edgeDetection() {
		imagePanel.outputimage = deepCopy(memory.memory.peek());

		int [] filter = new int[] {-1, -1, -1, -1, 8, -1, -1, -1, -1};
		Color[] colorArray = new Color[9];
		int[][] pixel = new int[imagePanel.outputimage.getWidth()][imagePanel.outputimage.getHeight()];

		int colorValue = 0;

		for (int i = 1; i < imagePanel.height - 1; i++) {
			for (int j = 1; j < imagePanel.width - 1; j++) {

				colorArray[0] = new Color(imagePanel.outputimage.getRGB(j - 1, i - 1));
				colorArray[1] = new Color(imagePanel.outputimage.getRGB(j - 1, i));
				colorArray[2] = new Color(imagePanel.outputimage.getRGB(j - 1, i + 1));
				colorArray[3] = new Color(imagePanel.outputimage.getRGB(j, i - 1));
				colorArray[4] = new Color(imagePanel.outputimage.getRGB(j, i));
				colorArray[5] = new Color(imagePanel.outputimage.getRGB(j, i + 1));
				colorArray[6] = new Color(imagePanel.outputimage.getRGB(j + 1, i - 1));
				colorArray[7] = new Color(imagePanel.outputimage.getRGB(j + 1, i));
				colorArray[8] = new Color(imagePanel.outputimage.getRGB(j + 1, i + 1));

				for(int k = 0; k <=8; k++)
					colorValue +=filter[k]*colorArray[k].getRed();

				if(colorValue < 0)
					colorValue = -colorValue;

				if(colorValue > 255)
					colorValue = 255;

				pixel[j][i] = colorValue;
				colorValue = 0;
			}
		}

		for(int i = 1; i < imagePanel.height - 1; i++) {
			for(int j = 1; j < imagePanel.width - 1; j++) {
				Color nColor = new Color(pixel[j][i], pixel[j][i], pixel[j][i]);
				imagePanel.outputimage.setRGB(j, i, nColor.getRGB());
			}
		}

		imagePanel.repaint();

		Color color[][] = new Color[imagePanel.width][imagePanel.height];

		for (int i = 0; i < imagePanel.height; i++)
			for (int j = 0; j < imagePanel.width; j++)
				color[j][i] = new Color(imagePanel.outputimage.getRGB(j,  i));

		imagePanel.currentColor = color;
		memory.memory.push(deepCopy(imagePanel.outputimage));
		memory.colorMemory.push(color);
		memory.sliderMemory.push(0);
	}

	void mosaic() {
		buttonPanel.mosaic = true;
		imagePanel.outputimage = deepCopy(memory.memory.peek());
		imagePanel.output = imagePanel.outputimage.getScaledInstance(imagePanel.outputimage.getWidth()/buttonPanel.mosaicV, imagePanel.outputimage.getHeight()/buttonPanel.mosaicV, Image.SCALE_SMOOTH);
		imagePanel.repaint();

		Color color[][] = new Color[imagePanel.width][imagePanel.height];

		for (int i = 0; i < imagePanel.height; i++)
			for (int j = 0; j < imagePanel.width; j++)
				color[j][i] = new Color(imagePanel.outputimage.getRGB(j,  i));

		imagePanel.currentColor = color;

		//imagePanel.outputimage = toBufferedImage(imagePanel.output);
		//memory.memory.push(deepCopy(imagePanel.outputimage));
		memory.memory.push(deepCopy(memory.memory.peek()));
		//memory.memory.push(null);
		memory.colorMemory.push(color);
		memory.sliderMemory.push(0);
	}

	void brigtnessSet() {
		slider.click = (!slider.click);

		if(slider.click == true) {
			add(slider);
			imagePanel.currentColor = memory.colorMemory.peek();
		}
		else {
			remove(slider);
		}

		repaint();
	}

	void enlargementSet() {
		buttonPanel.enlargement = (!buttonPanel.enlargement);

		if(buttonPanel.enlargement == true) {
			buttonPanel.function[5].setBorderPainted(true);
			imagePanel.image = deepCopy(memory.memory.peek());
		}
		else {
			buttonPanel.function[5].setBorderPainted(false);
			imagePanel.repaint();
		}
	}

	void clear() {
		memory.memory.clear();
		memory.redoMemory.clear();
		memory.colorMemory.clear();
		memory.redoColorMemory.clear();
		memory.sliderMemory.clear();
		memory.redoSliderMemory.clear();

		imagePanel.outputimage = null;

		repaint();

		Color color[][] = new Color[imagePanel.width][imagePanel.height];

		for (int i = 0; i < imagePanel.height; i++)
			for (int j = 0; j < imagePanel.width; j++)
				color[j][i] = new Color(imagePanel.inputimage.getRGB(j,  i));

		memory.memory.push(imagePanel.inputimage);
		memory.colorMemory.push(color);
		memory.sliderMemory.push(0);
	}

	BufferedImage deepCopy(BufferedImage image) {
		ColorModel colorModel= image.getColorModel();
		boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(image.getRaster().createCompatibleWritableRaster());

		return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
	}

	BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0,  null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = e.getPoint();

		if(0 < p.x && p.x < 720 && 0 < p.y && p.y < 540) {
			Color c = new Color(imagePanel.inputimage.getRGB(p.x, p.y));

			rgbLabel.setText("  R : " + c.getRed() + " G : " + c.getGreen() + " B : " + c.getBlue());
		}
		else {
			Color c = new Color(imagePanel.outputimage.getRGB(p.x - 720, p.y));

			rgbLabel.setText("  R : " + c.getRed() + " G : " + c.getGreen() + " B : " + c.getBlue());
		}


	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(slider.click == true)
			;
		else {
			buttonPanel.enlargement = true;
			imagePanel.scale = buttonPanel.scale;
			imagePanel.p = e.getPoint();
			int s = (int)75/imagePanel.scale;

			imagePanel.p.x = imagePanel.p.x - s;
			imagePanel.p.y = imagePanel.p.y - s;

			if(imagePanel.p.x < 0)
				imagePanel.p.x = 0;
			else if(imagePanel.p.x > 720)
				imagePanel.p.x -= 720;

			if(imagePanel.p.y < 0)
				imagePanel.p.y = 0;

			imagePanel.magnifier = imagePanel.image.getSubimage(imagePanel.p.x, imagePanel.p.y, s*2, s*2);
			imagePanel.result = imagePanel.magnifier.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			imagePanel.repaint();
		}
//		el
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(slider.click == true)
			;
		else if(buttonPanel.enlargement == true){
			imagePanel.scale = buttonPanel.scale;
			imagePanel.p = e.getPoint();
			int s = (int)75/imagePanel.scale;

			imagePanel.p.x = imagePanel.p.x - s;
			imagePanel.p.y = imagePanel.p.y - s;

			if(imagePanel.p.x < 0)
				imagePanel.p.x = 0;
			else if(imagePanel.p.x > 720)
				imagePanel.p.x -= 720;

			if(imagePanel.p.y < 0)
				imagePanel.p.y = 0;

			imagePanel.magnifier = imagePanel.image.getSubimage(imagePanel.p.x, imagePanel.p.y, s*2, s*2);
			imagePanel.result = imagePanel.magnifier.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			imagePanel.repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}