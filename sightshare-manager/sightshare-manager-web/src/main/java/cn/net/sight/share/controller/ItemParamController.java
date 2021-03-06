package cn.net.sight.share.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItemParam;
import cn.net.sight.share.service.ItemParamService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>Title: ItemParamController</p>
 * <p>Description: 商品规格参数模板管理</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月13日下午2:41:39
 * @version 1.0
 */
@Controller
@RequestMapping("/item/param/")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public SightshareResult getTbItemParamByTbItemCatId(@PathVariable Long itemCatId){
		SightshareResult result = itemParamService.geTbItemParamByTbItemCatId(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public SightshareResult insertTbItemParam(@PathVariable Long cid, String paramData){
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		SightshareResult result = itemParamService.insertTbItemParam(tbItemParam);
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getTbItemParamList(Integer page, Integer rows){
		EUDataGridResult tbItemParamList = itemParamService.getTbItemParamList(page, rows);
		return tbItemParamList;
	}
	
	@RequestMapping("/edit/{ids}")
	@ResponseBody
	public SightshareResult updateTbItemParam(@PathVariable Long ids){
		System.out.println("ids : " + ids);
		return SightshareResult.ok();
	}
	
	@RequestMapping("/delete/{ids}")
	@ResponseBody
	public SightshareResult deleteTbItemParam(@PathVariable String ids){
		SightshareResult result = null;
		try {
			Long id = Long.parseLong(ids);
			result = itemParamService.deleteTbItemParam(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
}
