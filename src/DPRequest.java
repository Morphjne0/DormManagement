import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DPRequest extends JPanel {

	JTextField titletext, pictext;
	JTextArea context;
	Login login = Login.getInstance();
	DormManagementDAO dao = DormManagementDAO.getInstance();

	public DPRequest() {

		this.setSize(400, 600);
		JPanel panel = new JPanel();
		this.setLayout(null);
		Font font = new Font("나눔스퀘어 Bold", Font.PLAIN, 18);
		// add(panel);

		JLabel titleLabel = new JLabel("제목");
		titleLabel.setBounds(34, 73, 100, 25);
		titleLabel.setFont(font);

		JLabel conLabel = new JLabel("내용 ");
		conLabel.setBounds(34, 140, 100, 25);
		conLabel.setFont(font);

		titletext = new JTextField();
		titletext.setColumns(10);
		titletext.setBounds(95, 73, 330, 25);
		add(titletext);

		context = new JTextArea();
		context.setColumns(50);
		context.setBounds(95, 140, 330, 250);
		add(context);

		add(titleLabel);
		add(conLabel);

		JButton addBtn = new JButton("등록");
		addBtn.setBounds(195, 410, 120, 45);
		add(addBtn);

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dao.name = login.id;
				dao.sid = login.pw;
				dao.Inspect();
				dao.deptValue = dao.dept;
				System.out.println(dao.dept);
				System.out.println(dao.deptValue);
				dao.rnValue = dao.roomNumb;
				dao.pnValue = dao.pNumb;
				Date today = new Date();
				SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				dao.pDateValue = date.format(today);
				dao.com = context.getText();
				dao.subValue = titletext.getText();
				dao.DPRequest();
			}
		});
		this.setVisible(true);

		revalidate();

	}

	static DPRequest instance = null;

	static DPRequest getInstance() {
		try {
			if (instance == null) {
				instance = new DPRequest();
			}
		} catch (NullPointerException e) {

		}
		return instance;
	}
}
