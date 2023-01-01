package it.unibo.tw.beans;

import java.util.ArrayList;
import java.util.List;

public class BeanCollection {

	public List<DatiReader> itemList = new ArrayList<DatiReader>();
	
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("List of BeanItems: ");
		for(DatiReader temp : itemList) {
			strbldr.append(temp);
		}
		strbldr.append("-----------------------");
		return strbldr.toString();
	}
}
