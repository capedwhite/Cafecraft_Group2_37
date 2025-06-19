/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Orderdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import model.Orderjoin;
import model.User;
import view.Orderhistorypanelreuse;
import view.Userorderhistory;
import view.dashboard;

/**
 *
 * @author ASUS
 */
public class Orderhistorycontroller {
    private final Userorderhistory userorderhistory;
    Orderdao orderdao = new Orderdao();
    
    
    int userid= Sessioncontroller.getcurrentuser();

    public Orderhistorycontroller(Userorderhistory userorderhistory){
        this.userorderhistory=userorderhistory;
        System.out.println("the user is" + userid);
        userorderhistory.addbackbtnlistener(new backbtn());
        loadallitems();
        
    }
    public void loadallitems(){
        List<Orderjoin> orderhistorylist = orderdao.getAllorderhistory(userid);
        JPanel historypanel = userorderhistory.gethistorypanel();
        historypanel.removeAll();
        for(Orderjoin orderhistory : orderhistorylist){
            JPanel reusecard = userorderhistory.addhistory(orderhistory);
            
        }
            historypanel.revalidate();
    historypanel.repaint();
    }
    
    class backbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           userorderhistory.dispose();
           dashboard Dashboard = new dashboard();
           Dashboardcontroller dashboardcontrol = new Dashboardcontroller(Dashboard);
           Dashboard.setVisible(true);
        }
        
    }
    
}
