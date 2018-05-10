package mart;

public class main {

	public static void main(String[] args) {
		Item cheese =  new Item("Bega", 1.5, 4.0, 80, 20, 6.0);
		System.out.println(cheese.getName());
		System.out.println(cheese.getManufacturingCost());
		System.out.println(cheese.getSellPrice());
		System.out.println(cheese.getReorderPoint());
		System.out.println(cheese.getReorderAmount());
		System.out.println(cheese.getTempMaximum());
	}

}
