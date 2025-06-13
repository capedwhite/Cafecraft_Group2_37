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
import view.Workersmanage;

/**
 *
 * @author ASUS
 */
public class Editworkercontroller {
        private final WorkersPanelController workerpanelcontroller;
    private final Employee selectedworker;
            private final Employeedao employeedao;
            private final Editemployee editemployee;
            private Workersmanage workermanage;
            public Editworkercontroller(Editemployee editemployee,Employeedao employeedao,Workersmanage workermanage,Employee selectedworker,WorkersPanelController workerpanelcontroller){
                this.workerpanelcontroller = workerpanelcontroller;
                this.editemployee = editemployee;
                this.selectedworker=selectedworker;
                this.employeedao=employeedao;
                this.workermanage= workermanage;
                editemployee.setFields(selectedworker);
                editemployee.addAddbtnlistener(new Addbtn());
            }
           class  Addbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                        try{
        
           selectedworker.setName(editemployee.getNamefield().getText());
            selectedworker.setNumber(editemployee.getnumber().getText());
            selectedworker.setStatus(editemployee.getsectorfield().getText());
            
            boolean check=employeedao.UpdateItems(selectedworker);
            if(check){
                 JOptionPane.showMessageDialog(editemployee,"edited item sucessfully");
                 
               
                 workerpanelcontroller.loadAllItemsToPanel();
            }
            else{
                JOptionPane.showMessageDialog(editemployee,"failed to edit item");
            }
          
        }
            catch(Exception ex){
            System.out.println("error adding item" + ex.getMessage());
                
            }
        
    }
        }
               
           }
            

