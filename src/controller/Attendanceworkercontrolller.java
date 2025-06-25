/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AttendanceDAO;
import dao.Employeedao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Attendancemodel;
import model.Employee;
import view.AttendanceTrackerr;
import view.Attendancereusepanel;
import view.WorkerPanelreuse;
import view.admin_sidebar;

/**
 *
 * @author ASUS
 */
public class Attendanceworkercontrolller {
    private final AttendanceTrackerr attendancetracker;
    private  List<Attendancereusepanel> panelcollect = new ArrayList<>();
    AttendanceDAO attendancedao = new AttendanceDAO();
    Employeedao employdao= new Employeedao();
    public Attendanceworkercontrolller( AttendanceTrackerr attendancetracker){
        this.attendancetracker = attendancetracker;
        loadAllItemsToPanel();
        attendancetracker.addAddbtnlistener(new addbtn());
        attendancetracker.addexitbtnlistener(new exitbtn());
        panelcollect = attendancetracker.getemployeecards();
    }
     public void loadAllItemsToPanel() {
        List<Employee> employees = employdao.getAllworker(); 
        JPanel workerpanel = attendancetracker.getattendancepanel();

        workerpanel.removeAll();
        for (Employee employee : employees) {
           JPanel card = attendancetracker.addEmployeeCard(employee);
           
           
        }

        workerpanel.revalidate(); 
        workerpanel.repaint();
    }
class addbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           List<Attendancemodel> attendanceList = new ArrayList<>();
try{
    for (Attendancereusepanel wp : panelcollect) {
        Employee employee = wp.getworker();
        String status = wp.getStatus()? "Present" : "Absent";
        attendanceList.add(new Attendancemodel(employee.getId(), status, attendancetracker.getselecteddate().getDate(),employee.getName()));
        
    }
    if(attendancedao.insertMultipleAttendances(attendanceList)){
            JOptionPane.showMessageDialog(attendancetracker, "Attendance saved!");
            attendancetracker.getpresent().setText(String.valueOf(attendancedao.getTotalByStatus("Present",attendancetracker.getselecteddate().getDate())));
            attendancetracker.getabsent().setText(String.valueOf(attendancedao.getTotalByStatus("Absent",attendancetracker.getselecteddate().getDate())));
    }
    else{
        JOptionPane.showMessageDialog(attendancetracker, "error saving Attendance");
    }
} 
catch(Exception ex){
    Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
}

        }
    
        
}
class exitbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            attendancetracker.dispose();
            admin_sidebar admindashboard = new admin_sidebar();
            admindashboard.setVisible(true);
            admindasboardcontroller admindashboardcontrol = new admindasboardcontroller(admindashboard);
        }
    
}
}
