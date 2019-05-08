package com.assignment.matrixprocessorapp.entity;

import javax.validation.constraints.NotEmpty;

/**
 * Entity class for Matrix with properties
 * 
 * @author gauravs
 *
 */
public class Matrix {

	int x;
	int y;
	int width;
	int height;
	@NotEmpty(message = "{Please provide valid matrix of integers}")
	int[][] elementSet;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getElementSet() {
		return elementSet;
	}

	public void setElementSet(int[][] elementSet) {
		this.elementSet = elementSet;
	}

}
