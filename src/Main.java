import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                    Co chcesz zrobić:
                    1. Wyswietl wszystkie produkty.
                    2. Wyswietl produkty z danej kategorii.
                    3. Dodaj produkt
                    4. Usun produkt
                    5. Usun kategorie
                    6. Usun produkty z danej kategorii
                    7. Zapisz moja liste zakupow
                    8. Zakoncz program\s
                    """);
        while(true){

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji!");
                    break;
            }
        }

//        shoppingList.printShoppingList();
//        //shoppingList.printCategoryList("Motoryzacja");
//
//        shoppingList.deleteProductsFromCategory(2);
//        shoppingList.printShoppingList();
//        shoppingList.deleteSpecificProduct(1,1);
//        shoppingList.printShoppingList();



    }
}