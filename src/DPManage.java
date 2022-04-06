import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class DPManage extends JPanel {
	JComboBox sbox;
	JTextField searchField;
	String name, sid, sub, pDate;
	static DefaultTableModel model;
	static DPManage instance = null;
	Object ob[][] = new Object[0][7];
	DormManagementDAO dao = DormManagementDAO.getInstance();
	ManageTab mng = ManageTab.getInstance();
	JTable table;
	JLabel titleDataLabel;
	JLabel subLabel;

	String header[] = { "방번호", " 학 과 ", "  학 번  ", "이 름", " 전 화 번 호 ", "제목", "등록일", "상태" };

	public DPManage() {
		Font indexFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 20);
		Font dataFont = new Font("나눔스퀘어 Bold", Font.PLAIN, 15);
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

		// 테이블 정렬기능
		table.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(table.getModel());

		table.setRowSorter(tablesorter);

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
				mng.select = "특이사항";
				ManageTab.refresh();
			}
		});

		String[] options = { "이름", "학번", "전화번호", "진행중", "해결" };
		sbox = new JComboBox<String>(options);
		sbox.setBounds(1110, 30, 80, 25);
		add(sbox);

		searchField = new JTextField();
		JButton searchB = new JButton("  검색  ");
		searchField.setBounds(1220, 30, 250, 25);
		searchB.setBounds(1490, 30, 70, 25);
		add(searchB);
		add(searchField);

		JLabel titleLabel = new JLabel("제목");
		titleLabel.setBounds(1000, 90, 150, 50);
		titleLabel.setFont(indexFont);
		titleDataLabel = new JLabel("건의사항"); // DB에 저장되있는 제목
		titleDataLabel.setBounds(1125, 91, 450, 50);
		titleDataLabel.setFont(dataFont);
		add(titleLabel);
		add(titleDataLabel);

		subLabel = new JLabel("내용");
		subLabel.setBounds(1000, 170, 580, 360);
		subLabel.setFont(dataFont);
		subLabel.setBackground(Color.green);
		subLabel.setVerticalAlignment(SwingConstants.TOP);
		add(subLabel);

		JButton solve = new JButton("처리완료");
		solve.setBounds(1365, 920, 200, 40);
		add(solve);

		JPanel panel = new JPanel();
		panel.setBorder(border);
		panel.setBounds(958, 58, 607, 850);

		JLabel textLabel = new JLabel();
		textLabel.setBounds(971, 100, 580, 400);
		textLabel.setOpaque(true);
		add(textLabel);
		add(panel);

		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				dao.nameValue = name;
				dao.sidValue = sid;
				dao.subValue = sub;
				dao.pDateValue = pDate;
				int result = JOptionPane.showConfirmDialog(null, "계속 하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					dao.DPSolve();
					model.setNumRows(0);
					ArrayList<DP_Data> dataArrayList = dao.getDPList();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
						model.addRow(v);
					}
				} else {
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
					ArrayList<DP_Data> dataArrayList = dao.getDP_NameSearch();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "학번") {
					dao.sidValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<DP_Data> dataArrayList = dao.getDP_NameSearch();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "전화번호") {
					dao.pnValue = searchField.getText();
					model.setNumRows(0);
					ArrayList<DP_Data> dataArrayList = dao.getDP_PNSearch();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "진행중") {
					dao.solveValue = "X";
					dao.state = searchField.getText();
					model.setNumRows(0);
					ArrayList<DP_Data> dataArrayList = dao.getDP_NameSearch();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
						model.addRow(v);
					}
				}
				if (sbox.getSelectedItem().toString() == "해결") {
					dao.solveValue = "O";
					dao.state = searchField.getText();
					model.setNumRows(0);
					ArrayList<DP_Data> dataArrayList = dao.getDP_solveSearch();
					for (DP_Data data : dataArrayList) {
						Vector<String> v = new Vector<String>();
						v.add(data.getRoomNumb());
						v.add(data.getDept());
						v.add(data.getSid());
						v.add(data.getName());
						v.add(data.getpNumb());
						v.add(data.getpDate());
						v.add(data.getSub());
						v.add(data.getState());
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
			name = (String) table.getValueAt(srow, 3);
			sid = (String) table.getValueAt(srow, 2);
			sub = (String) table.getValueAt(srow, 5);
			pDate = (String) table.getValueAt(srow, 6);

			dao.nameValue = name;
			dao.sidValue = sid;
			dao.Inspect();

			titleDataLabel.setText(dao.sub);
			subLabel.setText(dao.com);

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

	static DPManage getInstance() {
		try {
			if (instance == null) {
				instance = new DPManage();
			}
		} catch (NullPointerException e) {
		}
		return instance;
	}
}