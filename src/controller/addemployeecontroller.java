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
import view.Workersmanage;
import view.addemployee;

/**
 *
 * @author ASUS
 */
public class addemployeecontroller {
    
    private final Employeedao edao;
    private final addemployee addemp;
    private final Workersmanage workermanage;
    public addemployeecontroller( Employeedao edao,addemployee addemp,Workersmanage workermanage){
     
        this.edao=edao;
        this.addemp=addemp;
        this.workermanage=workermanage;
        addemp.addADDbtnlistener(new addbtnlistener());
        addemp.addCancelbtnlistener(new cancelbutton());
    }
    class addbtnlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           try{
            System.out.println("button clicked");
            String name=addemp.Namefield().getText();
            String Status=addemp.SectorField().getText();
            String numbertext=addemp.numberfield().getText();
            
            if (name.isEmpty() || Status.isEmpty() || numbertext.isEmpty()) {
                System.out.println("Please fill all fields");
                return;
            }
            Employee employee = new Employee(name,Status,numbertext);
            boolean check = edao.InsertEmployee(employee);
            if (check){
                   JOptionPane.showMessageDialog(addemp,"new employee added sucessfully");
                   addemp.dispose();
                   WorkersPanelController workerpanel = new WorkersPanelController(workermanage);
                   
                
            }
       else{
                    JOptionPane.showMessageDialog(addemp,"failed to add item");
                    
                    
                }
    }
         catch(Exception ex){
            System.out.println("error adding item" + ex.getMessage());
        }
         
    }
    }
    class cancelbutton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addemp.dispose();
        }
        
}
}
