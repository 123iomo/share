package cn.net.sight.share.pojo;

import java.util.List;

/**
 * 
 * <p>
 * Title: EUDataGridResult
 * </p>
 * <p>
 * Description: the result came from EasyUI
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月11日下午6:57:34
 * @version 1.0
 */
public class EUDataGridResult {

	private long total;
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
