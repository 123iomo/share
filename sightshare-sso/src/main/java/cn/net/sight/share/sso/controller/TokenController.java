package cn.net.sight.share.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.sso.service.TokenService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: TokenController
 * </p>
 * <p>
 * Description: 用户Token Controller
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午12:59:27
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	/**
	 * 
	 * <p>Title: getUserByToken</p>
	 * <p>Description: </p>
	 * @param token
	 * @param callback
	 * @param request
	 * @return TbUser对象 或者 MappingJacksonValue对象
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		SightshareResult result = null;
		// 从REDIS中获取SS_Token
		try {
			result = tokenService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		// 判断是否为jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}

	}

}
