package cn.net.sight.share.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.portal.pojo.SearchedResult;
import cn.net.sight.share.portal.service.SearchService;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: SearchServiceImpl
 * </p>
 * <p>
 * Description: 首页商品搜索Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月29日下午4:49:28
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchedResult search(String queryStr, Integer page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", queryStr);
		param.put("page", page + "");
		try {
			// 调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			// 把字符串转换成JAVA对象
			SightshareResult result = SightshareResult.formatToPojo(json, SearchedResult.class);
			if (result.getStatus() == 200) {
				SearchedResult searchedResult = (SearchedResult) result.getData();
				return searchedResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
