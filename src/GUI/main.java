package GUI;

import delivery.*;
import stock.*;
import java.util.HashMap;

public class main {

	public static void main(String[] args) {
		Store store = Store.getInstance();
		System.out.println(store.getCapital());
	}

}
