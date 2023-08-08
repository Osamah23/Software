package sakancommain;

import java.awt.*;
import java.awt.image.*;
import java.util.List;
import javax.swing.*;

public class PhotoShower extends JFrame {
	JPanel frame=new JPanel();

	public PhotoShower() {
		super();
		super.setSize(1200,1200);
		super.setLayout(new FlowLayout());
	}
	
	public void paintPhoto(BufferedImage a) {
		JLabel picLabel = new JLabel(new ImageIcon(a));
		frame.add(picLabel);
		frame.setSize(400,400);
		super.add(frame);
		super.setVisible(true);
	}

	public void paintAllPhotos(List<BufferedImage> photos) {
		for(int i=0;  i< photos.size(); i++) {
			paintPhoto(photos.get(i));
		}
	}
}