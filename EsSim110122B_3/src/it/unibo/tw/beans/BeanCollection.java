package it.unibo.tw.beans;

import java.util.ArrayList;
import java.util.List;

public class BeanCollection {

	public List<BeanItem> itemList = new ArrayList<BeanItem>();
	
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("List of BeanItems: ");
		for(BeanItem temp : itemList) {
			strbldr.append(temp);
		}
		strbldr.append("-----------------------");
		return strbldr.toString();
	}
}
