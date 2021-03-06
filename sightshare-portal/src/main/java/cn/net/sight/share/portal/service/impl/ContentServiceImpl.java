package cn.net.sight.share.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.portal.service.ContentService;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.JsonUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ContentServiceImpl
 * </p>
 * <p>
 * Description: 调用服务层服务，查询内容列表
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月19日上午11:34:04
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@SuppressWarnings("unchecked")
	@Override
	public String getContentList() {
		// 调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		// 把字符串转换成TaotaoResult
		try {
			SightshareResult SSResult = SightshareResult.formatToList(result, TbContent.class);
			// 取内容列表
			List<TbContent> list = (List<TbContent>) SSResult.getData();
			@SuppressWarnings("rawtypes")
			List<Map> resultList = new ArrayList<>();
			// 创建一个jsp页码要求的pojo列表
			for (TbContent tbContent : list) {
				@SuppressWarnings("rawtypes")
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
