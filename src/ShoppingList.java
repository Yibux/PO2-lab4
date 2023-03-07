import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class ShoppingList {
    HashMap<String, List<String>> shoppingList;

    public ShoppingList(){
        shoppingList = new HashMap<String, List<String>>();
    }

    public void loadList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("lista.txt"));
        String line;
        String category = null;
        List<String> products = new ArrayList<String>();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.charAt(0) != ' '){
                if(category != null) {
                    shoppingList.put(category,products);
                }
                category = line;
                products = new ArrayList<String>();
            }
            else{
                products.add(line.trim());
            }
        }
        if(category != null) {
            shoppingList.put(category,products);
        }
    }

}
