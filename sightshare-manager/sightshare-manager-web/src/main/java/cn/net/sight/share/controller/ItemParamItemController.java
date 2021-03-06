package cn.net.sight.share.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.sight.share.service.ItemParamItemService;

/**
 * 
 * <p>Title: ItemParamItemController</p>
 * <p>Description: 展示商品规格参数</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月13日下午5:01:31
 * @version 1.0
 */
@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParamList(@PathVariable Long itemId, Model model){
		String result = itemParamItemService.getTbItemParamItemByTbItemId(itemId);
		model.addAttribute("itemParam", result);
		return "item";
		
	}
	
}
