package cn.net.sight.share.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.rest.pojo.ItemCatResult;
import cn.net.sight.share.rest.service.ItemCatService;
import cn.net.sight.share.utils.JsonUtils;

/**
 * 
 * <p>
 * Title: ItemCatController
 * </p>
 * <p>
 * Description: 商品分类列表
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月15日下午6:07:57
 * @version 1.0
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping(value="/itemcat/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		ItemCatResult catResult = itemCatService.getItemCatList();
		// 把POJO转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		// 拼装返回值
		String result = callback + "(" + json + ")";
		return result;
	}
	
	/**
	 * 此方法是getItemCatList的增强版，用到了Spring4.1之后新添加类MappingJacksonValue
	 * <p>Title: getItemCatListAdvanced</p>
	 * <p>Description: </p>
	 * @param callback
	 * @return
	 */
	@RequestMapping("/itemcat/list/advanced")
	@ResponseBody
	public Object getItemCatListAdvanced(String callback){
		ItemCatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}
