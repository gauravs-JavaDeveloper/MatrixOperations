package com.assignment.matrixprocessorapp.services;

import com.assignment.matrixprocessorapp.entity.Matrix;

/**
 * @author gauravs
 *
 */
public interface MatrixService {

	/**
	 * Get details of longest sub matrix which contains maximum number of given
	 * character '1'
	 * 
	 * @param inputMatrix
	 * @return
	 */
	public Matrix getLongestSubMatrix(int[][] inputMatrix);

}
