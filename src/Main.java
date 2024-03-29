import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
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

        try{
            shoppingList.loadPreviousList();
        } catch (FileNotFoundException e){
            //do nothing
        }

        Scanner scanner = new Scanner(System.in);
        int categoryId, productId;
        while(true){
            System.out.println("""
                    Co chcesz zrobić:
                    1. Wyswietl wszystkie produkty.
                    2. Wyswietl produkty z danej kategorii.
                    3. Dodaj produkt
                    4. Usuń produkt
                    5. Usuń kategorię i produkty
                    6. Usuń wszystkie produkty i kategorie
                    7. Zapisz moja liste zakupow
                    8. Zakoncz program\s
                    """);

            int choice;
            while (true){
                try{
                    System.out.print("Co chcesz zrobić: ");
                    choice = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Nie podano liczby!");
                    scanner.nextLine();
                }
            }

            switch (choice){
                case 1:
                    try{
                        shoppingList.printShoppingList();
                    } catch (EmptyStackException e){
                        System.out.println("\nLista jest pusta!\n");
                    }
                    break;
                case 2:
                    try{
                        shoppingList.printClientCategories();
                    } catch (EmptyStackException e){
                        System.out.println("\nLista jest pusta!\n");
                        break;
                    }
                    while (true){
                        try{
                            System.out.print("Twój wybór: ");
                            categoryId = scanner.nextInt();
                            shoppingList.printClientCategoryList(shoppingList.findClientCategory(categoryId));
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        }
                        catch (NullPointerException e){
                            System.out.println("Lista jest pusta");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 3:
                    shoppingList.printAllCategories();
                    String category = null;
                    while (true){
                        try{
                            System.out.print("Twój wybór: ");
                            categoryId = scanner.nextInt();
                            category = shoppingList.findCategory(categoryId);
                            shoppingList.printCategoryList(category);
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        }
                        catch (NullPointerException e){
                            System.out.println(e.getMessage());
                            scanner.nextLine();
                        }
                    }

                    while (true){
                        try{
                            System.out.print("Twój wybór: ");
                            productId = scanner.nextInt();
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        }
                    }
                    try{
                        shoppingList.addItem(category, shoppingList.findProduct(category, productId));
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage() + " Nie dodano przedmiotu.");
                    }
                    break;
                case 4:
                    try{
                        shoppingList.printClientCategories();
                    } catch (EmptyStackException e){
                        System.out.println("\nLista jest pusta!\n");
                        break;
                    }
                    category = null;
                    while (true){
                        try{
                            System.out.print("Twój wybór: ");
                            categoryId = scanner.nextInt();
                            category = shoppingList.findClientCategory(categoryId);
                            shoppingList.printClientCategoryList(category);
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        }
                        catch (NullPointerException e){
                            System.out.println("Kategoria nie istnieje!");
                            scanner.nextLine();
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Twój wybór: ");
                            productId = scanner.nextInt();
                            shoppingList.deleteSpecificProduct(category, productId);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage() + " Nie usunieto przedmiotu.");
                        }
                    }
                    break;
                case 5:
                    try{
                        shoppingList.printClientCategories();
                    } catch (EmptyStackException e){
                        System.out.println("\nLista jest pusta!\n");
                        break;
                    }
                    category = null;
                    while (true){
                        try{
                            System.out.print("Twój wybór: ");
                            categoryId = scanner.nextInt();
                            category = shoppingList.findClientCategory(categoryId);
                            shoppingList.deleteProductsFromCategory(category);
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Nie podano liczby!");
                            scanner.nextLine();
                        }
                        catch (NullPointerException e){
                            System.out.println("Kategoria nie istnieje!");
                            scanner.nextLine();
                        }
                        catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage() + " Nie usunieto przedmiotów.");
                        }
                    }

                    break;
                case 6:
                    try{
                        shoppingList.deleteAllProducts();
                    } catch (NullPointerException e){
                        System.out.println("\nLista jest pusta!\n");
                    }
                    break;
                case 7:
                    try {
                        shoppingList.saveMyList();
                        System.out.println("Zapisano!\n");
                    } catch (IOException e){
                        System.out.println("Wystąpił błąd podczas zapisywania danych do pliku.");
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji!");
                    break;
            }
            if(choice == 8)
                break;

        }
        scanner.close();
    }
}