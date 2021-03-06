package cn.net.sight.share.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.net.sight.share.mapper.TbUserMapper;
import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.pojo.TbUserExample;
import cn.net.sight.share.pojo.TbUserExample.Criteria;
import cn.net.sight.share.sso.dao.JedisClient;
import cn.net.sight.share.sso.service.UserService;
import cn.net.sight.share.utils.CookieUtils;
import cn.net.sight.share.utils.JsonUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description: SSO用户登陆Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月3日下午3:23:37
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${USER_SESSION_REDIS_KEY}")
	private String USER_SESSION_REDIS_KEY;

	@Value("${USER_SESSION_REDIS_EXPIRE_TIME}")
	private Integer USER_SESSION_REDIS_EXPIRE_TIME;

	/**
	 * 
	 * <p>
	 * Title: checkUserIsExist
	 * </p>
	 * <p>
	 * Description: 检查用户信息是否存在且合法
	 * </p>
	 * 
	 * @param content
	 * @param type
	 * @return
	 * @see cn.net.sight.share.sso.service.UserService#checkUserIsExist(java.lang.String,
	 *      java.lang.Integer)
	 */
	@Override
	public SightshareResult checkUserIsExist(String content, Integer type) {
		// 创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 对数据进行校验：1、2、3分别代表username、phone、email
		// 用户名校验
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
			// 电话校验
		} else if (2 == type) {
			criteria.andPhoneEqualTo(content);
			// email校验
		} else {
			criteria.andEmailEqualTo(content);
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return SightshareResult.ok(true);
		}
		return SightshareResult.ok(false);
	}

	/**
	 * 
	 * <p>
	 * Title: createUser
	 * </p>
	 * <p>
	 * Description: 新增用户信息
	 * </p>
	 * 
	 * @param user
	 * @return
	 * @see cn.net.sight.share.sso.service.UserService#createUser(cn.net.sight.share.pojo.TbUser)
	 */
	@Override
	public SightshareResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		int count = userMapper.insert(user);
		if (count >= 0) {
			return SightshareResult.ok();
		} else {
			return SightshareResult.build(500, "Create User Failed");
		}
	}

	/**
	 * 
	 * <p>
	 * Title: userLogin
	 * </p>
	 * <p>
	 * Description: 用户登陆Service
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @see cn.net.sight.share.sso.service.UserService#userLogin(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public SightshareResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);

		// 查询是否存在此用户
		if (list == null || list.size() == 0) {
			return SightshareResult.build(400, "该用户信息不存在，请检查用户名/密码，是否存在和匹配");
		}

		// 进行密码比对
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return SightshareResult.build(400, "该用户信息不存在，请检查用户名/密码，是否存在和匹配");
		}

		// 生成一个Token
		String token = UUID.randomUUID().toString().substring(20);
		// 把用户ID写入
		token = token + "+" + user.getId().toString();

		// 把登陆成功的用户信息写入到REDIS中
		// 保存之前，把user对象内的密码清除，防止信息泄漏
		user.setPassword(null);
		try {
			jedisClient.set(USER_SESSION_REDIS_KEY + ":" + token, JsonUtils.objectToJson(user));
			jedisClient.expire(USER_SESSION_REDIS_KEY + ":" + token, USER_SESSION_REDIS_EXPIRE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 验证成功，添加Cookie的逻辑,cookie的有效期
		CookieUtils.setCookie(request, response, "SS_Token", token);

		// 返回Token
		return SightshareResult.ok(token);
	}

	/**
	 * 
	 * <p>
	 * Title: userLogout
	 * </p>
	 * <p>
	 * Description: 用户退出Service
	 * </p>
	 * 
	 * @param token
	 * @return
	 * @see cn.net.sight.share.sso.service.UserService#userLogout(java.lang.String)
	 */
	@Override
	public SightshareResult userLogout(HttpServletRequest request, HttpServletResponse response, String token) {
		try {
			// 从REDIS中获取用户信息
			String json = jedisClient.get(USER_SESSION_REDIS_KEY + ":" + token);
			// 如果用户信息为空。说明已经下线
			if (StringUtils.isBlank(json)) {
				return SightshareResult.build(400, "此Session已经过期，用户已下线");
			}
			// 如果用户信息存在，则删除对应的KEY
			jedisClient.del(USER_SESSION_REDIS_KEY + ":" + token);
			// 返回SightShareResult
			return SightshareResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			CookieUtils.deleteCookie(request, response, "SS_Token");
			return SightshareResult.ok();
		}

	}

}
