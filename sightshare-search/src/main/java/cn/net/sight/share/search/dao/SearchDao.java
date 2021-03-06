package cn.net.sight.share.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.net.sight.share.search.pojo.SearchedResult;

public interface SearchDao {

	SearchedResult search(SolrQuery query) throws Exception;
}
