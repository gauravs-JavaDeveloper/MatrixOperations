package com.assignment.matrixprocessorapp.services;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.matrixprocessorapp.entity.Matrix;

/**
 * Implementation of {@link MatrixService} for getting details about longest sub
 * matrix with maximum number of 1s
 * 
 * @author gauravs
 *
 */
@Service
public class MatrixServiceImpl implements MatrixService {
	private final Logger log = LoggerFactory.getLogger(MatrixServiceImpl.class);

	@Override
	public Matrix getLongestSubMatrix(int[][] inputMatrix) {
		Matrix result = new Matrix();
		// Initialize lower left and upper right coordinates
		Point mat_start_point = new Point(0, 0);
		Point mat_end_point = new Point(-1, -1);
		int max_area = 0;

		// Get dimensions of matrix
		final int MaxX = inputMatrix[0].length;
		final int MaxY = inputMatrix.length;

		Stack<Point> stack = new Stack<Point>();

		// Temporary Holder cache for each row of matrix
		int[] auxCache = new int[MaxX + 1];

		for (int row = 0; row != MaxX + 1; row++) {
			auxCache[row] = 0;
		}

		for (int col = 0; col != MaxY; col++) {
			int initWidth = 0; // initial width of sub matrix
			auxCache = updateAuxCache(auxCache, inputMatrix[col], MaxX);
			for (int row = 0; row != MaxX + 1; row++) {
				if (auxCache[row] > initWidth) {
					stack.push(new Point(row, initWidth));
					initWidth = auxCache[row];
				} else if (auxCache[row] < initWidth) {
					int current_area;
					Point tempPoint;
					do {
						tempPoint = stack.pop();
						current_area = initWidth * (row - tempPoint.x);
						// Update point which are covering max area of 1s in
						// matrix
						if (current_area > max_area) {
							max_area = current_area;
							mat_start_point.x = tempPoint.x;
							mat_start_point.y = col;
							mat_end_point.x = row - 1;
							mat_end_point.y = col - initWidth + 1;
						}
						initWidth = tempPoint.y;
					} while (auxCache[row] < initWidth);
					initWidth = auxCache[row];
					if (initWidth != 0) {
						stack.push(tempPoint);
					}
				}
			}
		}

		log.debug("The maximal sub matric has area %d.\n", max_area);
		log.debug("Position: (Staring_row=%d, Staring_col=%d) to (Ending_row=%d, Ending_col=%d)\n", mat_end_point.y + 1,
				mat_start_point.x + 1, mat_start_point.y + 1, mat_end_point.x + 1);
		result = populateResultMatrix(inputMatrix, mat_start_point, mat_end_point);
		return result;

	}

	// Update cache according to elements in give row of matrix
	private int[] updateAuxCache(int[] auxCache, int[] matrixRow, int MaxX) {
		for (int m = 0; m < MaxX; m++) {
			if (matrixRow[m] == 0) {
				auxCache[m] = 0;
			} else {
				auxCache[m]++;
			}
		}
		return auxCache;
	}

	// Populate resultant matrix with all details
	private Matrix populateResultMatrix(int[][] inputMatrix, Point startPoint, Point endPoint) {
		Matrix result = new Matrix();
		int width = (endPoint.x - startPoint.x) + 1;
		int height = 0;
		if (startPoint.y > 0 || startPoint.y > 0) {
			height = (startPoint.y - endPoint.y) + 1;
		}
		log.debug("Dimensions: (Width=%d ,Height=%d)", width, height);
		int[][] elementSet = new int[height][width];

		result.setX(endPoint.y);
		result.setY(startPoint.x);

		result.setWidth(width);
		result.setHeight(height);
		for (int i = endPoint.y, m = 0; m < height && i <= height; i++, m++) {
			for (int j = startPoint.x, n = 0; n < width && j <= width; j++, n++) {
				elementSet[m][n] = inputMatrix[i][j];
			}
		}
		result.setElementSet(elementSet);
		return result;

	}

	private static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
