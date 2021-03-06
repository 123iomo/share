package cn.net.sight.share.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <p>
 * Title: PageController
 * </p>
 * <p>
 * Description: SSO页面显示控制
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月4日上午11:15:51
 * @version 1.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@RequestMapping("/login")
	public String showLoginPage(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
