package Pack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

public class ManageTab extends JPanel {
	String search;
	Data d;
	String name, sid;
	static String pic;
	String header[] = { "방번호", " 학 과 ", "  학 번  ", "이 름", " 전 화 번 호 ", "성 별" };
	static DefaultTableModel model;
	static ManageTab instance = null;
	Object ob[][] = new Object[0][6];
	static DormManagementDAO dao = DormManagementDAO.getInstance();
	StdAddTap addTap;
	JTable table;
	static ImageIcon img;
	static JLabel picture;
	static JPanel picPanel;
	static String select;
	JLabel nameDataLabel, idDataLabel, deptDataLabel, rnDataLabel, pnDataLabel, enDataLabel, addrDataLabel,
			genderDataLabel, milDataLabel, chDataLabel, rlgnDataLabel;
	JComboBox<String> sbox;
	JTextField searchField;

	// 현재원관리 창에서 DB에 인원 추가,삭제할수있도록 만들고싶음

	public ManageTab() {
		Font indexFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 20);
		Font dataFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 15);
		TitledBorder border = new TitledBorder(new LineBorder(Color.black), "세부정보");
		TitledBorder picFrame = new TitledBorder(new LineBorder(Color.black));
		border.setTitleColor(Color.black);
		model = new DefaultTableModel(header, 0);
		setLayout(null);

		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		table.getColumn("방번호").setPreferredWidth(10);
		this.setVisible(true);
		scrollPane.setBounds(38, 38, 804, 880);
		scrollPane.setPreferredSize(new Dimension(200, 200));
		scrollPane.setVisible(true);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		// 테이블 정렬기능
		table.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(table.getModel());

		table.setRowSorter(tablesorter);

		ArrayList<Data> dataArrayList = dao.getList();
		for (Data d : dataArrayList) {
			Vector<String> v = new Vector<String>();
			v.add(d.getRoomNumb());
			v.add(d.getDept());
			v.add(d.getSid());
			v.add(d.getName());
			v.add(d.getpNumb());
			v.add(d.getGender());
			model.addRow(v);
		}
		add(scrollPane);

