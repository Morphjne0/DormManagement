package Pack;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

	static Login instance = null;
	DormManagementDAO DAO;
	DormManager DM;
	StdTab stab;
	String id = null;
	String pw = null;
	JPanel BP = new JPanel(new BorderLayout());
	JPanel t1 = new JPanel();
	JPanel test1 = new JPanel(new BorderLayout());
	JPanel test2 = new JPanel(new GridLayout(2, 1));
	ImageIcon img = new ImageIcon("img/symbol.png");
	JLabel logo = new JLabel(img);
	private JPanel loginPanel = new JPanel(new GridLayout(4, 2, 20, 10));
	private JLabel idLabel = new JLabel("아이디 ");
	private JTextField idText = new JTextField("배길성");
	private JLabel pwLabel = new JLabel("비밀번호 ");
	private JPasswordField pwText = new JPasswordField("1702100348");
	private JButton loginBtn = new JButton("로그인");

	public Login() {

		super("로그인창");

		this.setContentPane(test1);
		t1.setLayout(null);
		test1.add(test2, BorderLayout.CENTER);

		test2.add(logo);

		test2.add(BP);
		BP.add(t1, BorderLayout.CENTER);

		idLabel.setBounds(13, 50, 100, 25); // 오른쪽 로그인쓸거면
		idText.setBounds(110, 50, 150, 25);
		pwLabel.setBounds(13, 90, 100, 25);
		pwText.setBounds(110, 90, 150, 25);
		loginBtn.setBounds(285, 50, 75, 63);

		t1.add(loginBtn);
		t1.add(idLabel);
		t1.add(idText);
		t1.add(pwLabel);
		t1.add(pwText);

		DAO = DormManagementDAO.getInstance();
//라벨 가운데 정렬

		idLabel.setHorizontalAlignment(NORMAL);

		pwLabel.setHorizontalAlignment(NORMAL);

//현재 프레임 창 가운데 정렬 setSize를 먼저 해주어야 정상적으로 프레임이 가운데 정렬이 됨!

		setSize(400, 500);

		this.setLocationRelativeTo(null);

		this.setVisible(true);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//로그인 버튼을 눌렀을때

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//아이디 비번 확인 테스트 코드
				id = idText.getText().trim();
				pw = pwText.getText().trim();

				System.out.println(id);
				System.out.println(pw);

				if (id.equals("admin") && pw.equals("kopo2021@")) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "경고", JOptionPane.WARNING_MESSAGE);
					DM = DormManager.getInstance();
					setVisible(false);
					DM.viewP((DM.MngTab));
					DM.add(DM.menu, BorderLayout.NORTH);
					DM.menu.setVisible(true);
					if (!DM.isVisible()) {
						DM.setVisible(true);
					}

					// dispose();
				} else if (id.length() == 0 || pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력하십시오.", "경고", JOptionPane.WARNING_MESSAGE);
					return;

				} else if (DAO.login(id, pw)) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "경고", JOptionPane.WARNING_MESSAGE);
					stab = StdTab.getInstance();
					setVisible(false);
					stab.viewP(stab.cenP);
					stab.smenu.setVisible(true);
					stab.add(stab.smenu, BorderLayout.NORTH);
					if (!stab.isVisible()) {
						stab.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		});

//아이디 비밀번호 찾기 버튼을 눌렀을때

		setResizable(false);

	}

	public static void main(String[] args) {
		getInstance();
	}

	static Login getInstance() {
		try {
			if (instance == null) {
				instance = new Login();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}

	void reshow() {
		// id와 비번을 초기화 해주실것
		idText.setText("아이디");
		pwText.setText("1234");
		setVisible(true);
	}
}
