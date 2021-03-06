package cn.net.sight.share.sso.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbUserMapper;
import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.sso.dao.JedisClient;
import cn.net.sight.share.sso.service.TokenService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.JsonUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: TokenServiceImpl
 * </p>
 * <p>
 * Description:用户Token Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午12:57:44
 * @version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbUserMapper userMapper;

	@Value("${USER_SESSION_REDIS_KEY}")
	private String USER_SESSION_REDIS_KEY;

	@Value("${USER_SESSION_REDIS_EXPIRE_TIME}")
	private Integer USER_SESSION_REDIS_EXPIRE_TIME;

	@Override
	public SightshareResult getUserByToken(String token) {
		// 根据token从redis中查询用户信息
		String json = null;
		try {
			//正常情况，从REDIS中获取SS_Token对应的JSON值
			json = jedisClient.get(USER_SESSION_REDIS_KEY + ":" + token);
		} catch (Exception e) {
			//若失败，则尝试从Cookie中获取SS_Token值
			
			String cookie = token.trim();
			String userId = null;
			if(cookie != null && cookie != ""){
				//有Cookie,则说明用户是登陆成功的。
				if(cookie.contains("+")){
					userId = cookie.split("\\+")[1];
				}else {
					cookie = cookie.trim();
					userId = cookie.split(" ")[1];
				}
				TbUser user = userMapper.selectByPrimaryKey(Long.parseLong(userId));
				user.setPassword(null);
				return SightshareResult.ok(user);
			}else{
				System.out.println(ExceptionUtil.getStackTrace(e));
				return SightshareResult.build(400, "此session已经过期，请重新登录,并开启Cookie功能");
			}
		}
		
		// 判断是否为空
		if (StringUtils.isBlank(json)) {
			return SightshareResult.build(400, "此session已经过期，请重新登录");
		}
		// 更新过期时间
		try {
			jedisClient.expire(USER_SESSION_REDIS_KEY + ":" + token, USER_SESSION_REDIS_EXPIRE_TIME);
		} catch (Exception e) {
			System.out.println(ExceptionUtil.getStackTrace(e));
		}
		// 返回用户信息
		return SightshareResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
