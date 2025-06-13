/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Employeedao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import model.Employee;
import view.Workersmanage;
import view.addemployee;
import view.WorkerPanelreuse;
import view.admin_sidebar;


/**
 *
 * @author ASUS
 */
public class WorkersPanelController {
    private final Employeedao employeedao=new Employeedao();
    private final Workersmanage workermanage;
    public WorkersPanelController(Workersmanage workermanage){
        
        this.workermanage=workermanage;    
workermanage.addADDWORKERbtnlistener(new Addworkerlistener());
workermanage.Backbtnlistener(new backbtn());
 loadAllItemsToPanel();
    }
    class Addworkerlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           addemployee addworker = new addemployee(workermanage,true);
           addemployeecontroller addemployecontrol= new addemployeecontroller(employeedao,addworker,workermanage,WorkersPanelController.this);
            addworker.setVisible(true);
            
            
        }
    }
        public void loadAllItemsToPanel() {
        List<Employee> employees = employeedao.getAllworker(); 
        JPanel workerpanel = workermanage.getworkerpanel(); 

        workerpanel.removeAll();
        for (Employee employee : employees) {
           WorkerPanelreuse card = workermanage.addEmployeeCard(employee);
           Workersreusecontroller workercontrol = new Workersreusecontroller(workermanage,employeedao,card,WorkersPanelController.this);
           
        }

        workerpanel.revalidate(); 
        workerpanel.repaint();
    }
    class backbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           workermanage.dispose();
           admin_sidebar adminsidebar = new admin_sidebar();
           admindasboardcontroller admincontrol = new admindasboardcontroller(adminsidebar);
           adminsidebar.setVisible(true);
        }
      
    }
        
    }
    

