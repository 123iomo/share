package cn.net.sight.share.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.search.service.SearchItemService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>Title: SearchItemController</p>
 * <p>Description: Solr索引库维护</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月29日下午1:51:45
 * @version 1.0
 */
@Controller
@RequestMapping("/manager")
public class SearchItemController {
	
	@Autowired
	private SearchItemService itemService;

	//导入商品数据到索引库
	@RequestMapping("/importall")
	@ResponseBody
	public SightshareResult importAllItems(){
		SightshareResult result = itemService.importAllItems();
		return result;
	}
}
