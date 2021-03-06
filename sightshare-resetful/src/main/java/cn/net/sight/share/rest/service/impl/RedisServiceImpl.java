package cn.net.sight.share.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.rest.dao.JedisClient;
import cn.net.sight.share.rest.service.RedisService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: RedisServiceImpl
 * </p>
 * <p>
 * Description: Redis同步缓存服务
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月24日下午10:52:18
 * @version 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public SightshareResult syncContent(Long contentCategoryId) {
		Long count = 0L;
		try {
			count = jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "");
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if (count.equals(0)) {
			return SightshareResult.build(500, "Clean the Redis is Failed.");
		} else {
			return SightshareResult.ok();
		}
	}

}
