import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class ShoppingList {
    LinkedHashMap<String, List<String>> shoppingList;

    public ShoppingList(){
        shoppingList = new LinkedHashMap<>();
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

    public void printShoppingList(){
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            System.out.println("Kategoria: " + entry.getKey());
            List<String> products = entry.getValue();
            for (String product : products){
                System.out.println("    - " + product);
            }
        }
        System.out.print("\n");
    }

    public void printCategoryList(String category){
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            if(entry.getKey().equals(category)) {
                List<String> products = entry.getValue();
                int i = 1;
                for (String product : products){
                    System.out.println(i + ". " + product);
                }
                break;
            }
        }
        System.out.print("\n");
    }

    public void printAllCategories(){
        int i = 1;
        System.out.println("Dostepne kategorie:");
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            System.out.println(i+". " + entry.getKey());
        }
        System.out.print("\n");
    }

    public void deleteAllProducts(){
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            entry.getValue().clear();
        }
        shoppingList.clear();
    }

    public void deleteProductsFromCategory(int category){
        int i = 0;
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            if(i == category){
                entry.getValue().clear();
                break;
            }
            ++i;
        }
    }

    public void deleteSpecificProduct(int category, int product){
        int i = 0;
        for (Map.Entry<String, List<String>> entry : shoppingList.entrySet() ){
            if(i == category){
                 entry.getValue().remove(product);
                 break;
            }
            ++i;
        }
    }

}
