package it.unibo.tw.beans;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	public int[][] matArray;
	
	public Matrix(int rows, int cols) {
		matArray = new int[rows][cols];
	}
	
	public void add(int col, int row, int val) {
		matArray[row][col] = val;
	}
	
	public Matrix sum(Matrix toAdd) {
		Matrix toRet = new Matrix(matArray.length, matArray[0].length);
		for(int row = 0; row<matArray.length; row++) {
			for(int col = 0; col<matArray[0].length; col++) {
				toRet.add(col, row, this.matArray[row][col]+toAdd.matArray[row][col]);
			}
		}
		return toRet;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Matrix) {
			//TODO Add additional check
			
			return true;
		}else {
			return false;
		}
	}
	
}
