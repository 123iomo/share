package cn.net.sight.share.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.pojo.TbItemLink;
import cn.net.sight.share.service.ItemService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 商品管理Controller
 * <p>
 * Title: ItemController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月11日下午4:19:29
 * @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	private Integer page = null;
	private Integer rows = null;

	@Autowired
	private ItemService itemService;

	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getTbItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.geTbItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getTbItemList(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
		EUDataGridResult tbItemList = itemService.getTbItemList(page, rows);
		return tbItemList;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	private SightshareResult insertTbItem(TbItem tbItem, String desc, String itemParams, String link, String password)
			throws Exception {
		SightshareResult result = null;
		try {
			result = itemService.createTbItem(tbItem, desc, itemParams,link, password);
			HttpClientUtil.doGet("http://localhost:8083/search/manager/importall");
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

	@RequestMapping(value = "/delete/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public SightshareResult deleteTbItem(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String ids) {
		SightshareResult result = null;
		try {
			long id = Long.parseLong(ids);
			result = itemService.deleteTbItemById(id);
			HttpClientUtil.doGet("http://localhost:8083/search/manager/importall");
			response.sendRedirect("http://localhost:8080/item/list?page=" + page + "&rows=" + rows);
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public SightshareResult updateTbItem(String _) {
		System.out.println("ids:" + _);
		return SightshareResult.ok();
	}

	@RequestMapping(value = "/instock/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public SightshareResult instockTbItem(@PathVariable String ids) {
		SightshareResult result = null;
		try {
			long id = Long.parseLong(ids);
			result = itemService.instockTbItem(id);
			HttpClientUtil.doGet("http://localhost:8083/search/manager/importall");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}

	@RequestMapping(value = "/reshelf/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public SightshareResult reshelfTbItem(@PathVariable String ids) {
		SightshareResult result = null;
		try {
			long id = Long.parseLong(ids);
			result = itemService.reshelfTbItem(id);
			HttpClientUtil.doGet("http://localhost:8083/search/manager/importall");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
}