//---------------------여기까지 작업(테이블 세부조정중이었음)-------------------------------------------------------------

		JButton ref = new JButton("갱신");
		ref.setBounds(699, 3, 140, 30);
		add(ref);
		ref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		String[] options = { "이름", "학번", "전화번호", "학과" };
		sbox = new JComboBox<String>(options);
		sbox.setBounds(1110, 30, 80, 25);
		add(sbox);

		searchField = new JTextField();
		JButton searchB = new JButton("  검색  ");
		searchField.setBounds(1220, 30, 250, 25);
		searchB.setBounds(1490, 30, 70, 25);
		add(searchB);
		add(searchField);
		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (sbox.getSelectedItem().toString() == "이름") {
					dao.nameValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<Data> dataArrayList = dao.getMNG_NameSearch();
					for (Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getGender());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "학번") {
					dao.sidValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<Data> dataArrayList = dao.getMNG_SidSearch();
					for (Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getGender());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "전화번호") {
					dao.pnValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<Data> dataArrayList = dao.getMNG_PNSearch();
					for (Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getGender());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "학과") {
					dao.deptValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<Data> dataArrayList = dao.getMNG_DeptSearch();
					for (Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getGender());
						model.addRow(v);
					}
				}
			}
		});

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(1000, 64, 150, 50);
		nameLabel.setFont(indexFont);
		nameDataLabel = new JLabel("");
		nameDataLabel.setBounds(1150, 65, 150, 50);
		nameDataLabel.setFont(dataFont);
		add(nameLabel);
		add(nameDataLabel);

		JLabel idLabel = new JLabel("학번");
		idLabel.setBounds(1000, 135, 150, 50);
		idLabel.setFont(indexFont);
		idDataLabel = new JLabel("");
		idDataLabel.setBounds(1150, 135, 150, 50);
		idDataLabel.setFont(dataFont);
		add(idLabel);
		add(idDataLabel);

		JLabel deptLabel = new JLabel("학과");
		deptLabel.setBounds(1000, 225, 150, 50);
		deptLabel.setFont(indexFont);
		deptDataLabel = new JLabel("");
		deptDataLabel.setBounds(1150, 225, 150, 50);
		deptDataLabel.setFont(dataFont);
		add(deptLabel);
		add(deptDataLabel);

		JLabel rnLabel = new JLabel("방번호");
		rnLabel.setBounds(1000, 305, 150, 50);
		rnLabel.setFont(indexFont);
		rnDataLabel = new JLabel("");
		rnDataLabel.setBounds(1150, 305, 150, 50);
		rnDataLabel.setFont(dataFont);
		add(rnLabel);
		add(rnDataLabel);

		JLabel pnLabel = new JLabel("핸드폰번호");
		pnLabel.setBounds(1000, 395, 150, 50);
		pnLabel.setFont(indexFont);
		pnDataLabel = new JLabel("");
		pnDataLabel.setBounds(1150, 395, 150, 50);
		pnDataLabel.setFont(dataFont);
		add(pnLabel);
		add(pnDataLabel);

		JLabel enLabel = new JLabel("비상연락처");
		enLabel.setBounds(1000, 485, 150, 50);
		enLabel.setFont(indexFont);
		enDataLabel = new JLabel("");
		enDataLabel.setBounds(1150, 485, 150, 50);
		enDataLabel.setFont(dataFont);
		add(enLabel);
		add(enDataLabel);

		JLabel addrLabel = new JLabel("주소");
		addrLabel.setBounds(1000, 575, 150, 50);
		addrLabel.setFont(indexFont);
		addrDataLabel = new JLabel("");
		addrDataLabel.setBounds(1150, 575, 150, 50);
		addrDataLabel.setFont(dataFont);
		add(addrLabel);
		add(addrDataLabel);

		JLabel genderLabel = new JLabel("성별");
		genderLabel.setBounds(1000, 665, 150, 50);
		genderLabel.setFont(indexFont);
		genderDataLabel = new JLabel("");
		genderDataLabel.setBounds(1150, 665, 150, 50);
		genderDataLabel.setFont(dataFont);
		add(genderLabel);
		add(genderDataLabel);

		JLabel milLabel = new JLabel("군필여부");
		milLabel.setBounds(1000, 755, 150, 50);
		milLabel.setFont(indexFont);
		milDataLabel = new JLabel("");
		milDataLabel.setBounds(1150, 755, 150, 50);
		milDataLabel.setFont(dataFont);
		add(milLabel);
		add(milDataLabel);

		JLabel chLabel = new JLabel("병력");
		chLabel.setBounds(1000, 835, 150, 50);
		chLabel.setFont(indexFont);
		chDataLabel = new JLabel("");
		chDataLabel.setBounds(1150, 835, 150, 50);
		chDataLabel.setFont(dataFont);
		add(chLabel);
		add(chDataLabel);

		JLabel rlgnLabel = new JLabel("종교");
		rlgnLabel.setBounds(1000, 910, 150, 50);
		rlgnLabel.setFont(indexFont);

		rlgnDataLabel = new JLabel("");
		rlgnDataLabel.setBounds(1150, 910, 150, 50);
		rlgnDataLabel.setFont(dataFont);
		add(rlgnLabel);
		add(rlgnDataLabel);

		JButton addBtn = new JButton("추가");
		addBtn.setBounds(440, 945, 120, 30);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTap = StdAddTap.getInstance();
				addTap.select = "add";
				addTap.notice.setText("*은 필수 표기사항입니다");
				if (addTap.nametext.getText() != null) {
					addTap.nametext.setText("");
					addTap.idtext.setText("");
					addTap.depttext.setText("");
					addTap.gendertext.setText("");
					addTap.rntext.setText("");
					addTap.pntext.setText("");
					addTap.entext.setText("");
					addTap.pictext.setText("");
					addTap.addrtext.setText("");
					addTap.miltext.setText("");
					addTap.htext.setText("");
					addTap.rlgntext.setText("");
				}
				if (!addTap.isVisible()) {
					addTap.setVisible(true);
				}
			}
		});

		JButton editBtn = new JButton("수정");
		editBtn.setBounds(580, 945, 120, 30);
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				addTap = StdAddTap.getInstance();
				addTap.select = "edit";
				addTap.nameValue = nameDataLabel.getText();
				addTap.sidValue = idDataLabel.getText();
				addTap.deptValue = deptDataLabel.getText();
				addTap.genderValue = genderDataLabel.getText();
				addTap.pnValue = pnDataLabel.getText();
				addTap.rnValue = rnDataLabel.getText();
				addTap.enValue = enDataLabel.getText();
				addTap.milValue = milDataLabel.getText();
				addTap.addrValue = addrDataLabel.getText();
				addTap.chValue = chDataLabel.getText();
				addTap.rlgnValue = rlgnDataLabel.getText();
				addTap.picValue = pic;

				addTap.nametext.setText(nameDataLabel.getText());
				addTap.idtext.setText(idDataLabel.getText());
				addTap.depttext.setText(deptDataLabel.getText());
				addTap.gendertext.setText(genderDataLabel.getText());
				addTap.rntext.setText(rnDataLabel.getText());
				addTap.pntext.setText(pnDataLabel.getText());
				addTap.entext.setText(enDataLabel.getText());
				addTap.pictext.setText(pic);
				addTap.addrtext.setText(addrDataLabel.getText());
				addTap.miltext.setText(milDataLabel.getText());
				addTap.htext.setText(chDataLabel.getText());
				addTap.rlgntext.setText(rlgnDataLabel.getText());
				addTap.notice.setText("수정할 항목을 입력하십시오.");
				if (!addTap.isVisible()) {
					addTap.setVisible(true);
				}
			}
		});

		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setBounds(720, 945, 120, 30);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dao.nameValue = name;
				dao.sidValue = sid;
				dao.MngDelete();
				refresh();
				nameDataLabel.setText(null);
				idDataLabel.setText(null);
				deptDataLabel.setText(null);
				rnDataLabel.setText(null);
				pnDataLabel.setText(null);
				enDataLabel.setText(null);
				addrDataLabel.setText(null);
				genderDataLabel.setText(null);
				milDataLabel.setText(null);
				chDataLabel.setText(null);
				rlgnDataLabel.setText(null);
				picPanel.remove(picture);
			}
		});
		add(addBtn);
		add(deleteBtn);
		add(editBtn);

		picPanel = new JPanel();
		// panel_1.setBackground(Color.GREEN);
		picPanel.setBounds(1401, 71, 157, 230);
		pic = "C:/pic/symbol.png";
		img = new ImageIcon(pic);
		picture = new JLabel(img);

		add(picPanel);
		picPanel.add(picture);
		JPanel panel = new JPanel();
		panel.setBorder(border);
		// panel.setBackground(Color.RED);
		panel.setBounds(958, 58, 607, 910);

		// picture.setBorder(picFrame);
		add(panel);

		table.addMouseListener(new ML());

		revalidate();
	}

	class ML implements MouseListener {
		public void mousePressed(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {

			int srow = table.getSelectedRow();
			name = (String) table.getValueAt(srow, 3);
			sid = (String) table.getValueAt(srow, 2);
			dao.name = name;
			dao.sid = sid;
			dao.Inspect();

			nameDataLabel.setText(dao.name);
			idDataLabel.setText(dao.sid);
			deptDataLabel.setText(dao.dept);
			rnDataLabel.setText(dao.roomNumb);
			pnDataLabel.setText(dao.pNumb);
			enDataLabel.setText(dao.eNumb);
			addrDataLabel.setText(dao.addr);
			System.out.println(dao.addr);
			genderDataLabel.setText(dao.gender);
			milDataLabel.setText(dao.mil);
			if (dao.chis.length() == 0) {
				chDataLabel.setText("없음");
			} else {
				chDataLabel.setText(dao.chis);
			}
			if (dao.rlgn.length() == 0) {
				rlgnDataLabel.setText("무교");
			} else {
				rlgnDataLabel.setText(dao.rlgn);
			}
			// remove(picPanel);
			picPanel.remove(picture);
			if (dao.pic.length() == 0) {
				System.out.println("if");
				pic = "C:/pic/symbol.png";
			} else {
				System.out.println("else");
				pic = dao.pic;
			}

			img = new ImageIcon(pic);
			picture = new JLabel(img);

			picPanel.add(picture);
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

	static ManageTab getInstance() {
		try {
			if (instance == null) {
				instance = new ManageTab();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}

	static void refresh() {

		if (select == "특이사항") {
			DPManage.model.setNumRows(0);
			ArrayList<DP_Data> dataArrayList = dao.getDPList();
			for (DP_Data e : dataArrayList) {
				Vector<String> v = new Vector<String>();
				v.add(e.getRoomNumb());
				v.add(e.getDept());
				v.add(e.getSid());
				v.add(e.getName());
				v.add(e.getpNumb());
				v.add(e.getpDate());
				v.add(e.getSub());
				v.add(e.getState());
				DPManage.model.addRow(v);
			}
		}
		if (select == "외박관리") {
			ONManage.model.setNumRows(0);
			System.out.println("외박관리");
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
				ONManage.model.addRow(v);
			}
		}
		if (select == "상벌점관리") {
			RnPManage.model.setNumRows(0);
			ArrayList<RnP_Data> dataArrayList = dao.getRnP_List();
			for (RnP_Data e : dataArrayList) {
				Vector<String> v = new Vector<String>();
				v.add(e.getRoomNumb());
				v.add(e.getDept());
				v.add(e.getSid());
				v.add(e.getName());
				v.add(e.getPnum());
				v.add(String.valueOf(e.getPnt()));
				RnPManage.model.addRow(v);
			}
		} else {
			model.setNumRows(0);
			ArrayList<Data> dataArrayList = dao.getList();
			for (Data d : dataArrayList) {
				Vector<String> v = new Vector<String>();
				v.add(d.getRoomNumb());
				v.add(d.getDept());
				v.add(d.getSid());
				v.add(d.getName());
				v.add(d.getpNumb());
				v.add(d.getGender());
				model.addRow(v);
			}
		}
	}
}
