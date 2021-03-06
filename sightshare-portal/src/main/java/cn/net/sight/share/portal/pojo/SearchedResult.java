package cn.net.sight.share.portal.pojo;

import java.util.List;

public class SearchedResult {

	// 商品列表
	private List<SearchedItem> itemList;
	// 总记录数
	private Long recordCount;
	// 总页数
	private Long pageCount;
	// 当前页
	private Long curPage;

	public List<SearchedItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchedItem> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
}
