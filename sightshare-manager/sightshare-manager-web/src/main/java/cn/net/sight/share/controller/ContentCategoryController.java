package cn.net.sight.share.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.EUTreeNode;
import cn.net.sight.share.service.ContentCategoryService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>Title: ContentCategoryController</p>
 * <p>Description: CMS系统内容列表展示</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月17日下午12:01:41
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0") Long parentId){
		List<EUTreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public SightshareResult createContentCategory(Long parentId, String name){
		SightshareResult result = contentCategoryService.createContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/update")
	public SightshareResult updateContentCategory(Long id, String name){
		SightshareResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public SightshareResult removeContentCategory(Long id){
		SightshareResult result = contentCategoryService.removeContentCategory(id);
		return result;
	}
	
}
