package controller;

import dao.Itemdao;
import java.util.List;
import javax.swing.JPanel;
import model.Itemmenu;
import view.MenuItems;
import view.useritempanel;

public class UserMenucontroller {

    private final Itemdao itemdao;
    private final MenuItems menuitem;

    public UserMenucontroller(Itemdao itemdao, MenuItems menuitem) {
        this.itemdao = itemdao;
        this.menuitem = menuitem;

        loadUserItems(); // 🔁 Load menu items into UI
        MenuitemsController menucontroller =new MenuitemsController(menuitem); // ✅ No .init() here
    }

    public void loadUserItems() {
        List<Itemmenu> itemList = itemdao.getAllMenuItems();
        JPanel menuPanel = menuitem.getMenupanel();

        menuPanel.removeAll(); // Clear old items

        for (Itemmenu item : itemList) {
            useritempanel card = menuitem.addItemCard(item); // Create and add card to panel
            menuPanel.add(card);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }
}
