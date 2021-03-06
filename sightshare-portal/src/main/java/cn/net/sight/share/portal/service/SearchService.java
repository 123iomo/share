package cn.net.sight.share.portal.service;

import cn.net.sight.share.portal.pojo.SearchedResult;

public interface SearchService {

	SearchedResult search(String queryStr, Integer page);
}
