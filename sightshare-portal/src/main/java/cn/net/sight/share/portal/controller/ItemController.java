package cn.net.sight.share.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.portal.pojo.SearchedItemInfo;
import cn.net.sight.share.portal.service.ItemService;

/**
 * 
 * <p>Title: ItemController</p>
 * <p>Description: 商品详情页面展示Controller</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年2月3日上午11:18:04
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model){
		SearchedItemInfo item = itemService.geTbItemById(itemId);
		model.addAttribute("item", item);
		
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String showItemDesc(@PathVariable Long itemId){
		String result = itemService.getTbItemDescByTbItemId(itemId);
		return result;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String showItemParam(@PathVariable Long itemId){
		String result = itemService.getTbItemParamByTbItemId(itemId);
		return result;
	}
}
