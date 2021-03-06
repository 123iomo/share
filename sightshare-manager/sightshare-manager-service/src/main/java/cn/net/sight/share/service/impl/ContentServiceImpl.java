package cn.net.sight.share.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.sight.share.mapper.TbContentMapper;
import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.pojo.TbContentExample;
import cn.net.sight.share.pojo.TbContentExample.Criteria;
import cn.net.sight.share.service.ContentService;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ContentServiceImpl
 * </p>
 * <p>
 * Description: Content服务层
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月17日下午5:42:57
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public EUDataGridResult getAllContentList(Long categoryId, Integer pageNum, Integer PageSize) {
		// 查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 获得结果集
		List<TbContent> list = contentMapper.selectByExample(example);
		// 设置分页
		PageHelper.startPage(pageNum, PageSize);
		EUDataGridResult dataGridResult = new EUDataGridResult();
		dataGridResult.setRows(list);
		PageInfo<TbContent> PageInfo = new PageInfo<>(list);
		dataGridResult.setTotal(PageInfo.getTotal());
		return dataGridResult;
	}

	@Override
	public SightshareResult createContent(TbContent tbContent) {
		// 补全POJO
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		contentMapper.insert(tbContent);
		// 添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + tbContent.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SightshareResult.ok();
	}

}
