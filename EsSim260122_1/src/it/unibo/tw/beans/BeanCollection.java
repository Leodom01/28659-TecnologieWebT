package it.unibo.tw.beans;

import java.util.ArrayList;
import java.util.List;

public class BeanCollection {

	public List<Asta> itemList = new ArrayList<Asta>();
	
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("List of BeanItems: ");
		for(Asta temp : itemList) {
			strbldr.append(temp);
		}
		strbldr.append("-----------------------");
		return strbldr.toString();
	}
}
