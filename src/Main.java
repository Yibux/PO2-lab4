import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        ShoppingList shpopingList = new ShoppingList();
        try{
            shpopingList.loadList();
        }
        catch (FileNotFoundException e){
            System.out.println("Plik nie istnieje!");
            System.exit(1);
        }
    }
}