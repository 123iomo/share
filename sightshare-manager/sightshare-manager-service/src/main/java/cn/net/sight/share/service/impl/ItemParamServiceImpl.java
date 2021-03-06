package cn.net.sight.share.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.sight.share.mapper.TbItemCatMapper;
import cn.net.sight.share.mapper.TbItemParamMapper;
import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItemCat;
import cn.net.sight.share.pojo.TbItemParam;
import cn.net.sight.share.pojo.TbItemParamAdvanced;
import cn.net.sight.share.pojo.TbItemParamExample;
import cn.net.sight.share.pojo.TbItemParamExample.Criteria;
import cn.net.sight.share.service.ItemParamService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ItemParamServiceImpl
 * </p>
 * <p>
 * Description: 商品规格参数模板
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月13日下午2:33:58
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 
	 * <p>
	 * Title: geTbItemParamByTbItemCatId
	 * </p>
	 * <p>
	 * Description: 根据TbItemCatId获得TbItemParam对象
	 * </p>
	 * 
	 * @param itemCatId
	 * @return SightshareResult
	 * @see cn.net.sight.share.service.ItemParamService#geTbItemParamByTbItemCatId(java.lang.Long)
	 */
	@Override
	public SightshareResult geTbItemParamByTbItemCatId(Long itemCatId) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return SightshareResult.ok(list.get(0));
		}
		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: insertTbItemParam
	 * </p>
	 * <p>
	 * Description: TbItemParam对象插入操作
	 * </p>
	 * 
	 * @param tbItemParam
	 * @return SightshareResult
	 * @see cn.net.sight.share.service.ItemParamService#insertTbItemParam(cn.net.sight.share.pojo.TbItemParam)
	 */
	@Override
	public SightshareResult insertTbItemParam(TbItemParam tbItemParam) {
		// 补全tbItemParam对象
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		// 完成插入操作
		int count = itemParamMapper.insert(tbItemParam);
		if (count > 0) {
			return SightshareResult.ok();
		} else {
			return null;
		}
	}

	@Override
	public EUDataGridResult getTbItemParamList(Integer pageNum, Integer pageSize) {
		// 查询商品列表
		TbItemParamExample example = new TbItemParamExample();
		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		// 获得商品列表
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		List<TbItemParamAdvanced> resultList = new ArrayList<>();
		for (TbItemParam tbItemParam : list) {
			TbItemParamAdvanced tbItemParamAdvanced = new TbItemParamAdvanced();
			tbItemParamAdvanced.setCreated(tbItemParam.getCreated());
			tbItemParamAdvanced.setId(tbItemParam.getId());
			tbItemParamAdvanced.setItemCatId(tbItemParam.getItemCatId());

			TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(tbItemParam.getItemCatId());
			String itemCatName = itemCat.getName();
			tbItemParamAdvanced.setItemCatName(itemCatName);
			tbItemParamAdvanced.setParamData(tbItemParam.getParamData());
			tbItemParamAdvanced.setUpdated(tbItemParam.getUpdated());
			resultList.add(tbItemParamAdvanced);
		}
		// 创建一个返回值对象
		EUDataGridResult dataGridResult = new EUDataGridResult();
		dataGridResult.setRows(resultList);
		PageInfo<TbItemParamAdvanced> pageInfo = new PageInfo<>(resultList);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;

	}

	/**
	 * 
	 * <p>
	 * Title: deleteTbItemParam
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.net.sight.share.service.ItemParamService#deleteTbItemParam(java.lang.Long)
	 */
	@Override
	public SightshareResult deleteTbItemParam(Long id) {
		SightshareResult result = null;
		try {
			int count = itemParamMapper.deleteByPrimaryKey(id);
			if (count == 1) {
				result = SightshareResult.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

}
