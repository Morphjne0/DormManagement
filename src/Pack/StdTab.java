package Pack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StdTab extends JFrame {
	DormManagementDAO dao = DormManagementDAO.getInstance();
	Login login;
	StdCenterPanel cenP;
	DPRequest dpr;
	StdMenu smenu;
	JLabel version = new JLabel("Version. A01");
	static StdTab instance = null;
	Container c = getContentPane();
	JPanel vP = new JPanel(new BorderLayout());

	void viewP(JPanel p) {
		c.remove(cenP);
		c.add(p, BorderLayout.CENTER);
		revalidate();
	}

	public StdTab() {
		cenP = new StdCenterPanel();
		smenu = new StdMenu();
		dpr = new DPRequest();
		Color color = new Color(0xDCD3B7);

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.add(cenP, BorderLayout.CENTER);
		setTitle("Dormitary Management Program");
		this.setVisible(true);
		this.setSize(490, 560);

		vP.add(version, BorderLayout.EAST);
		c.add(vP, BorderLayout.SOUTH);
		// c.add(menu,BorderLayout.NORTH);
		vP.setBackground(color);

		// setResizable(false);
	}

	static StdTab getInstance() throws NullPointerException {
		if (instance == null) {
			instance = new StdTab();
		}
		return instance;
	}

	void logout() {
		login = Login.getInstance();
		setVisible(false);
		login.reshow();
	}
}
