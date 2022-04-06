
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

public class RnPManage extends JPanel {
	String search, picAddr;
	Data e;
	int srow;
	String name, sid, pic;
	static DefaultTableModel model;
	DefaultTableModel logmodel;
	static RnPManage instance = null;
	DormManagementDAO dao = DormManagementDAO.getInstance();
	ManageTab mng = ManageTab.getInstance();
	JTable table, logTable;
	JPanel picPanel, ImagePanel;
	JLabel nameDataLabel, idDataLabel, deptDataLabel, pnDataLabel, rnDataLabel, picLabel, pntsumData;
	JTextField pntField, rsnField, searchField;
	String time;
	JComboBox sbox;

	String header[] = { "방번호", " 학 과 ", "  학 번  ", "이 름", " 전 화 번 호 ", "벌점" };

	public RnPManage() {
		Font indexFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 20);
		Font dataFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 15);
		Font pntFont = new Font("나눔스퀘어", Font.PLAIN, 15);
		TitledBorder border = new TitledBorder(new LineBorder(Color.black), "세부정보");
		border.setTitleColor(Color.black);
		setLayout(null);

		model = new DefaultTableModel(header, 0);
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

//정렬기능
		table.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(table.getModel());

		table.setRowSorter(tablesorter);

		ArrayList<RnP_Data> dataArrayList = dao.getRnP_List();
		for (RnP_Data e : dataArrayList) {
			Vector<String> v = new Vector<String>();
			v.add(e.getRoomNumb());
			v.add(e.getDept());
			v.add(e.getSid());
			v.add(e.getName());
			v.add(e.getPnum());
			v.add(String.valueOf(e.getPnt()));
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
				mng.select = "상벌점관리";
				ManageTab.refresh();
			}
		});

		String[] options = { "이름", "학번", "전화번호" };
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
		rnDataLabel = new JLabel("  asdsadsadasdasd   ");
		rnDataLabel.setBounds(1150, 80, 150, 50);
		rnDataLabel.setFont(dataFont);
		add(rnLabel);
		add(rnDataLabel);

		JLabel deptLabel = new JLabel("학과");
		deptLabel.setBounds(1000, 160, 150, 50);
		deptLabel.setFont(indexFont);
		deptDataLabel = new JLabel("asdf");
		deptDataLabel.setBounds(1150, 160, 150, 50);
		deptDataLabel.setFont(dataFont);
		add(deptLabel);
		add(deptDataLabel);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(1000, 240, 150, 50);
		nameLabel.setFont(indexFont);
		nameDataLabel = new JLabel("asdf");
		nameDataLabel.setBounds(1150, 240, 150, 50);
		nameDataLabel.setFont(dataFont);
		add(nameLabel);
		add(nameDataLabel);

		JLabel idLabel = new JLabel("학번");
		idLabel.setBounds(1000, 320, 150, 50);
		idLabel.setFont(indexFont);
		idDataLabel = new JLabel("  asdsadsadasdasd   ");
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

		JLabel pntsum = new JLabel("총점 :");
		pntsumData = new JLabel();
		pntsum.setBounds(1000, 500, 150, 50);
		pntsumData.setBounds(1040, 500, 150, 50);
		add(pntsumData);
		add(pntsum);
		pntsum.setFont(pntFont);
		pntsumData.setFont(pntFont);

		String Logheader[] = { "점수", "사유", "일시" };

		logmodel = new DefaultTableModel(Logheader, 0);
		logTable = new JTable(logmodel);
		logTable.getTableHeader().setReorderingAllowed(false);
		logTable.getTableHeader().setResizingAllowed(false);
		JScrollPane logsc = new JScrollPane(logTable);
		logsc.setBounds(988, 550, 550, 350);

		// 테이블 밑에 텍스트필드 2개 버튼한개 만들고 추가할 수 있게,

		DefaultTableCellRenderer logtableRenderer = new DefaultTableCellRenderer();
		logtableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel logtableColumnmodel = logTable.getColumnModel();
		for (int i = 0; i < logtableColumnmodel.getColumnCount(); i++) {
			logtableColumnmodel.getColumn(i).setCellRenderer(logtableRenderer);
		}

		JLabel pnt = new JLabel("점수");
		pntField = new JTextField(10);
		pnt.setBounds(990, 910, 100, 50);
		pntField.setBounds(1030, 920, 120, 30);
		JLabel rsn = new JLabel("사유");
		rsnField = new JTextField();
		rsn.setBounds(1175, 910, 100, 50);
		rsnField.setBounds(1220, 920, 130, 30);
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(1380, 920, 150, 30);

		add(pnt);
		add(pntField);
		add(rsn);
		add(rsnField);
		add(addBtn);
		add(logsc);

		picPanel = new JPanel();
		ImagePanel = new JPanel();
		picAddr = "C:/pic/symbol.png";
		ImageIcon img = new ImageIcon(picAddr);
		picLabel = new JLabel(img);
		picPanel.setBounds(1401, 71, 157, 230);
		ImagePanel.add(picLabel);
		picPanel.add(ImagePanel);
		add(picPanel);
		JPanel panel = new JPanel();
		panel.setBorder(border);
		panel.setBounds(958, 58, 607, 910);
		add(panel);

		searchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (sbox.getSelectedItem().toString() == "이름") {
					dao.nameValue = searchField.getText();
					model.setNumRows(0);
					// dao.getRnP_NameSearch();
					ArrayList<RnP_Data> dataArrayList = dao.getRnP_NameSearch();
					for (RnP_Data d : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(d.getRoomNumb());
						v.add(d.getDept());
						v.add(d.getSid());
						v.add(d.getName());
						v.add(d.getPnum());
						v.add(String.valueOf(d.getPnt()));
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "학번") {
					dao.sidValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<RnP_Data> dataArrayList = dao.getRnP_SidSearch();
					for (RnP_Data d : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(d.getRoomNumb());
						v.add(d.getDept());
						v.add(d.getSid());
						v.add(d.getName());
						v.add(d.getPnum());
						v.add(String.valueOf(d.getPnt()));
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "전화번호") {
					dao.pnValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<RnP_Data> dataArrayList = dao.getRnP_PNSearch();
					for (RnP_Data d : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(d.getRoomNumb());
						v.add(d.getDept());
						v.add(d.getSid());
						v.add(d.getName());
						v.add(d.getPnum());
						v.add(String.valueOf(d.getPnt()));
						model.addRow(v);
					}
				}
			}
		});

		table.addMouseListener(new ML());
		addBtn.addActionListener(new AL());

		revalidate();
	}

	class AL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("추가")) {
				dao.pnt = Integer.parseInt(pntField.getText());
				dao.rsn = rsnField.getText();
				dao.date = date.format(today);
				dao.RnPAdd();
				dao.getPntList();
				dao.RnPUpdate();
				System.out.println();
				model.setNumRows(0);
				ArrayList<RnP_Data> dataArrayList = dao.getRnP_List();
				for (RnP_Data d : dataArrayList) {
					Vector<String> v = new Vector<String>();
					v.add(d.getRoomNumb());
					v.add(d.getDept());
					v.add(d.getSid());
					v.add(d.getName());
					v.add(d.getPnum());
					v.add(String.valueOf(d.getPnt()));
					model.addRow(v);
				}
				logmodel.setNumRows(0);
				ArrayList<RnP_Log> logArrayList = dao.getRnP_LogList();
				for (RnP_Log r : logArrayList) {
					Vector<String> v = new Vector<String>();
					v.add(String.valueOf(r.getPnt()));
					v.add(r.getRsn());
					v.add(r.getDate());
					logmodel.addRow(v);
				}

			}
			pntsumData.setText((String) table.getValueAt(srow, 5));
			revalidate();

		}
	}

	class ML implements MouseListener {
		public void mousePressed(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {
			dao.pic = null;
			picAddr = null;

			int rows = logmodel.getRowCount();
			if (rows != 0) {
				logmodel.setNumRows(0);
			}
			srow = table.getSelectedRow();
			sid = (String) table.getValueAt(srow, 2);
			name = (String) table.getValueAt(srow, 3);
			dao.sidValue = sid;
			dao.nameValue = name;

			dao.getRnP_LogList();
			dao.RnPInfo();
			nameDataLabel.setText(dao.name);
			idDataLabel.setText(dao.sid);
			deptDataLabel.setText(dao.dept);
			rnDataLabel.setText(dao.roomNumb);
			pnDataLabel.setText(dao.pNumb);
			picAddr = dao.pic;
			pntsumData.setText((String) table.getValueAt(srow, 5));

			/* 사진갱신 */ ImagePanel.remove(picLabel);
			picPanel.remove(ImagePanel);
			if (dao.pic.length() == 0) {
				pic = "C:/pic/abc.gif";
			} else {
				pic = dao.pic;
			}

			picPanel.add(ImagePanel);
			// ImagePanel.setBounds(971, 540, 580, 300);
			ImageIcon img;
			img = new ImageIcon(pic);
			picLabel = new JLabel(img);

			ImagePanel.add(picLabel);
			System.out.println(pic);
			revalidate();

			ArrayList<RnP_Log> logArrayList = dao.getRnP_LogList();
			for (RnP_Log r : logArrayList) {
				Vector<String> v = new Vector<String>();
				v.add(String.valueOf(r.getPnt()));
				v.add(r.getRsn());
				v.add(r.getDate());
				logmodel.addRow(v);
			}
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

	static RnPManage getInstance() {
		try {
			if (instance == null) {
				instance = new RnPManage();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}
}