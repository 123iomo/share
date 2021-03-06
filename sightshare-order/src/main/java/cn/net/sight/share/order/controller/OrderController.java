package cn.net.sight.share.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.sight.share.order.pojo.SSOrderItem;
import cn.net.sight.share.order.service.OrderService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

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
 * @date 2017年2月6日下午5:53:35
 * @version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public SightshareResult createOrder(@RequestBody SSOrderItem order) {
		SightshareResult result;
		try {
			result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
