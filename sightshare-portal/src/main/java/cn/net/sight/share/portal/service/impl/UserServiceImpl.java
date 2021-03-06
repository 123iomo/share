package cn.net.sight.share.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.portal.service.UserService;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description: 用户操作服务层
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日上午9:02:29
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;

	@Value("${SSO_GET_USER_TOKEN}")
	public String SSO_GET_USER_TOKEN;

	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			// 调用SSO服务接口，根据TOKEN获取用户信息字符串
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_GET_USER_TOKEN + token);
			// 把返回的JSON值转换成SightShareResult对象
			if (StringUtils.isBlank(json)) {
				return null;
			}

			SightshareResult result = SightshareResult.formatToPojo(json, TbUser.class);

			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
