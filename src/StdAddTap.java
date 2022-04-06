
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StdAddTap extends JFrame {
	JTextField nametext, idtext, depttext, gendertext, rntext, pntext, entext, pictext, addrtext, miltext, htext,
			rlgntext;
	String select, nameValue, sidValue, deptValue, rnValue, pnValue, enValue, addrValue, genderValue, milValue, chValue,
			rlgnValue, picValue;
	DormManagementDAO dao = DormManagementDAO.getInstance();
	ManageTab mng = ManageTab.getInstance();
	JLabel notice;
	static StdAddTap instance = null;

	public StdAddTap() {
		setSize(450, 920);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		this.setVisible(true);
		Font font = new Font("나눔스퀘어 Bold", Font.PLAIN, 15);
		JLabel nameLabel = new JLabel("이름 *");
		nameLabel.setBounds(34, 73, 100, 15);
		nameLabel.setFont(font);

		JLabel idLabel = new JLabel("학번 *");
		idLabel.setBounds(34, 122, 100, 15);
		idLabel.setFont(font);

		JLabel deptLabel = new JLabel("학과 *");
		deptLabel.setBounds(34, 173, 100, 15);
		deptLabel.setFont(font);

		JLabel genderLabel = new JLabel("성별");
		genderLabel.setBounds(34, 233, 100, 15);
		genderLabel.setFont(font);

		JLabel rnLabel = new JLabel("방번호 *");
		rnLabel.setBounds(34, 283, 100, 15);
		rnLabel.setFont(font);

		JLabel pnLabel = new JLabel("핸드폰번호 *");
		pnLabel.setBounds(34, 347, 100, 15);
		pnLabel.setFont(font);

		JLabel enLabel = new JLabel("비상연락처 *");
		enLabel.setBounds(34, 405, 100, 15);
		enLabel.setFont(font);

		JLabel milLabel = new JLabel("군필");
		milLabel.setBounds(34, 459, 100, 15);
		milLabel.setFont(font);

		JLabel hLabel = new JLabel("병력");
		hLabel.setBounds(34, 520, 100, 15);
		hLabel.setFont(font);

		JLabel rlgnLabel = new JLabel("종교");
		rlgnLabel.setBounds(34, 580, 100, 15);
		rlgnLabel.setFont(font);

		JLabel picLabel = new JLabel("사진");
		picLabel.setBounds(34, 640, 100, 15);
		picLabel.setFont(font);

		JLabel addrLabel = new JLabel("주소 *");
		addrLabel.setBounds(34, 700, 100, 15);
		addrLabel.setFont(font);

		notice = new JLabel("*은 필수 표기사항입니다");
		notice.setBounds(243, 33, 180, 30);
		notice.setFont(font);

		nametext = new JTextField();
		nametext.setColumns(10);
		nametext.setBounds(133, 70, 198, 25);
		nametext.setColumns(10);
		panel.add(nametext);

		idtext = new JTextField();
		idtext.setColumns(10);
		idtext.setBounds(133, 119, 198, 25);
		panel.add(idtext);

		depttext = new JTextField();
		depttext.setColumns(10);
		depttext.setBounds(133, 170, 198, 25);
		panel.add(depttext);

		gendertext = new JTextField();
		gendertext.setColumns(10);
		gendertext.setBounds(133, 230, 198, 25);
		panel.add(gendertext);

		rntext = new JTextField();
		rntext.setColumns(10);
		rntext.setBounds(133, 280, 198, 25);
		panel.add(rntext);

		pntext = new JTextField();
		pntext.setColumns(10);
		pntext.setBounds(133, 344, 198, 25);
		panel.add(pntext);

		entext = new JTextField();
		entext.setColumns(10);
		entext.setBounds(133, 402, 198, 25);
		panel.add(entext);

		miltext = new JTextField();
		miltext.setColumns(10);
		miltext.setBounds(133, 456, 198, 25);
		panel.add(miltext);

		htext = new JTextField();
		htext.setColumns(10);
		htext.setBounds(133, 520, 198, 25);
		panel.add(htext);

		rlgntext = new JTextField();
		rlgntext.setColumns(10);
		rlgntext.setBounds(133, 580, 198, 25);
		panel.add(rlgntext);

		JButton picBtn = new JButton("파일 선택");
		pictext = new JTextField();
		pictext.setColumns(10);
		pictext.setBounds(133, 640, 198, 25);
		picBtn.setBounds(350, 640, 50, 23);
		panel.add(pictext);
		panel.add(picBtn);

		addrtext = new JTextField();
		addrtext.setColumns(10);
		addrtext.setBounds(133, 700, 198, 64);
		panel.add(addrtext);

		picBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("C:");
				int SOD = fc.showOpenDialog(null);
				if (SOD != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "경로를 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String Path = fc.getSelectedFile().getAbsolutePath();
				String filePath = Path.replaceAll(Matcher.quoteReplacement(File.separator), "/");
				pictext.setText(filePath);
			}
		});
		JButton addBtn = new JButton("등록");
		addBtn.setBounds(130, 780, 200, 45);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mng = ManageTab.getInstance();
				if (select == "add") {

					dao.nameValue = nametext.getText();
					dao.sidValue = idtext.getText();
					dao.deptValue = depttext.getText();
					if (gendertext.getText().length() == 0)
						dao.genderValue = "데이터 없음";
					else
						dao.genderValue = gendertext.getText();
					if (miltext.getText().length() == 0)
						dao.milValue = "데이터 없음";
					else
						dao.milValue = miltext.getText();

					dao.pnValue = pntext.getText();
					dao.addrValue = addrtext.getText();
					dao.rnValue = rntext.getText();
					dao.enValue = entext.getText();
					if (htext.getText().length() == 0)
						dao.chValue = "데이터 없음";
					else
						dao.chValue = htext.getText();

					if (rlgntext.getText().length() == 0)
						dao.rlgnValue = "데이터 없음";
					else
						dao.rlgnValue = rlgntext.getText();
					dao.pic = pictext.getText();
					dao.MngAdd();
					ManageTab.refresh();

					setVisible(false);
				}
				if (select == "edit") {
					if (nametext.getText().length() == 0) {
						dao.nameValue = nameValue;
					} else {
						dao.nameValue = nametext.getText();
					}
					if (idtext.getText().length() == 0) {
						dao.sidValue = sidValue;
					} else {
						dao.sidValue = idtext.getText();

					}
					if (depttext.getText().length() == 0) {
						dao.deptValue = deptValue;
					} else {
						dao.deptValue = depttext.getText();

					}
					if (gendertext.getText().length() == 0) {
						if (genderValue.length() == 0) {
							dao.genderValue = "데이터 없음";
						} else {
							dao.genderValue = genderValue;
						}
					} else {
						dao.genderValue = gendertext.getText();
					}
					if (miltext.getText().length() == 0) {
						if (milValue.length() == 0) {
							dao.milValue = "데이터 없음";
						} else {
							dao.milValue = milValue;
						}
					} else {
						dao.milValue = miltext.getText();
					}
					if (pntext.getText().length() == 0) {
						dao.pnValue = pnValue;
					} else {
						dao.pnValue = pntext.getText();
					}
					if (addrtext.getText().length() == 0) {
						dao.addrValue = addrValue;
					} else {
						dao.addrValue = addrtext.getText();
					}
					if (rntext.getText().length() == 0) {
						dao.rnValue = rnValue;
					} else {
						dao.rnValue = rntext.getText();
					}
					if (entext.getText().length() == 0) {
						dao.enValue = enValue;
					} else {
						dao.enValue = entext.getText();
					}
					if (htext.getText().length() == 0) {
						if (chValue.length() == 0) {
							dao.chValue = "데이터 없음";
						} else {
							dao.chValue = chValue;
						}

					} else {
						dao.chValue = htext.getText();
					}

					if (rlgntext.getText().length() == 0) {
						if (rlgnValue.length() == 0) {
							System.out.println("if");
							dao.rlgnValue = "데이터 없음";
						} else {
							System.out.println("else");
							dao.rlgnValue = rlgnValue;
						}
					} else {
						dao.rlgnValue = rlgntext.getText();
					}

					if (pictext.getText().length() == 0) {
						dao.pic = picValue;
					} else {
						dao.pic = pictext.getText();
					}
					dao.MngEdit();
					ManageTab.refresh();
					setVisible(false);
				}
			}
		});
		panel.add(notice);
		panel.add(nameLabel);
		panel.add(idLabel);
		panel.add(deptLabel);
		panel.add(nameLabel);
		panel.add(genderLabel);
		panel.add(rnLabel);
		panel.add(pnLabel);
		panel.add(enLabel);
		panel.add(milLabel);
		panel.add(hLabel);
		panel.add(rlgnLabel);
		panel.add(picLabel);
		panel.add(addrLabel);
		panel.add(addBtn);
		add(panel);

		revalidate();

	}

	static StdAddTap getInstance() {
		try {
			if (instance == null) {
				instance = new StdAddTap();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}
}
