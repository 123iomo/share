package cn.net.sight.share.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.sight.share.mapper.TbItemDescMapper;
import cn.net.sight.share.mapper.TbItemLinkMapper;
import cn.net.sight.share.mapper.TbItemMapper;
import cn.net.sight.share.mapper.TbItemParamItemMapper;
import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.pojo.TbItemDesc;
import cn.net.sight.share.pojo.TbItemExample;
import cn.net.sight.share.pojo.TbItemLink;
import cn.net.sight.share.pojo.TbItemParamItem;
import cn.net.sight.share.service.ItemService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.IDUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 商品管理Service
 * <p>
 * Title: ItemServiceImpl
 * </p>
 * <p>
 * Description: the implement script of the ItemService
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月11日下午4:15:09
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
	private TbItemLinkMapper linkMapper;

	/**
	 * 
	 * <p>
	 * Title: geTbItemById
	 * </p>
	 * <p>
	 * Description: get the TbItem by TbItem.id
	 * </p>
	 * 
	 * @param itemId
	 * @return TbItem
	 * @see cn.net.sight.share.service.ItemService#geTbItemById(java.lang.Long)
	 */
	@Override
	public TbItem geTbItemById(Long itemId) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	/**
	 * 
	 * <p>
	 * Title: getTbItemList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return EUDataGridResult
	 * @see cn.net.sight.share.service.ItemService#getTbItemList(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public EUDataGridResult getTbItemList(Integer pageNum, Integer pageSize) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		// 获得商品列表
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult dataGridResult = new EUDataGridResult();
		dataGridResult.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	/**
	 * 
	 * <p>
	 * Title: createTbItem
	 * </p>
	 * <p>
	 * Description: Item补全
	 * </p>
	 * 
	 * @param tbItem,
	 *            tbItemDesc_Str
	 * @return SightshareResult
	 * @throws Exception
	 * @see cn.net.sight.share.service.ItemService#createTbItem(cn.net.sight.share.pojo.TbItem)
	 */
	@Override
	public SightshareResult createTbItem(TbItem tbItem, String tbItemDesc_Str, String tbItemParams, String link,
			String password) throws Exception {

		// 生成商品ID
		Long tbItemId = IDUtils.genItemId();
		String tbItemTitle = tbItem.getTitle();
		tbItem.setId(tbItemId);
		// 商品状态 1-正常 2-下架 3-删除
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());

		// 插入操作
		itemMapper.insert(tbItem);
		// 添加商品描述
		SightshareResult result_TbItemDesc = insrtTbItemDesc(tbItemId, tbItemDesc_Str);
		// 添加规格参数
		SightshareResult result_TbItemParamItem = insertTbItemParamItem(tbItemId, tbItemParams);
		// 添加商品链接
		SightshareResult result_TbItemLink = insertTbItemLink(tbItemId, tbItemTitle, link, password);

		if (result_TbItemDesc.getStatus() != 200 || result_TbItemParamItem.getStatus() != 200
				|| result_TbItemLink.getStatus() != 200) {
			throw new Exception();
		}
		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: insertTbItemLink
	 * </p>
	 * <p>
	 * Description: 添加商品网盘链接
	 * </p>
	 * 
	 * @param tbItemId
	 * @param tbItemTitle
	 * @param link
	 * @param password
	 * @return
	 */
	private SightshareResult insertTbItemLink(Long tbItemId, String tbItemTitle, String link, String password) {
		TbItemLink itemLink = new TbItemLink();
		itemLink.setItemId(tbItemId);
		itemLink.setItemTitle(tbItemTitle);
		itemLink.setItemLink(link);
		itemLink.setLinkPassword(password);
		linkMapper.insert(itemLink);
		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: insrtTbItemDesc
	 * </p>
	 * <p>
	 * Description: 添加商品描述
	 * </p>
	 * 
	 * @param tbItemId,
	 *            tbItemDesc_Str
	 * @return SightshareResult
	 */
	private SightshareResult insrtTbItemDesc(Long tbItemId, String tbItemDesc_Str) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItemId);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setItemDesc(tbItemDesc_Str);
		tbItemDesc.setUpdated(new Date());
		itemDescMapper.insert(tbItemDesc);
		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: insertTbItemParam
	 * </p>
	 * <p>
	 * Description: 创建一个TbItemParamItem对象
	 * </p>
	 * 
	 * @param tbItemId
	 * @param tbItemParams
	 * @return SightshareResult
	 * @throws Exception
	 */
	private SightshareResult insertTbItemParamItem(Long tbItemId, String tbItemParams) throws Exception {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setItemId(tbItemId);
		tbItemParamItem.setParamData(tbItemParams);
		tbItemParamItem.setUpdated(new Date());
		// 向tb_item_param_item表中插入数据
		int count = itemParamItemMapper.insert(tbItemParamItem);
		if (count > 0) {
			return SightshareResult.ok();
		} else {
			throw new Exception();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: deleteTbItemById
	 * </p>
	 * <p>
	 * Description: 删除商品资源
	 * </p>
	 * 
	 * @param itemId
	 * @return
	 * @see cn.net.sight.share.service.ItemService#deleteTbItemById(java.lang.Long)
	 */
	@Override
	public SightshareResult deleteTbItemById(Long itemId) {
		SightshareResult result = null;
		try {
			int count_param = itemParamItemMapper.deleteByPrimaryKey(itemId);
			int count_base = itemMapper.deleteByPrimaryKey(itemId);
			int count_desc = itemDescMapper.deleteByPrimaryKey(itemId);

			if (count_base == 1 && count_desc == 1 && count_param == 1) {
				result = SightshareResult.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 
	 * <p>
	 * Title: instockTbItem
	 * </p>
	 * <p>
	 * Description: 商品下架处理
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.net.sight.share.service.ItemService#instockTbItem(java.lang.Long)
	 */
	@Override
	public SightshareResult instockTbItem(Long id) {
		SightshareResult result = null;
		try {
			TbItem record = itemMapper.selectByPrimaryKey(id);
			record.setStatus((byte) 2);
			int count = itemMapper.updateByPrimaryKey(record);
			if (count == 1) {
				result = SightshareResult.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 
	 * <p>
	 * Title: reshelfTbItem
	 * </p>
	 * <p>
	 * Description: 商品上架处理
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.net.sight.share.service.ItemService#reshelfTbItem(java.lang.Long)
	 */
	@Override
	public SightshareResult reshelfTbItem(Long id) {
		SightshareResult result = null;
		try {
			TbItem record = itemMapper.selectByPrimaryKey(id);
			record.setStatus((byte) 1);
			int count = itemMapper.updateByPrimaryKey(record);
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
