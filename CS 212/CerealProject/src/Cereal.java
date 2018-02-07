
public class Cereal {
	private int idcereal;
	private String brand;
	private String name;
	private int amount_of_sugar_in_grams;
	private int amount_of_fiber_in_grams;
	private String expiration_date; 
	private String productdescription; 
	private float price_per_box;
	private int inventory;
	
	Cereal(int idcereal, String brand, String name, int amount_of_sugar_in_grams, 
			int amount_of_fiber_in_grams, String expiration_date, float price_per_box,
			String productdescription, int inventory){
		this.setIdcereal(idcereal);
		this.setBrand(brand);
		this.setName(name);
		this.setAmount_of_sugar_in_grams(amount_of_sugar_in_grams);
		this.setAmount_of_fiber_in_grams(amount_of_fiber_in_grams);
		this.setExpiration_date(expiration_date);
		this.setPrice_per_box(price_per_box);
		this.setProductdescription(productdescription);
		this.setInventory(inventory);
		
	}
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getAmount_of_sugar_in_grams() {
		return amount_of_sugar_in_grams;
	}
	public void setAmount_of_sugar_in_grams(int amount_of_sugar_in_grams) {
		this.amount_of_sugar_in_grams = amount_of_sugar_in_grams;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount_of_fiber_in_grams() {
		return amount_of_fiber_in_grams;
	}
	public void setAmount_of_fiber_in_grams(int amount_of_fiber_in_grams) {
		this.amount_of_fiber_in_grams = amount_of_fiber_in_grams;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public float getPrice_per_box() {
		return price_per_box;
	}
	public void setPrice_per_box(float price_per_box) {
		this.price_per_box = price_per_box;
	}
	public int getIdcereal() {
		return idcereal;
	}
	public void setIdcereal(int idcereal) {
		this.idcereal = idcereal;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getProductdescription() {
		return productdescription;
	}
	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}
	
	public void print() {
		System.out.println(getIdcereal() + " | " + getBrand() + " | " + getName() + " | " + 
	getAmount_of_sugar_in_grams() + " | " + getAmount_of_fiber_in_grams() + " | " + getExpiration_date()
	+ " | " + getPrice_per_box() + " | " + getProductdescription() + " | " + getInventory());
	}
	public String createTable(){
		String tmp = "<td>"+ getIdcereal() + "</td>\n"
				+ "<td>"+ getBrand() +"</td>\n"
				+ "<td>"+ getName() +"</td>\n"
				+ "<td>"+ getAmount_of_sugar_in_grams() +"</td>\n"
				+ "<td>"+ getAmount_of_fiber_in_grams() +"</td>\n"
				+ "<td>"+ getExpiration_date() +"</td>\n"
				+ "<td>"+ getPrice_per_box() +"</td>\n"
				+ "<td>"+ getProductdescription() +"</td>\n"
				+ "<td>"+ getInventory() +"</td>\n";
		
		return tmp;
	}
	
	
	
}
