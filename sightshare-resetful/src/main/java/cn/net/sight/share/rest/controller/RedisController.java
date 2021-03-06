package cn.net.sight.share.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.rest.service.RedisService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>Title: RedisController</p>
 * <p>Description: </p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月24日下午11:06:42
 * @version 1.0
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public SightshareResult contentCacheSync(@PathVariable Long contentCid){
		SightshareResult result = redisService.syncContent(contentCid);
		return result;
	}
	
}
