package cn.net.sight.share.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.rest.service.ItemService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>Title: ItemController</p>
 * <p>Description: 商品详情Controller</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年2月2日下午9:26:05
 * @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public SightshareResult getItemBaseInfo(@PathVariable Long itemId){
		SightshareResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public SightshareResult getItemDescInfo(@PathVariable Long itemId){
		SightshareResult result = itemService.getItemDescInfo(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public SightshareResult getItemParamInfo(@PathVariable Long itemId){
		SightshareResult result = itemService.getItemParamInfo(itemId);
		return result;
	}
}
