import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StdMenu extends JPanel {

    JButton bt1 = new JButton("외박 신청");
    JButton bt2 = new JButton("특이사항 등록");
    JButton bt3 = new JButton("로그아웃");
    JPanel MP = new JPanel();
    JPanel LP = new JPanel();
    DormManagementDAO dao=DormManagementDAO.getInstance();
    Login login=Login.getInstance();
    public StdMenu() {
        setLayout(new BorderLayout());

        Color color = new Color(0xBCE0D8);//0xDEDEDE
        Font f2 = new Font("나눔고딕코딩",Font.PLAIN,12);
        setBackground(color);
        MP.setBackground(color);
        LP.setBackground(color);

        add(MP,BorderLayout.CENTER);
        add(LP,BorderLayout.EAST);

        bt1.setFont(f2);
        bt2.setFont(f2);
        bt3.setFont(f2);
        MP.add(new JLabel("                                        "));
        MP.add(bt1);
        MP.add(new JLabel("   "));
        MP.add(bt2);
        LP.add(bt3);

        bt1.addActionListener(new MenuAL());
        bt2.addActionListener(new MenuAL());
        bt3.addActionListener(new MenuAL());


    }
    class MenuAL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            StdTab stab = (StdTab) getTopLevelAncestor();



            Object[]option={"확인"};
            if (b.getText().equals("외박 신청")) {
                int result = JOptionPane.showConfirmDialog(null, "외박을 신청하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);                //stab.viewP(new ONRequest());
                if(result == JOptionPane.OK_OPTION) {

                    dao.name = login.id;
                    dao.sid = login.pw;
                    System.out.println(dao.name+","+dao.sid);
                    dao.Inspect();
                    System.out.println(dao.name+","+dao.sid);
                    Date today = new Date();
                    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
                    dao.ddate = date.format(today);
                    dao.OnRequest();
                    JOptionPane.showOptionDialog(null, "외박신청이 완료되었습니다.", "확인",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);

                }else{
                }

            } if (b.getText().equals("특이사항 등록")) {
                JOptionPane.showOptionDialog(null, "특이사항 등록 화면으로 이동합니다.", "확인",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);

                stab.viewP(new DPRequest());
            } if (b.getText().equals("로그아웃")) {
                JOptionPane.showOptionDialog(null, "로그아웃합니다.", "확인",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                //dao.disconnect();
                stab.logout();
            }
        }
    }
}
