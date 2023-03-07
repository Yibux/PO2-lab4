import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class ShoppingList {
    LinkedHashMap<String, List<String>> shoppingList;
    LinkedHashMap<String, List<String>> clientShoppingList;

    public ShoppingList(){
        shoppingList = new LinkedHashMap<>();
        clientShoppingList = new LinkedHashMap<>();
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
    public String findProduct(String category, int product){
        String productName = null;
        int i = 1;
        List<String> products = shoppingList.get(category);
        for (String produc : products){
            if(i == product){
                productName = produc;
                break;
            }
        }
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

    public void printShoppingList() throws EmptyStackException{
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
        List<String> productList = shoppingList.get(category);
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

    public void deleteAllProducts(){
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            entry.getValue().clear();
        }
        clientShoppingList.clear();
    }

    public void deleteProductsFromCategory(int category){
        int i = 0;
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            if(i == category){
                entry.getValue().clear();
                break;
            }
            ++i;
        }
    }

    public void deleteSpecificProduct(int category, int product){
        int i = 0;
        for (Map.Entry<String, List<String>> entry : clientShoppingList.entrySet() ){
            if(i == category){
                 entry.getValue().remove(product);
                 break;
            }
            ++i;
        }
    }

}
