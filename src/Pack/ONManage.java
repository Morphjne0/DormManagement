package Pack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class ONManage extends JPanel {
	String picAddr;
	JComboBox sbox;
	JTextField searchField, destField, tempField, transField;
	static DefaultTableModel model;
	static ONManage instance = null;
	Object ob[][] = new Object[0][9];
	DormManagementDAO dao = DormManagementDAO.getInstance();
	ManageTab mng = ManageTab.getInstance();
	JTable table;
	JLabel transDataLabel, dDateDataLabel, nameDataLabel, idDataLabel, pnDataLabel, deptDataLabel, rnDataLabel,
			enDataLabel, picLabel;
	JPanel picPanel;

	String header[] = { "방번호", " 학 과 ", "  학 번  ", "이 름", " 전 화 번 호 ", "출발일", "행선지", "복귀일", "체온", "복귀" };
	// ArrayList<Data> dataArrayList = new ArrayList<Data>();

	public ONManage() {
		Font indexFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 20);
		Font dataFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 15);
		Font addFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 18);
		TitledBorder border = new TitledBorder(new LineBorder(Color.black), "세부정보");
		TitledBorder returnB = new TitledBorder(new LineBorder(Color.black));
		border.setTitleColor(Color.black);
		setLayout(null);

		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		table.getColumn("방번호").setPreferredWidth(20);
		table.getColumn("체온").setPreferredWidth(20);
		table.getColumn("복귀").setPreferredWidth(20);
		table.getColumn("복귀일").setPreferredWidth(45);
		this.setVisible(true);
		scrollPane.setBounds(38, 38, 804, 900);
		scrollPane.setPreferredSize(new Dimension(200, 200));
		scrollPane.setVisible(true);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
//테이블 정렬기능
		table.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(table.getModel());

		table.setRowSorter(tablesorter);

		ArrayList<ON_Data> dataArrayList = dao.getONList();
		for (ON_Data f : dataArrayList) {
			Vector<String> v = new Vector<String>();
			v.add(f.getRoomNumb());
			v.add(f.getDept());
			v.add(f.getSid());
			v.add(f.getName());
			v.add(f.getPnum());
			v.add(f.getDdate());
			v.add(f.getDesti());
			v.add(f.getRdate());
			v.add(f.getTemp());
			v.add(f.getRet());

			model.addRow(v);
		}
		add(scrollPane);

		// table.updateUI();
