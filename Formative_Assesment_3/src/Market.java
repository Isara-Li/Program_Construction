import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


class item implements Serializable{
    private int itemCode;
    private String itemName;
    private double itemPrice;
    private double itemWeight;
    private String dateOfManufacturing;
    private String dateOfExpiry;
    private String manufacturerName;
    private double discountPercentage;

    public item(int itemCode, String itemName, double itemPrice, double itemWeight, String dateOfManufacturing,
                String dateOfExpiry, String manufacturerName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.dateOfManufacturing = dateOfManufacturing;
        this.dateOfExpiry = dateOfExpiry;
        this.manufacturerName = manufacturerName;
        this.discountPercentage = 0;
    }
    
    public int getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }
    
    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	} 
}

class ItemCodeNotFound extends Exception{
    public ItemCodeNotFound(){};
}

class POS implements Serializable{
	private transient String cashierName;
	private transient String customerName;
	private transient String branchName;
	private Map<Integer,item> itemList ;
	private Map<item,Integer> boughtItems;
	
	public POS(String cashierName, String customerName, String branchName) {
		this.cashierName = cashierName;
		this.customerName = customerName;
		this.branchName = branchName;
		this.itemList = new HashMap<>();
		this.boughtItems = new HashMap<>();
	}
	
	public void addItem(item Item) {
		itemList.put(Item.getItemCode(), Item);
	}
	public item getItemDetails() {
		item Item = null;
		try {
			 InputStreamReader r = new InputStreamReader(System.in);
			 BufferedReader br = new BufferedReader(r);
			 System.out.println("Enter the item code you want to buy");
	
			 Item = itemList.get(Integer.parseInt(br.readLine()));
			 
			 if (Item == null) {
				 throw new ItemCodeNotFound();
			 }
			 
			 } 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
		catch (ItemCodeNotFound e) {
			System.out.println("No such item excists.");
			e.printStackTrace();
		}
			 return Item;
	}
	
	public void buyItem() {
		item boughtItem = this.getItemDetails();
		int amount = 0;
		try {
			InputStreamReader r_0 = new InputStreamReader(System.in);
			BufferedReader br_0 = new BufferedReader(r_0);
			System.out.println("Enter the amount");
			amount = Integer.parseInt(br_0.readLine());
		 
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
		
		boughtItems.put(boughtItem, amount);
	}
	
	public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (item item : boughtItems.keySet()) {
            totalPrice = totalPrice + item.getItemPrice() * boughtItems.get(item);
            double discountedPrice = item.getItemPrice() * (1 - (item.getDiscountPercentage() / 100));
            totalPrice -= discountedPrice;
        }
        return totalPrice;
    }
	
	public void printBill() {
        System.out.println("Cashier Name: " + cashierName);
        System.out.println("Branch Name: " + branchName);
        System.out.println("Customer Name: " + customerName);


        System.out.println("-----------------------------------------------");

        for (item item : boughtItems.keySet()) {
            double discountedPrice = item.getItemPrice() * (1 - (item.getDiscountPercentage() / 100));
            System.out.format("%10d\t%10s\t%10.2f\t%8.2f\t%8.2f\t%8.2f\n", item.getItemCode(),
                    item.getItemName(), item.getItemPrice(), item.getItemWeight(), item.getDiscountPercentage(),
                    discountedPrice);
        }

        System.out.println("-----------------------------------------------");
        
        System.out.format("%56s %8.2f\n", "Total Price:", this.calculateTotalPrice());

        System.out.println("===============================================");
    }
	
}


public class Market {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	item item1 = new item(1, "Item 1", 10.0, 0.5, "May 1", "May 2",
            "Manufacturer A");
    item item2 = new item(2, "Item 2", 20.0, 1.0, "May 1", "May 2",
            "Manufacturer B");
    item item3 = new item(3, "Item 3", 50.0, 1.6,"May 1", "May 2",
            "Manufacturer C");
    item item4 = new item(4, "Item 4", 90.0, 1.8, "May 1", "May 2",
            "Manufacturer D");
    
    POS pos_1 = new POS("cashier1", "customer1", "matara");
    
    pos_1.addItem(item1);
    pos_1.addItem(item2);
    pos_1.addItem(item3);
    pos_1.addItem(item4);
    
    
    pos_1.buyItem();
    System.out.println("Item 1 added successfully (Cus 1)");
    pos_1.buyItem();
    System.out.println("Item 2 added successfully (Cus 1)");
    
    // Serialization of pos_1
    FileOutputStream filestrem = new FileOutputStream("out.ser");
    ObjectOutputStream oStream = new ObjectOutputStream(filestrem);
    
    
    oStream.writeObject(pos_1);
    
    oStream.close();
    
    // New pos is created.
    POS pos_2 = new POS("cashier2", "customer2", "matara");
    pos_2.addItem(item1);
    pos_2.addItem(item2);
    pos_2.addItem(item3);
    pos_2.addItem(item4);
    
    pos_2.buyItem();
    System.out.println("Item 1 added successfully (Cus 2)");
    pos_2.buyItem();
    System.out.println("Item 2 added successfully (Cus 2)");
    
    pos_2.printBill();
    
    
    // Deserialization
    FileInputStream filestrem_0 = new FileInputStream("out.ser");
    ObjectInputStream oStream_0 = new ObjectInputStream(filestrem_0);
    
    
    Object oneObject = oStream_0.readObject();
    POS pos_10 = (POS) oneObject;
    
    oStream_0.close();
    
    pos_10.buyItem();
    System.out.println("Item 3 added successfully (Cus 1)");
    
    
    
    pos_10.printBill();
    

    
    
    
	}
}
