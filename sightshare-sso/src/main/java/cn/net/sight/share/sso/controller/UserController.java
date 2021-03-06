package cn.net.sight.share.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.sso.service.UserService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

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
 * @date 2017年2月3日下午3:33:19
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * <p>
	 * Title: checkUserIsExist
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkUserIsExist(@PathVariable String param, @PathVariable Integer type, String callback) {

		SightshareResult result = null;

		// 参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = SightshareResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = SightshareResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = SightshareResult.build(400, "校验内容类型错误");
		}
		// 校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}
		// 调用服务
		try {
			result = userService.checkUserIsExist(param, type);

		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}

	}

	/**
	 * 
	 * <p>
	 * Title: createUser
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public SightshareResult createUser(TbUser user) {
		try {
			SightshareResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public SightshareResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SightshareResult result = userService.userLogin(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(400, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping("/logout/{token}")
	@ResponseBody
	public Object userLogout(HttpServletRequest request, HttpServletResponse response, @PathVariable String token,
			String callback) {
		SightshareResult result = null;
		try {
			result = userService.userLogout(request, response, token);
			if(result.getStatus() == 200){
				response.sendRedirect("http://localhost:8082/index.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, "用户退出时，发生错误");
		}

		// 判断是否为JSONP调用

		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

}
