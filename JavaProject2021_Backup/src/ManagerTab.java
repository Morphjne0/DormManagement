import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ManagerTab extends JPanel {
    ManageTab MTab;
    StdTab STab;
    Login login;
    DPManage dpm;
    Menu menu;
    CenterPanel cenP;
    Scanner scan = new Scanner(System.in);

    String Sid;
    String Sname;

    void viewP(JPanel p){
        removeAll();
           add(p, BorderLayout.CENTER);
        revalidate();
    }
    public ManagerTab() {
        setLayout(new BorderLayout());
        cenP= new CenterPanel();
        add(cenP,BorderLayout.CENTER);

    }

}
