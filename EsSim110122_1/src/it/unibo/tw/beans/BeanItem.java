package it.unibo.tw.beans;

public class BeanItem {

	public boolean equals(Object obj) {
		if(obj instanceof BeanItem) {
			//TODO Add additional check
			
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return "Instance of BeanItem";
	}
	
}
