package cn.net.sight.share.search.service;

import cn.net.sight.share.search.pojo.SearchedResult;

public interface SearchService {

	SearchedResult search(String queryStr, int page, int rows) throws Exception;
}
