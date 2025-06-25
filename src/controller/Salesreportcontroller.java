/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Salesreport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Vieworders;
import view.dailysalesreport;

/**
 *
 * @author ASUS
 */
public class Salesreportcontroller {
    private final dailysalesreport dailysales;
    private final Salesreport dao = new Salesreport();
    public Salesreportcontroller(dailysalesreport dailysales){
        this.dailysales=dailysales;
        loadsales();
        dailysales.adddonebtnlistener(new donebtn());
    }
    public void loadsales(){
        int orders =dao.showorderreport();
        double sales = dao.showtotalsales();
        dailysales.getorders().setText(String.valueOf(orders));
        dailysales.gettotalsales().setText(String.valueOf(sales));
        
    }
    class donebtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dailysales.dispose();
            Vieworders vieworder = new Vieworders();
            vieworder.setVisible(true);
            Viewordercontroller viewcontrol = new Viewordercontroller(vieworder);
            
        }
        
    }
}
