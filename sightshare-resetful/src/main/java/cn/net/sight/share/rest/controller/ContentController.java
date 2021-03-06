package cn.net.sight.share.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.rest.service.ContentService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public SightshareResult getContentListById(@PathVariable Long contentCategoryId){
		try {
			List<TbContent> list = contentService.getContentListById(contentCategoryId);
			return SightshareResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
}
