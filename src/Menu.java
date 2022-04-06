import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel {

	JButton bt1 = new JButton("현재원 관리");
	JButton bt2 = new JButton(" 특이사항 ");
	JButton bt3 = new JButton("외박자 관리");
	JButton bt4 = new JButton("상.벌점 관리");
	JButton bt5 = new JButton("로그아웃");
	JLabel trash = new JLabel("                               ");
	DormManagementDAO DAO = DormManagementDAO.getInstance();

	JPanel MP = new JPanel();
	JPanel LP = new JPanel();

	public Menu() {
		setLayout(new BorderLayout());
		Font f1 = new Font("나눔스퀘어 Bold", Font.BOLD, 15);
		Font f2 = new Font("나눔고딕코딩", Font.PLAIN, 15);

		Color color = new Color(0xBCE0D8);// 0xDEDEDE
		setBackground(color);
		MP.setBackground(color);
		LP.setBackground(color);

		add(MP, BorderLayout.CENTER);
		add(LP, BorderLayout.EAST);

		MP.add(trash);
		MP.add(bt1);
		MP.add(bt2);
		MP.add(bt3);
		MP.add(bt4);
		LP.add(bt5);

		bt1.addActionListener(new MenuAL());
		bt2.addActionListener(new MenuAL());
		bt3.addActionListener(new MenuAL());
		bt4.addActionListener(new MenuAL());
		bt5.addActionListener(new MenuAL());

		bt1.setFont(f2);
		bt2.setFont(f2);
		bt3.setFont(f2);
		bt4.setFont(f2);
		bt5.setFont(f2);

	}

	class MenuAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			DormManager DM = (DormManager) getTopLevelAncestor();

			Object[] option = { "확인" };
			if (b.getText().equals("현재원 관리")) {
				JOptionPane.showOptionDialog(null, "현재원 관리 화면으로 이동합니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DAO.DormManage();
				DM.MngTab.viewP(new ManageTab());
				DM.MngTab.revalidate();
			}
			if (b.getText().equals(" 특이사항 ")) {
				JOptionPane.showOptionDialog(null, "특이사항 창으로 이동합니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DM.MngTab.viewP(new DPManage());
			}
			if (b.getText().equals("외박자 관리")) {
				JOptionPane.showOptionDialog(null, "외박자 관리 창으로 이동합니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DM.MngTab.viewP(new ONManage());
			}
			if (b.getText().equals("상.벌점 관리")) {
				JOptionPane.showOptionDialog(null, "상.벌점 관리 창으로 이동합니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DM.MngTab.viewP(new RnPManage());
			}
			if (b.getText().equals("로그아웃")) {
				JOptionPane.showOptionDialog(null, "로그아웃되었습니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DM.logout();
			}
			if (b.getText().equals("종료")) {
				System.out.println("프로그램을 종료합니다.");
				JOptionPane.showOptionDialog(null, "프로그램을 종료합니다.", "확인", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				DAO.disconnect();
				System.exit(0);
			}
		}
	}
}
