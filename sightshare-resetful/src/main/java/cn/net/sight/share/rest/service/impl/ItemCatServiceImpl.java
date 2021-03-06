package cn.net.sight.share.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbItemCatMapper;
import cn.net.sight.share.pojo.TbItemCat;
import cn.net.sight.share.pojo.TbItemCatExample;
import cn.net.sight.share.pojo.TbItemCatExample.Criteria;
import cn.net.sight.share.rest.dao.JedisClient;
import cn.net.sight.share.rest.pojo.ItemCatNode;
import cn.net.sight.share.rest.pojo.ItemCatResult;
import cn.net.sight.share.rest.service.ItemCatService;

/**
 * 
 * <p>
 * Title: ItemCatServiceImpl
 * </p>
 * <p>
 * Description: 首页商品分类展示服务层
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月15日下午5:23:58
 * @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_CATEGORY_REDIS_KEY}")
	private String ITEM_CATEGORY_REDIS_KEY;

	@Override
	public ItemCatResult getItemCatList() {
		ItemCatResult catResult = new ItemCatResult();
		// 查询分类列表
		catResult.setData(getItemCatList(0));
		return catResult;
	}

	private List<?> getItemCatList(long parentId) {
		// 从缓存中取内容
		/*try {
			String result = jedisClient.hget(ITEM_CATEGORY_REDIS_KEY, parentId + "");
			if (!StringUtils.isBlank(result)) {
				// 把字符串转换成List
				List<TbItemCat> redisResultList = JsonUtils.jsonToList(result, TbItemCat.class);
				return redisResultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 创建结果List
		List resultList = new ArrayList<>();
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		
		// 向缓存中添加内容
		/*try {
			// 把List转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			String hkey = ITEM_CATEGORY_REDIS_KEY;
			String key = parentId + "";
			jedisClient.hset(hkey, key, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		// 向resultList中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				ItemCatNode node = new ItemCatNode();
				if (parentId == 0) {
					node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");

				} else {
					node.setName(tbItemCat.getName());
				}
				node.setUrl("/products/" + tbItemCat.getId() + ".html");
				node.setItem(getItemCatList(tbItemCat.getId()));
				resultList.add(node);
				count++;

				// 首页资源分类显示限制，只允许14大类
				if (parentId == 0 && count >= 14) {
					break;
				}
				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}

		return resultList;
	}

}
