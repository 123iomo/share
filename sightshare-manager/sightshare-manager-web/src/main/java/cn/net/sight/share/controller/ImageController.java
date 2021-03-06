package cn.net.sight.share.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.net.sight.share.service.ImageService;
import cn.net.sight.share.utils.JsonUtils;

/**
 * 
 * <p>Title: ImageController</p>
 * <p>Description: 上传图片处理</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月12日下午7:47:39
 * @version 1.0
 */
@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String imageUpLoad(MultipartFile uploadFile){
		Map map = imageService.upLoadImage(uploadFile); 
		//为了保证功能的兼容性，需要把JAVA对象，转换成JSON
		String map2Json = JsonUtils.objectToJson(map);
		return map2Json;
	}
}