//---------------------여기까지 작업(테이블 세부조정중이었음)-------------------------------------------------------------

		JButton ref = new JButton("갱신");
		ref.setBounds(699, 3, 140, 30);
		add(ref);
		ref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ManageTab.select = "외박관리";
				ManageTab.refresh();
			}
		});

		String[] options = { "이름", "학번", "전화번호", "복귀", "미복귀" };
		sbox = new JComboBox<String>(options);
		sbox.setBounds(1110, 30, 80, 25);
		add(sbox);

		searchField = new JTextField();
		JButton searchB = new JButton("  검색  ");
		searchField.setBounds(1220, 30, 250, 25);
		searchB.setBounds(1490, 30, 70, 25);
		add(searchB);
		add(searchField);

		JLabel rnLabel = new JLabel("방번호");
		rnLabel.setBounds(1000, 80, 150, 50);
		rnLabel.setFont(indexFont);
		rnDataLabel = new JLabel("asdf");
		rnDataLabel.setBounds(1150, 80, 150, 50);
		rnDataLabel.setFont(dataFont);
		add(rnLabel);
		add(rnDataLabel);

		JLabel deptLabel = new JLabel("학과");
		deptLabel.setBounds(1000, 160, 150, 50);
		deptLabel.setFont(indexFont);
		deptDataLabel = new JLabel("  asdsadsadasdasd   ");
		deptDataLabel.setBounds(1150, 160, 150, 50);
		deptDataLabel.setFont(dataFont);
		add(deptLabel);
		add(deptDataLabel);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(1000, 240, 150, 50);
		nameLabel.setFont(indexFont);
		nameDataLabel = new JLabel("1234asd");
		nameDataLabel.setBounds(1150, 240, 150, 50);
		nameDataLabel.setFont(dataFont);
		add(nameLabel);
		add(nameDataLabel);

		JLabel idLabel = new JLabel("학번");
		idLabel.setBounds(1000, 320, 150, 50);
		idLabel.setFont(indexFont);
		idDataLabel = new JLabel("asdf");
		idDataLabel.setBounds(1150, 320, 150, 50);
		idDataLabel.setFont(dataFont);
		add(idLabel);
		add(idDataLabel);

		JLabel pnLabel = new JLabel("전화번호");
		pnLabel.setBounds(1000, 400, 150, 50);
		pnLabel.setFont(indexFont);
		pnDataLabel = new JLabel("asdf");
		pnDataLabel.setBounds(1150, 400, 150, 50);
		pnDataLabel.setFont(dataFont);
		add(pnLabel);
		add(pnDataLabel);

		JLabel enLabel = new JLabel("비상연락처");
		enLabel.setBounds(1000, 480, 150, 50);
		enLabel.setFont(indexFont);
		enDataLabel = new JLabel("asdf");
		enDataLabel.setBounds(1150, 480, 150, 50);
		enDataLabel.setFont(dataFont);
		add(enLabel);
		add(enDataLabel);

		JLabel transLabel = new JLabel("이동수단");
		transLabel.setBounds(1000, 560, 150, 50);
		transLabel.setFont(indexFont);
		transDataLabel = new JLabel("asdf");
		transDataLabel.setBounds(1150, 560, 150, 50);
		transDataLabel.setFont(dataFont);
		add(transLabel);
		add(transDataLabel);

		JLabel dest = new JLabel("행선지");
		dest.setBounds(1000, 660, 150, 50);
		dest.setFont(addFont);

		destField = new JTextField();
		destField.setBounds(1080, 670, 220, 30);

		add(dest);
		add(destField);

		JLabel trans = new JLabel("이동수단");
		trans.setBounds(1000, 740, 150, 50);
		trans.setFont(addFont);

		transField = new JTextField();
		transField.setBounds(1080, 750, 220, 30);

		add(trans);
		add(transField);

		JLabel temp = new JLabel("체    온");
		temp.setBounds(1000, 820, 150, 50);
		temp.setFont(addFont);

		tempField = new JTextField();
		tempField.setBounds(1080, 830, 220, 30);

		add(temp);
		add(tempField);

		JButton returnBtn = new JButton("복귀");
		returnBtn.setBounds(1380, 890, 160, 35);
		add(returnBtn);
		JPanel returnP = new JPanel();
		returnP.setBounds(960, 640, 603, 300);
		returnP.setBorder(returnB);
		add(returnP);

		picPanel = new JPanel();

		picAddr = "C:/pic/abcd.png";
		ImageIcon img = new ImageIcon(picAddr);
		picLabel = new JLabel(img);

		picPanel.setBounds(1401, 71, 157, 230);
		add(picPanel);
		picPanel.add(picLabel);

		JPanel panel = new JPanel();
		panel.setBorder(border);
		panel.setBounds(958, 58, 607, 610);
		add(panel);

		// 라디오버튼 추가해서 복귀미복귀 설정할 수 있게하고 검색도 따로 할 수 있도록
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				Date today = new Date();
				SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				if (destField.getText() != null && transField.getText() != null && tempField.getText() != null) {
					dao.rDateValue = date.format(today).toString();
					dao.transValue = transField.getText();
					dao.tempValue = tempField.getText();
					dao.rtrnValue = new String("O");
					dao.ONReturn();
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getONList();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
			}
		});

		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (sbox.getSelectedItem().toString() == "이름") {
					dao.nameValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getON_NameSearch();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "학번") {
					dao.sidValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getON_SidSearch();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "전화번호") {
					dao.pnValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getON_PNSearch();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "복귀") {
					dao.rtrnValue = new String("O");
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getON_returnSearch();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "미복귀") {
					dao.rtrnValue = new String("X");
					model.setNumRows(0);
					ArrayList<ON_Data> dataArrayList = dao.getON_returnSearch();
					for (ON_Data f : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(f.getRoomNumb());
						v.add(f.getDept());
						v.add(f.getSid());
						v.add(f.getName());
						v.add(f.getPnum());
						v.add(f.getDdate());
						v.add(f.getDesti());
						v.add(f.getRdate());
						v.add(f.getTemp());
						v.add(f.getRet());

						model.addRow(v);
					}
				}
			}
		});

		table.addMouseListener(new ML());

		revalidate();
	}

	class ML implements MouseListener {
		public void mousePressed(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {

			int srow = table.getSelectedRow();
			dao.nameValue = (String) table.getValueAt(srow, 3);
			dao.sidValue = (String) table.getValueAt(srow, 2);
			dao.Inspect();
			nameDataLabel.setText(dao.name);
			idDataLabel.setText(dao.sid);
			deptDataLabel.setText(dao.dept);
			rnDataLabel.setText(dao.roomNumb);
			pnDataLabel.setText(dao.pNumb);
			enDataLabel.setText(dao.eNumb);
			transDataLabel.setText(dao.trans);
			picAddr = dao.pic;
			picPanel.remove(picLabel);
			ImageIcon img;

			if (picAddr.length() < 5) {

				picAddr = "C:/pic/abcd.png";
				img = new ImageIcon(picAddr);
				System.out.println("null");
				System.out.println(picAddr);
				System.out.println(dao.pic + "1");
			} else {
				img = new ImageIcon(picAddr);
				System.out.println(picAddr);
				System.out.println(dao.pic);
				System.out.println("사진출력");
			}

			picLabel = new JLabel(img);
			picPanel.add(picLabel);

			revalidate();
		}

		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {
		}

	}

	static ONManage getInstance() {
		try {
			if (instance == null) {
				instance = new ONManage();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}
}