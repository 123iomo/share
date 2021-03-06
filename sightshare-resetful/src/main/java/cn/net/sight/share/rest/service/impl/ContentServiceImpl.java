package cn.net.sight.share.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbContentMapper;
import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.pojo.TbContentExample;
import cn.net.sight.share.pojo.TbContentExample.Criteria;
import cn.net.sight.share.rest.dao.JedisClient;
import cn.net.sight.share.rest.service.ContentService;
import cn.net.sight.share.utils.JsonUtils;

/**
 * 
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: REST服务层</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月17日下午6:56:13
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentListById(Long contentCategoryId) {
		//从缓存中取内容
		try {
			
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "");
			if(!StringUtils.isBlank(result)){
				//把字符串转换成List
				List<TbContent> redisResultList = JsonUtils.jsonToList(result, TbContent.class);
				return redisResultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		List<TbContent> list = contentMapper.selectByExample(example);

		//向缓存中添加内容
		try {
			//把List转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			String hkey = INDEX_CONTENT_REDIS_KEY;
			String key = contentCategoryId + "";
			jedisClient.hset(hkey, key, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
