package cn.net.sight.share.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbItemDescMapper;
import cn.net.sight.share.mapper.TbItemMapper;
import cn.net.sight.share.mapper.TbItemParamItemMapper;
import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.pojo.TbItemDesc;
import cn.net.sight.share.pojo.TbItemParamItem;
import cn.net.sight.share.pojo.TbItemParamItemExample.Criteria;
import cn.net.sight.share.pojo.TbItemParamItemExample;
import cn.net.sight.share.rest.dao.JedisClient;
import cn.net.sight.share.rest.service.ItemService;
import cn.net.sight.share.utils.JsonUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ItemServiceImpl
 * </p>
 * <p>
 * Description: 商品详情Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月2日下午9:26:10
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_INFO_REDIS_KEY}")
	private String ITEM_INFO_REDIS_KEY;

	@Value("${ITEM_INFO_REDIS_EXPIRE_TIME}")
	private Integer ITEM_INFO_REDIS_EXPIRE_TIME;

	/**
	 * 
	 * <p>
	 * Title: getItemBaseInfo
	 * </p>
	 * <p>
	 * Description: 根据商品ID查询商品信息
	 * </p>
	 * 
	 * @param tbItemId
	 * @return
	 * @see cn.net.sight.share.rest.service.ItemService#getItemBaseInfo(java.lang.Long)
	 */
	@Override
	public SightshareResult getItemBaseInfo(Long tbItemId) {
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品ID--》信息
			String item_json = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":base");
			if (!StringUtils.isBlank(item_json)) {
				TbItem item = JsonUtils.jsonToPojo(item_json, TbItem.class);
				return SightshareResult.ok(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据商品ID查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(tbItemId);

		try {
			// 把商品信息写入到缓存中
			jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":base", JsonUtils.objectToJson(item));
			// 设置KEY的有效期，过期则删除
			jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":base", ITEM_INFO_REDIS_EXPIRE_TIME);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SightshareResult.ok(item);
	}

	@Override
	public SightshareResult getItemDescInfo(Long tbItemId) {
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品ID--》信息
			String itemDesc_json = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":desc");
			if (!StringUtils.isBlank(itemDesc_json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(itemDesc_json, TbItemDesc.class);
				return SightshareResult.ok(itemDesc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(tbItemId);

		try {
			// 把商品信息写入到缓存中
			jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":desc", JsonUtils.objectToJson(itemDesc));
			// 设置KEY的有效期，过期则删除
			jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":desc", ITEM_INFO_REDIS_EXPIRE_TIME);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SightshareResult.ok(itemDesc);
	}

	@Override
	public SightshareResult getItemParamInfo(Long tbItemId) {
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品ID--》信息
			String itemParam_json = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":param");
			if (!StringUtils.isBlank(itemParam_json)) {

				TbItemParamItem itemParam = JsonUtils.jsonToPojo(itemParam_json, TbItemParamItem.class);
				return SightshareResult.ok(itemParam);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据商品id查询规格参数
		// 设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(tbItemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem paramItem = list.get(0);
			try {
				// 把商品信息写入缓存
				jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":param", JsonUtils.objectToJson(paramItem));
				// 设置key的有效期
				jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + tbItemId + ":param", ITEM_INFO_REDIS_EXPIRE_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SightshareResult.ok(paramItem);
		}
		return SightshareResult.build(400, "无此商品规格");

	}

	@Override
	public TbItem geTbItemById(Long tbItemId) {

		return null;
	}

}
