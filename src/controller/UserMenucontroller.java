package controller;

//import dao.Itemdao;
//import java.util.List;
//import javax.swing.JPanel;
import dao.Itemdao;
import dao.Orderdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Itemmenu;
import model.Orderentry;
import view.MenuItems;
import view.Orderconfirmation;
import view.useritempanel;

public class UserMenucontroller {

    private final Itemdao itemdao;
    private final MenuItems menuitem;
    private List<useritempanel> itempanel;
    private final Orderdao orderdao = new Orderdao();
List<Orderentry> currentOrderList = new ArrayList<>();
    public UserMenucontroller(Itemdao itemdao, MenuItems menuitem,List<useritempanel> itempanel) {
        this.itemdao = itemdao;
        this.menuitem = menuitem;
        this.itempanel=itempanel;
        menuitem.addConfirmListener(new confirmlistener());
        menuitem.addbtnResetListener(new resetbtn());
        menuitem.addcombolistener(new combolistener());

        loadUserItems(); // 🔁 Load menu items into UI
//        MenuitemsController menucontroller =new MenuitemsController(menuitem); 
    }

    public void loadUserItems() {
        List<Itemmenu> itemList = itemdao.getAllMenuItems();
        JPanel menuPanel = menuitem.getMenupanel();

        menuPanel.removeAll(); // Clear old items
    itempanel = new ArrayList<>(); 
        for (Itemmenu item : itemList) {
            useritempanel card = menuitem.addItemCard(item); // Create and add card to panel
            menuPanel.add(card);
            itempanel.add(card);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }
    
    class confirmlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int userID = Sessioncontroller.getcurrentuser();
            System.out.println("DEBUG - Logged-in user ID: " + userID);
          try {
            System.out.println("button clicked");

            boolean hasSelection = false;
            boolean Sucess = true;
            
            for (useritempanel panel : itempanel) {
                int quantity = (Integer) panel.getQuantity().getValue();
                if (panel.isSelected() & quantity > 0){
                    hasSelection = true;

                    Itemmenu item = panel.getItem();
                   

                    Orderentry order = new Orderentry(item.getName(), item.getPrice(), quantity, item.getId());
                    currentOrderList.add(order);
                    double totalPrice = 0;
                  totalPrice += order.getUnitPrice() * order.getQty() ;
                    boolean check = orderdao.Insertorderdetails(order, userID,totalPrice);

                    if (!check) {
                        Sucess=false;
                        JOptionPane.showMessageDialog(menuitem, "failed to insert item");
                    }
                }
            }

            if (!hasSelection) {
         JOptionPane.showMessageDialog(menuitem, "You have to select at least one item first!");
            }
         if(Sucess & hasSelection){
             JOptionPane.showMessageDialog(menuitem, "Items ordered successfully!");
                Orderconfirmation orderconfirmation = new Orderconfirmation(menuitem, true);
                OrderConfirmationcontroller ordercontrol= new OrderConfirmationcontroller(orderconfirmation,currentOrderList,menuitem);
                orderconfirmation.setVisible(true);

        } 
          }catch (Exception ex) {
            Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(menuitem, "An error occurred while placing the order.");
        }
    }
}
    class resetbtn implements ActionListener{

        @Override
       
        public void actionPerformed(ActionEvent e) {
             for (useritempanel panel : itempanel) {
            panel.getplaceorder().setSelected(false);
            panel.getQuantity().setValue(1);
            
        }
    }
        
}
    class combolistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             String selectedCategory = (String) menuitem.getfilterfield().getSelectedItem();
         JPanel menuPanel = menuitem.getMenupanel();
        // Fetch filtered items from DB
        List<Itemmenu> filteredItems;
        if (selectedCategory.equals("All")) {
            filteredItems = itemdao.getAllMenuItems();
        } else {
            filteredItems = itemdao.getItemsByCategory(selectedCategory);
        }
         menuPanel.removeAll(); 
        
           for (Itemmenu item : filteredItems) {
            useritempanel card = menuitem.addItemCard(item); // Create and add card to panel
            menuPanel.add(card);
            itempanel.add(card);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }
       
        }
        
}
