package cn.net.sight.share.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.portal.service.impl.UserServiceImpl;
import cn.net.sight.share.utils.CookieUtils;

/**
 * 
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月8日下午3:47:18
 * @version 1.0
 */
@Controller
@RequestMapping("/personal")
public class UserController {

	@RequestMapping(value = "/info.html", produces = "text/html;charset=UTF-8")
	public String showUserInfo(HttpServletRequest request, Model model) {
		return "my-info";
	}
	
	@RequestMapping(value = "/img.html", produces = "text/html;charset=UTF-8")
	public String showUserImg(HttpServletRequest request, Model model) {
		return "my-info-img";
	}
	
	@RequestMapping(value = "/more.html", produces = "text/html;charset=UTF-8")
	public String showMore(HttpServletRequest request, Model model) {
		return "my-info-more";
	}
}
