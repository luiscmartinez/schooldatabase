package schooldatabase;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import java.util.List;
import java.util.function.Consumer;

public class MenuFactory {

    /**
     * Creates a menu with the specified title.
     * 
     * @param title The title of the menu.
     * @return A new Menu object.
     */
    public static Menu createMenu(String title) {
        return new Menu(title);
    }

    /**
     * Creates a menu item with the specified title and action.
     * 
     * @param title  The title of the menu item.
     * @param action The action to perform when the menu item is clicked.
     * @return A new MenuItem object.
     */
    public static MenuItem createMenuItem(String title, Runnable action) {
        MenuItem menuItem = new MenuItem(title);
        menuItem.setOnAction(e -> action.run());
        return menuItem;
    }

    /**
     * Creates a menu with the specified title and a list of menu items.
     * 
     * @param title The title of the menu.
     * @param items A list of MenuItem objects to be added to the menu.
     * @return A new Menu object containing the provided menu items.
     */
    public static Menu createMenuWithItems(String title, List<MenuItem> items) {
        Menu menu = new Menu(title);
        menu.getItems().addAll(items);
        return menu;
    }

    /**
     * Dynamically adds menu items to a given menu.
     * 
     * @param menu  The menu to which menu items will be added.
     * @param items A list of MenuItem objects to be added.
     */
    public static void addItemsToMenu(Menu menu, List<MenuItem> items) {
        menu.getItems().addAll(items);
    }

    /**
     * Creates a menu item that performs a specific action on a target.
     * 
     * @param title  The title of the menu item.
     * @param target The target on which the action will be performed.
     * @param action The action to perform, accepting the target as input.
     * @param <T>    The type of the target.
     * @return A new MenuItem object.
     */
    public static <T> MenuItem createMenuItemWithAction(String title, T target, Consumer<T> action) {
        MenuItem menuItem = new MenuItem(title);
        menuItem.setOnAction(e -> action.accept(target));
        return menuItem;
    }
}
