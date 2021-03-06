package cn.net.sight.share.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.portal.pojo.CartItem;
import cn.net.sight.share.portal.service.CartService;
import cn.net.sight.share.utils.CookieUtils;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.JsonUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: CartServiceImpl
 * </p>
 * <p>
 * Description: 购物车实现逻辑层
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午1:10:54
 * @version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITEM_INFO_BASE_URL}")
	private String ITEM_INFO_BASE_URL;

	/**
	 * 
	 * <p>
	 * Title: addItemIntoCart
	 * </p>
	 * <p>
	 * Description: 向购物车中增加商品
	 * </p>
	 * 
	 * @param itemId
	 * @param itemNum
	 * @return
	 * @see cn.net.sight.share.portal.service.CartService#addItemIntoCart(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public SightshareResult addItemIntoCart(Long itemId, Integer itemNum, HttpServletRequest request,
			HttpServletResponse response) {
		// 取商品信息
		CartItem cartItem = null;
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			// 如果存在此商品
			if (cItem.getId().equals(itemId)) {
				// 增加商品数量
				cItem.setNumber(cItem.getNumber() + itemNum);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_BASE_URL + itemId);
			// 把json转换成java对象
			SightshareResult taotaoResult = SightshareResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNumber(itemNum);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "SS_Cart", JsonUtils.objectToJson(itemList), true);

		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: getCartItemList
	 * </p>
	 * <p>
	 * Description: 从cookie中取商品列表
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从Cookie中获取商品列表
		String cookieValue = CookieUtils.getCookieValue(request, "SS_Cart", true);
		if (StringUtils.isBlank(cookieValue)) {
			return new ArrayList<CartItem>();
		}
		// 把Cookie转换成POJO的列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cookieValue, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartItemList(request);
		return cartItemList;
	}

	/**
	 * 
	 * <p>
	 * Title: deleteCartItem
	 * </p>
	 * <p>
	 * Description: 删除购物车商品
	 * </p>
	 * 
	 * @param itemId
	 * @return
	 * @see com.taotao.portal.service.CartService#deleteCartItem(Long)
	 */
	@Override
	public SightshareResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 从列表中找到此商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId().equals(itemId)) {
				itemList.remove(cartItem);
				break;
			}

		}
		
		// 把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "SS_Cart", JsonUtils.objectToJson(itemList), true);

		return SightshareResult.ok();
	}

}	
