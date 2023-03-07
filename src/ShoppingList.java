import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ShoppingList {
    LinkedHashMap<String, List<String>> shoppingList;
    LinkedHashMap<String, List<String>> clientShoppingList;

    public ShoppingList(){
        shoppingList = new LinkedHashMap<>();
        clientShoppingList = new LinkedHashMap<>();
    }

    public void loadPreviousList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("Moja lista.txt"));
        String line;
        String category = null;
        List<String> products = new ArrayList<>();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.isBlank())
                continue;
            else if(line.charAt(0) != ' '){
                if(category != null) {
                    clientShoppingList.put(category,products);
                }
                category = line;
                products = new ArrayList<>();
            }
            else{
                products.add(line.trim());
            }
        }
        if(category != null) {
            clientShoppingList.put(category,products);
        }
    }
    public void loadList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("lista.txt"));
        String line;
        String category = null;
        List<String> products = new ArrayList<>();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.charAt(0) != ' '){
                if(category != null) {
                    shoppingList.put(category,products);
                }
                category = line;
                products = new ArrayList<>();
            }
            else{
                products.add(line.trim());
            }
        }
        if(category != null) {
            shoppingList.put(category,products);
        }
        scanner.close();
    }

    public String findCategory(int category){
        String categoryName = null;
        int i = 1;
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet()){
            if(i == category){
                categoryName = entry.getKey();
                break;
            }
            ++i;
        }
        return categoryName;
    }
    public String findClientCategory(int category){
        String categoryName = null;
        int i = 1;
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet()){
            if(i == category){
                categoryName = entry.getKey();
                break;
            }
            ++i;
        }
        return categoryName;
    }
    public String findProduct(String category, int product){
        String productName = null;
        int i = 1;
        List<String> products = shoppingList.get(category);
        productName = products.get(product-1);
        return productName;
    }
    public void addItem(String categoryName, String productName){

        /*Dodanie przedmiotu jak kategoria istnieje*/
        if (categoryName == null) {
            throw new IllegalArgumentException("Nieprawidłowa kategoria!");
        }
        else if(productName == null){
            throw new IllegalArgumentException("Nieprawidłowy produkt!");
        }

        List<String> productList = clientShoppingList.computeIfAbsent(categoryName, k -> new ArrayList<>());

        productList.add(productName);

    }

    public void printShoppingList(){
        if(clientShoppingList.isEmpty()) {
            throw new EmptyStackException();
        }
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            System.out.println("Kategoria: " + entry.getKey());
            List<String> products = entry.getValue();
            for (String product : products){
                System.out.println("    - " + product);
            }
        }
        System.out.print("\n");
    }

    public void printCategoryList(String category){
        if(category == null){
            throw new NullPointerException("Kategoria nie istnieje!");
        }
        int i=1;
        System.out.println("Wybierz id produktu: ");
        List<String> productList = shoppingList.get(category);
        for (String product : productList){
            System.out.println(i + ". " + product);
            ++i;
        }
        System.out.print("\n");
    }
    public void printClientCategoryList(String category){
        if(category == null){
            throw new NullPointerException("Kategoria nie istnieje!");
        }
        int i=1;
        System.out.println("Wybierz id produktu: ");
        List<String> productList = clientShoppingList.get(category);
        for (String product : productList){
            System.out.println(i + ". " + product);
            ++i;
        }
        System.out.print("\n");
    }

    public void printAllCategories(){
        int i = 1;
        System.out.println("Podaj id kategorii:");
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            System.out.println(i+". " + entry.getKey());
            ++i;
        }
        System.out.print("\n");
    }

    public void printClientCategories(){
        if(clientShoppingList.isEmpty()) {
            throw new EmptyStackException();
        }
        int i = 1;
        System.out.println("Podaj id kategorii:");
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            System.out.println(i+". " + entry.getKey());
            ++i;
        }
        System.out.print("\n");
    }

    public void deleteAllProducts(){
        if(clientShoppingList.isEmpty())
            throw new NullPointerException();
        clientShoppingList.clear();
    }

    public void deleteProductsFromCategory(String category){
        if (category == null) {
            throw new IllegalArgumentException("Nieprawidłowa kategoria!");
        }
        clientShoppingList.get(category).clear();
        clientShoppingList.remove(category);
    }

    public void deleteSpecificProduct(String category, int product){
        if (category == null) {
            throw new IllegalArgumentException("Nieprawidłowa kategoria!");
        }

        List<String> products = clientShoppingList.get(category);
        if(product - 1 > products.size()){
            throw new IndexOutOfBoundsException("Podano nieprawidłowy indeks!");
        }
        products.remove(product-1);
        if(products.size() == 0)
            clientShoppingList.remove(category);
    }
    public void saveMyList() throws IOException{
        FileWriter writer = new FileWriter("Moja lista.txt", false);
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            writer.write("\n" + entry.getKey());
            List<String> products = entry.getValue();
            for (String product : products){
                writer.write("\n    " + product);
            }
        }
        writer.close();
    }

}
