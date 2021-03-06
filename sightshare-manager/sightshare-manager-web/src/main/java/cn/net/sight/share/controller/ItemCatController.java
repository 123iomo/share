package cn.net.sight.share.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.EUTreeNode;
import cn.net.sight.share.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> list = itemCatService.getTbItemCatList(parentId);
		return list;
	}

}
