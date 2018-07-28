package src.main.java.util;

import java.util.List;

/**
 * 
 * Pagination class provides methods to apply pagination on a given list of objects
 *
 * Page represents the current page you want to display 
 * 
 * (Note that the page argument is actually a page index ; remember that the first page is actually page 0 not 1)
 * 
 * Size represents the number of elements to display in one page
 * 
 */

public class Pagination<T> {
	
	private int page;
	private int size;
	private List<T> list;
	
	public Pagination(int page, int size, List<T> list) {
		if(list == null) {																				//List cannot be null or we get a NullPointerException
			throw new IllegalArgumentException("Passed Argument List<T> list is null");
		}
		this.size = (size < 1)? 10 : size;																//If size is less than one it is reset to default value
		this.list = list;
		this.page = (page < 0)? 0 : correctPage(page);													//If page is negative it is reset to 0 otherwise the page is checked by correctPage()
	}																									//Page is initialized at the end because we need size and list for correctPage()

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}
	
	/**
	 * Corrects the page
	 * If it is higher than the last page index (Out of Bounds), it is reset to last page index
	 * @param page is the requested page number
	 * @return correct page number
	 */
	private int correctPage(int page) {				
		int lastPageIndex = getLastIndex();
		return (page > lastPageIndex)? lastPageIndex : page;		
	}
	
	/**
	 * 
	 * @return the sublist of elements corresponding to the given page
	 */
	public List<T> getPageList() {		
		int start = page * size;																		//Start of sublist is calculated with given page and page size
		int end = (start + size) > list.size() ? list.size() : (start + size);							//End of sublist is calculated with page size added to start 
		return list.subList(start, end);																//or is directly end of list if it is the end
	}
	
	/**
	 * 
	 * @return the total number of pages
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) list.size() / size);											//If we have 3 elements and page size = 2 ; 3/2 = 1.5 so we need 2 pages
	}
	
	public int getCurrentIndex() {
		return page;
	}
	
	/**
	 * 
	 * @return the last page index e.g. total of pages -1 because start is 0
	 */
	public int getLastIndex() {
		return (!list.isEmpty())? getTotalPages() - 1 : 0;												//If the list is empty then last index would be -1 because total pages is 0
	}																									//So to avoid errors last index is reset to 0 if list is empty
	
	/**
	 * 
	 * @return the begin index to display the page navigation
	 */
	public int getBeginIndex() {
		return Math.max(0, getCurrentIndex() - 2);														//If current page index is higher than 2 then the begin index increases for better UX
	}
	
	/**
	 * 
	 * @return the end index to display the page navigation
	 */
	public int getEndIndex() {
		return Math.min(getBeginIndex() + 4, getLastIndex());											//If last index is more than 5 pages away it doesn't show for better UX
	}
}
