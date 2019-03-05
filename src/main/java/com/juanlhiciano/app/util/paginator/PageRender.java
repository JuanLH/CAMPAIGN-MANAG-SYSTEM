package com.juanlhiciano.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page<T> page;
	private int totalPages;
	private int elementsByPage;
	private int actualPage;
	private List<PageItem> pages;
	
	public PageRender(String url,Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		elementsByPage = page.getSize();
		totalPages = page.getTotalPages();
		actualPage = page.getNumber() +1;
		
		int from,to;
		if(totalPages<= elementsByPage) {
			from = 1;
			to = totalPages;	
		}
		else {
			if(actualPage <= elementsByPage/2) {
				from = 1;
				to = elementsByPage;
			}
			else if(actualPage >= totalPages - elementsByPage/2) {
				from = totalPages - elementsByPage + 1;
				to = elementsByPage;
			}
			else {
				from = actualPage - elementsByPage/2;
				to = elementsByPage;
			}
		}
		
		for(int  i=0; i<to; i++) {
			pages.add(new PageItem(from + i, actualPage == from+i));
		}
		
	}

	public String getUrl() {
		return url;
	}

	public Page<T> getPage() {
		return page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getElementsByPage() {
		return elementsByPage;
	}

	public int getActualPage() {
		return actualPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean hasNext() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
}
