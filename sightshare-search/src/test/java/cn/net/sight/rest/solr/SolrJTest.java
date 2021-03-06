package cn.net.sight.rest.solr;

import org.junit.Test;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * 
 * <p>Title: SolrJTest</p>
 * <p>Description: SOLRJ连接和功能测试</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月29日上午10:35:30
 * @version 1.0
 */
public class SolrJTest {

	@Test
	public void addDocument() throws Exception {
		// 创建一个连接
		SolrServer server = new HttpSolrServer("http://192.168.231.131:8080/solr");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "TG_01");
		document.addField("item_title", "SOLR测试商品添加2");
		document.addField("item_price", 123);
		// 把文档写入索引库
		server.add(document);
		// 提交，关闭连接
		server.commit();
	}

	@Test
	public void deleteDocument() throws Exception {
		// 创建一个连接
		SolrServer server = new HttpSolrServer("http://192.168.231.131:8080/solr");
		// 按ID进行删除  200--OK
		//server.deleteById("TG_01");
		
		//查询删除--全部删除
		//server.deleteByQuery("*:*");
		server.commit();
	}
	
	@Test
	public void queryDocument() throws Exception{
		SolrServer server = new HttpSolrServer("http://192.168.231.131:8080/solr");
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
		
	}
}
