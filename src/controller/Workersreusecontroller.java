/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Employeedao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Employee;
import view.Editemployee;
import view.WorkerPanelreuse;
import view.Workersmanage;

/**
 *
 * @author ASUS
 */
public class Workersreusecontroller {
    private final Workersmanage workermanage;
    private final Employeedao employeedao;
    private final WorkerPanelreuse panelreuse;
    private final WorkersPanelController workerpanelcontroller;
    public Workersreusecontroller (Workersmanage workermanage,Employeedao employeedao,WorkerPanelreuse panelreuse,WorkersPanelController workerpanelcontroller){
        this.workerpanelcontroller = workerpanelcontroller;
        this.workermanage=workermanage;
        this.employeedao=employeedao;
        this.panelreuse=panelreuse;
        panelreuse.addEditbtnlistener(new editbtn());
        panelreuse.adddeletebtnlistener(new deletebtn());
    }
    
    class editbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Employee selectedworker = panelreuse.Selectedworker();
            System.out.println("button clicked");
            Editemployee editemployee = new Editemployee();
           
            Editworkercontroller editworkercontrol = new Editworkercontroller(editemployee,employeedao,workermanage,selectedworker,workerpanelcontroller);
             editemployee.setVisible(true);
        }
        
    }
    class deletebtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        try{
             System.out.println("button clicked");
             Employee selectedItem = panelreuse.Selectedworker();
             boolean check = employeedao.deleteItemById(selectedItem);
             if(check){
                 JOptionPane.showMessageDialog(panelreuse,"Deleted employee sucessfully");
               
                workerpanelcontroller.loadAllItemsToPanel();
                 
             }
             else{
                 JOptionPane.showMessageDialog(panelreuse,"couldnt delete employee");
             }
             
        }
            catch(Exception ex){
            System.out.println("error deleting worker" + ex.getMessage());
                 }
}
    }
        
    }
    

