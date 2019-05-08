package com.assignment.matrixprocessorapp.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.matrixprocessorapp.MatrixProcessorApplication;
import com.assignment.matrixprocessorapp.entity.Matrix;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MatrixProcessorApplication.class })
public class MatrixServiceImplTest {

	@Autowired
	private MatrixService matService;

	@Test
	public void TestGetLongestSubMatrixWithRectangularForm() {
		int intputMat[][] = { { 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 1, 0, 0 }, };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == 1, is(true));
		assertThat(result.getY() == 1, is(true));
		assertThat(result.getHeight() == 2, is(true));
		assertThat(result.getWidth() == 3, is(true));
	}

	@Test
	public void getLongestSubMatrixTestWithSquareForm() {
		int intputMat[][] = { { 1, 0, 0, 0, 1, 0 }, { 0, 0, 1, 1, 0, 0 }, { 0, 0, 1, 1, 0, 0 } };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == 1, is(true));
		assertThat(result.getY() == 2, is(true));
		assertThat(result.getHeight() == 2, is(true));
		assertThat(result.getWidth() == 2, is(true));
	}

	@Test
	public void getLongestSubMatrixTestWithSingleCol() {
		int intputMat[][] = { { 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1, 0 } };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == 0, is(true));
		assertThat(result.getY() == 4, is(true));
		assertThat(result.getHeight() == 3, is(true));
		assertThat(result.getWidth() == 1, is(true));
	}

	@Test
	public void getLongestSubMatrixTestWithSingleLine() {
		int intputMat[][] = { { 1, 0, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0 } };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == 1, is(true));
		assertThat(result.getY() == 0, is(true));
		assertThat(result.getHeight() == 1, is(true));
		assertThat(result.getWidth() == 5, is(true));
	}

	@Test
	public void getLongestSubMatrixTestWithAllZero() {
		int intputMat[][] = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == -1, is(true));
		assertThat(result.getY() == 0, is(true));
		assertThat(result.getHeight() == 0, is(true));
		assertThat(result.getWidth() == 5, is(false));
	}

	@Test
	public void getLongestSubMatrixTestWithAllOne() {
		int intputMat[][] = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 } };
		Matrix result = matService.getLongestSubMatrix(intputMat);
		assertNotNull(result);
		assertThat(result.getX() == 0, is(true));
		assertThat(result.getY() == 0, is(true));
		assertThat(result.getHeight() == 3, is(true));
		assertThat(result.getWidth() == 6, is(true));
	}

}
