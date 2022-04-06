package Pack;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	public CenterPanel() {

		setLayout(null);
		ImageIcon img = new ImageIcon("img/symbol.png");
		JLabel logo = new JLabel(img);

		Font f1 = new Font("나눔스퀘어 Bold", Font.PLAIN, 30);
		logo.setBounds(585, 210, 500, 500);
		add(logo);

		Color color = new Color(0xDCD3B7);
		setBackground(color);
	}
}
