import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        ShoppingList shoppingList = new ShoppingList();
        try{
            shoppingList.loadList();
        }
        catch (FileNotFoundException e){
            System.out.println("Plik nie istnieje!");
            System.exit(1);
        }
        shoppingList.printShoppingList();
        //shoppingList.printCategoryList("Motoryzacja");

        shoppingList.deleteProductsFromCategory(2);
        shoppingList.printShoppingList();
        shoppingList.deleteSpecificProduct(1,1);
        shoppingList.printShoppingList();



    }
}