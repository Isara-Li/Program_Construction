import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ShoppingCart{
	int id;
	List<String> itemList;
	
	public ShoppingCart(int id) {
		this.id = id;
		this.itemList = new ArrayList<String>();
	}
	public void addItem(String newItem) {
		itemList.add(newItem);
	}
	
	public void removeItem(String itemName) throws IllegalArgumentException {
		for (String item : itemList) {
			if(item == itemName) {
				itemList.remove(itemName);
				return;
			}
		}
		throw new IllegalArgumentException("Enter an item in the cart");
	}
	
}

public class BasicException {
	public static void main(String[] args) {
		ShoppingCart sc = new ShoppingCart(0);
		sc.addItem("Item1");
		sc.addItem("Item2");
		try {
		sc.removeItem("Item3");
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
    
	}
}
