package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items = new ArrayList<Item>();

	public List<Item> getItems() {
		return items;
	}

	public void empty() {
		this.items = new ArrayList<Item>();
	}
	
	public Item getItem(String name) {
		for(Item currIt : items) {
			if(currIt.getDescription().equals(name)) {
			return currIt;
			}
		}
		return null;
	}
	
	public void removeQuantity(Item item, int qtyToReduce) {
		for(Item temp : items) {
			if(temp.equals(item)) {
				temp.setQuantity(temp.getQuantity()-qtyToReduce);
				return;
			}
		}
		return;
	}

}
