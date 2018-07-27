package src.test.java.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.main.java.util.Pagination;

public class PaginationTest {

	@Test
	public void testPaginationIncorrectSize() {
		Pagination<String> incorrectPagination1 = new Pagination<>(0, -1, new ArrayList<String>());
		Pagination<String> incorrectPagination2 = new Pagination<>(0, 0, new ArrayList<String>());
		int correctSize = 10;
		assertEquals("Size should be the default value (10)", correctSize, incorrectPagination1.getSize());
		assertEquals("Size should be the default value (10)", correctSize, incorrectPagination2.getSize());		
	}
	
	@Test
	public void testPaginationCorrectSize() {
		Pagination<String> correctPagination = new Pagination<>(0, 1, new ArrayList<String>());
		assertEquals("Size should be the given size (1)", 1, correctPagination.getSize());
	}
	
	@Test
	public void testPaginationIncorrectPage() {
		Pagination<String> incorrectPagination = new Pagination<>(-1, 10, new ArrayList<String>());
		assertEquals("Page should be the default value (0)", 0, incorrectPagination.getPage());
	}
	
	@Test
	public void testPaginationCorrectPage() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		Pagination<String> correctPagination = new Pagination<>(1, 1, list);
		assertEquals("Page should be the given value (1)", 1, correctPagination.getPage());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPaginationNullList() {
		new Pagination<>(0, 10, null);
	}
	
	@Test
	public void testCorrectPageIncorrectPageAndNotEmptyList() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		Pagination<String> incorrectPagination = new Pagination<>(100, 10, list);
		assertEquals("Page should be the last index (0)", 0, incorrectPagination.getPage());
	}
	
	@Test
	public void testCorrectPageIncorrectPageAndEmptyList() {
		Pagination<String> incorrectPagination = new Pagination<>(100, 10, new ArrayList<String>());
		assertEquals("Page should be the last index (0)", 0, incorrectPagination.getPage());
	}
	
	@Test //Test already exists but they don't mean the same thing
	public void testCorrectPageCorrectPageAndNotEmptyList() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> correctPagination = new Pagination<>(1, 1, list);
		assertEquals("Page should be the given value (1)", 1, correctPagination.getPage());
	}
	
	@Test
	public void testCorrectPageCorrectPageAndEmptyList() {
		Pagination<String> correctPagination = new Pagination<>(0, 10, new ArrayList<String>());
		assertEquals("Page should be the given value (0)", 0, correctPagination.getPage());
	}
	
	@Test
	public void testGetPageList() {
		List<String> list = new ArrayList<>();
		list.add("Test 1");
		list.add("Test 2");
		Pagination<String> pagination = new Pagination<>(1, 1, list);
		List<String> page = pagination.getPageList();
		assertEquals("Page list should contain 'Test 2' at index 0", "Test 2", page.get(0));
	}
	
	@Test
	public void testGetTotalPages() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination = new Pagination<>(0, 2, list);
		assertEquals("Total pages should be 2", 2, pagination.getTotalPages());		
	}
	
	@Test
	public void testGetLastIndexNotEmptyList() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination = new Pagination<>(0, 1, list);
		assertEquals("Last index should be 2", 2, pagination.getLastIndex());
	}
	
	@Test
	public void testGetLastIndexEmptyList() {
		Pagination<String> pagination = new Pagination<>(0, 10, new ArrayList<String>());
		assertEquals("Last index should be 0", 0, pagination.getLastIndex());
	}
	
	@Test
	public void testGetBeginIndexZero() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination1 = new Pagination<>(1, 1, list);
		Pagination<String> pagination2 = new Pagination<>(2, 1, list);
		assertEquals("Begin index should be 0", 0, pagination1.getBeginIndex());
		assertEquals("Begin index should be 0", 0, pagination2.getBeginIndex());
	}
	
	@Test
	public void testGetBeginIndexMoreThanZero() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination = new Pagination<>(5, 1, list);
		assertEquals("Begin index should be 3", 3, pagination.getBeginIndex());
	}
	
	@Test
	public void testGetEndIndexLessThanEndIndex() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination = new Pagination<>(0, 1, list);
		assertEquals("End index should be 4", 4, pagination.getEndIndex());
	}
	
	@Test
	public void testGetEndIndexEndIndex() {
		List<String> list = new ArrayList<>();
		list.add("Test");
		list.add("Test");
		list.add("Test");
		Pagination<String> pagination = new Pagination<>(2, 1, list);
		assertEquals("End index should be 2", 2, pagination.getEndIndex());
	}

}
