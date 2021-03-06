package cn.net.sight.share.pojo;

import java.util.List;

/**
 * 
 * <p>
 * Title: EasyUIResult
 * </p>
 * <p>
 * Description: easyUIDataGrid对象返回值
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月17日下午7:04:24
 * @version 1.0
 */
public class EasyUIResult {

	private Integer total;

	private List<?> rows;

	public EasyUIResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public EasyUIResult(long total, List<?> rows) {
		this.total = (int) total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
