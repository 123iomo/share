package cn.net.sight.share.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.net.sight.share.order.service.ShipService;

/**
 * 
 * <p>
 * Title: ShipController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月16日下午2:00:57
 * @version 1.0
 */
@Controller
public class ShipController {

	@Autowired
	private ShipService shipService;

	@RequestMapping(value = "/order/showlink/{TxtTid}/{TxtUserNick}", method = RequestMethod.GET)
	public String showItemLink(@PathVariable String TxtTid, @PathVariable String TxtUserNick, Model model) {
		String form = null;
		try {
			// 返回一个包含有Form表单的HTML片段
			form = shipService.getItemLink(TxtTid, TxtUserNick);
			model.addAttribute("ResultForm", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "result";
	}
	
}
