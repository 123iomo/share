package cn.net.sight.share.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.portal.pojo.CartItem;
import cn.net.sight.share.portal.pojo.Order;
import cn.net.sight.share.portal.service.CartService;
import cn.net.sight.share.portal.service.OrderService;
import cn.net.sight.share.utils.CookieUtils;

/**
 * 
 * <p>
 * Title: OrderController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午7:05:51
 * @version 1.0
 */
@Controller
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/order-cart")
	public String showOrdeCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "order-cart";
	}

	@RequestMapping("/order/create")
	public String createOrder(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 从Request中取用户信息
			TbUser user = (TbUser) request.getAttribute("user");
			// 在order对象中补全用户信息
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			order.setEndTime(new DateTime().plusHours(12).toDate());
			order.setCloseTime(new DateTime().plusDays(1).toDate());
			order.setShippingCode("AY9727");
			order.setShippingName("虚拟网盘");

			// 调用服务
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));

			CookieUtils.setCookie(request, response, "SS_Order_ID", orderId);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "创建订单出错。请稍后重试！");
			return "error/exception";
		}

	}

	@RequestMapping(value="/selfship.html", produces = "text/html;charset=UTF-8")
	public String showLink(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 从Request中取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		String orderId = CookieUtils.getCookieValue(request, "SS_Order_ID");
		//清除缓存信息
		CookieUtils.deleteCookie(request, response, "SS_Token");
		CookieUtils.deleteCookie(request, response, "SS_Order_ID");
		return "ship";
	}
}
