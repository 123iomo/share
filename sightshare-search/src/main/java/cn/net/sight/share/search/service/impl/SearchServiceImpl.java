package cn.net.sight.share.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.sight.share.search.dao.SearchDao;
import cn.net.sight.share.search.pojo.SearchedResult;
import cn.net.sight.share.search.service.SearchService;

/**
 * 
 * <p>
 * Title: SearchServiceImpl
 * </p>
 * <p>
 * Description:搜索Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月29日下午2:49:18
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchedResult search(String queryStr, int page, int rows) throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queryStr);
		// 设置分页
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜素域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		SearchedResult searchedResult = searchDao.search(query);
		// 计算查询结果总页数
		long recordCount = searchedResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		searchedResult.setPageCount(pageCount);
		searchedResult.setCurPage((long) page);

		return searchedResult;
	}

}
