package cn.net.sight.share.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.search.pojo.SearchedResult;
import cn.net.sight.share.search.service.SearchService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: SearchController
 * </p>
 * <p>
 * Description: 商品查询Controller
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月29日下午2:38:24
 * @version 1.0
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public SightshareResult query(@RequestParam("q") String queryStr, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows) {
		//查询条件不能为空
		if(StringUtils.isBlank(queryStr)){
			return SightshareResult.build(400, "Cann't query for nothing.");
		}
		
		SearchedResult searchResult = null;
		try {
			queryStr = new String(queryStr.getBytes("iso8859-1"), "utf-8");
			searchResult = searchService.search(queryStr, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return SightshareResult.ok(searchResult);
	}
}
