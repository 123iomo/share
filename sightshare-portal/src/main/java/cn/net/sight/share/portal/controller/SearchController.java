package cn.net.sight.share.portal.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.net.sight.share.portal.pojo.SearchedResult;
import cn.net.sight.share.portal.service.SearchService;

/**
 * 
 * <p>
 * Title: SearchController
 * </p>
 * <p>
 * Description: 首页商品搜索Controller
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月29日下午5:01:08
 * @version 1.0
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/search")
	public String search(@RequestParam("q") String queryStr, @RequestParam(defaultValue = "1") Integer page,
			Model model) {
		if (queryStr != null) {
			try {
				queryStr = new String(queryStr.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SearchedResult searchedResult = searchService.search(queryStr, page);
		model.addAttribute("query", queryStr);
		model.addAttribute("totalPages", searchedResult.getPageCount());
		model.addAttribute("itemList", searchedResult.getItemList());
		model.addAttribute("page", page);
		return "search";
	}

	@RequestMapping("/products/{c}")
	public void showCatPage(String c,HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("http://localhost:8082/search.html?q=unity");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
