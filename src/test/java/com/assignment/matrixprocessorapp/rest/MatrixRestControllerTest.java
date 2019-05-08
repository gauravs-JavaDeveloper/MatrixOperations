package com.assignment.matrixprocessorapp.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.matrixprocessorapp.MatrixProcessorApplication;
import com.assignment.matrixprocessorapp.entity.Matrix;
import com.assignment.matrixprocessorapp.services.MatrixService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatrixRestControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private MatrixService matService;

	HttpHeaders headers = new HttpHeaders();

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void TestGetLongestSubMatrix() {
		Matrix inputMat = new Matrix();
		int elementSet[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		inputMat.setElementSet(elementSet);
		HttpEntity<Matrix> entity = new HttpEntity<Matrix>(inputMat, headers);
		ResponseEntity<Matrix> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.POST, entity, Matrix.class);
		assertNotNull(response.getBody());
		assertThat(response.getBody().getX() == 1, is(true));
		assertThat(response.getBody().getY() == 0, is(true));
		assertThat(response.getBody().getHeight() == 2, is(true));
		assertThat(response.getBody().getWidth() == 4, is(true));
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void TestGetLongestSubMatrixWithEmptySet() {
		Matrix inputMat = new Matrix();
		int elementSet[][] = { {} };
		HttpEntity<Matrix> entity = new HttpEntity<Matrix>(inputMat, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.POST, entity, String.class);
		response.getStatusCode();
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody(), "Matrix should not be Empty");

	}

	@Test
	public void TestGetLongestSubMatrixWithWrongHttpMethod() {
		Matrix inputMat = new Matrix();
		int elementSet[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		inputMat.setElementSet(elementSet);
		HttpEntity<Matrix> entity = new HttpEntity<Matrix>(inputMat, headers);
		ResponseEntity<Matrix> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.GET, entity, Matrix.class);
		response.getStatusCode();
		assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);

	}

	@Test
	public void TestGetLongestSubMatrixWithWrongAcceptType() {
		Matrix inputMat = new Matrix();
		int elementSet[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		inputMat.setElementSet(elementSet);
		headers.set("accept", "applicaiton/xml");
		HttpEntity<Matrix> entity = new HttpEntity<Matrix>(inputMat, headers);
		ResponseEntity<Matrix> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.POST, entity, Matrix.class);
		response.getStatusCode();
		assertEquals(response.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

	}

	@Test
	public void TestGetLongestSubMatrixWithJsonRequest() throws JSONException {
		int elementSet[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		String input = "{\"elementSet\":" + Arrays.deepToString(elementSet) + "}";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);
		ResponseEntity<Matrix> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.POST, entity, Matrix.class);
		response.getStatusCode();
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	public void TestGetLongestSubMatrixWithJsonRequestWithContentTypeXml() throws JSONException {
		int elementSet[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		String input = "{\"elementSet\":" + Arrays.deepToString(elementSet) + "}";
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);
		ResponseEntity<Matrix> response = restTemplate.exchange(createURLWithPort("/api/v1/longestSubMatrixes"),
				HttpMethod.POST, entity, Matrix.class);
		response.getStatusCode();
		assertEquals(response.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
