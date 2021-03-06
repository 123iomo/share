package cn.net.sight.share.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.service.ContentService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ContentController
 * </p>
 * <p>
 * Description:网站内容展示
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月17日下午5:34:54
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getAllContentList(Long categoryId, Integer page, Integer rows) {
		EUDataGridResult contentList = contentService.getAllContentList(categoryId, page, rows);
		return contentList;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public SightshareResult createConetent(TbContent tbContent){
		SightshareResult result = contentService.createContent(tbContent);
		return result;
	} 
}
