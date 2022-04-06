import javax.swing.*;
import java.awt.*;

public class DormManager extends JFrame {

    ManageTab MTab;
    ManagerTab MngTab;
    RnPManage RnPTab;
    StdMenu smenu;
    Menu menu;
    CenterPanel cenP;
    Login login;
    JPanel vP = new JPanel(new BorderLayout());
    JLabel version = new JLabel("Version. A01");
    Container c = getContentPane();
    static DormManager instance=null;
    void viewP(JPanel p){
        c.remove(cenP);
         c.add(p, BorderLayout.CENTER);
        revalidate();
    }


    public DormManager(){
        MTab= new ManageTab();
        MngTab= new ManagerTab();
        cenP = new CenterPanel();
        menu = new Menu();
        RnPTab = new RnPManage();
        Color color = new Color(0xDCD3B7);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.add(cenP,BorderLayout.CENTER);
        setTitle("Dormitary Management Program");
        this.setVisible(true);
        this.setSize(1680,1080);


        vP.add(version,BorderLayout.EAST);
        c.add(vP,BorderLayout.SOUTH);
        //c.add(menu,BorderLayout.NORTH);
        vP.setBackground(color);

        setResizable(false);

    }
    static DormManager getInstance() throws NullPointerException{
            if(instance==null){instance=new DormManager();}
        return instance;
    }
    void logout(){
        login=Login.getInstance();
        setVisible(false);
        login.reshow();
    }
}
