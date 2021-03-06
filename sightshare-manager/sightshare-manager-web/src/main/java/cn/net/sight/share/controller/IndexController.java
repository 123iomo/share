package cn.net.sight.share.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Back-Stage Index Controller
 * <p>Title: IndexController</p>
 * <p>Description: </p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月11日下午4:53:39
 * @version 1.0
 */
@Controller
public class IndexController {

	/**
	 * show the index page
	 * <p>Title: showIndex</p>
	 * <p>Description: </p>
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * show another pages
	 * <p>Title: showPage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
