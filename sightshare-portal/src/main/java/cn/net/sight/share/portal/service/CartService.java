package cn.net.sight.share.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.net.sight.share.portal.pojo.CartItem;
import cn.net.sight.share.utils.SightshareResult;

public interface CartService {

	SightshareResult addItemIntoCart(Long itemId, Integer itemNum, HttpServletRequest request,
			HttpServletResponse response);

	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	SightshareResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
