import javax.swing.*;
import java.awt.*;

public class StdCenterPanel extends JPanel {
    public StdCenterPanel(){
        setLayout(null);
        ImageIcon img = new ImageIcon("img/symbol.png");
        JLabel logo = new JLabel(img);

        Font f1 = new Font("나눔스퀘어 Bold", Font.PLAIN, 30);
        logo.setBounds(85, 110, 300, 300);
        add(logo);

        Color color = new Color(0xDCD3B7);
        setBackground(color);
    }
}
