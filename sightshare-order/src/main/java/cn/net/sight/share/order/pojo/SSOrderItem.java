package cn.net.sight.share.order.pojo;

import java.util.List;

import cn.net.sight.share.pojo.TbOrder;
import cn.net.sight.share.pojo.TbOrderItem;
import cn.net.sight.share.pojo.TbOrderShopping;

/**
 * 
 * <p>Title: SSOrderItem</p>
 * <p>Description: 商品订单项POJO</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年2月6日下午5:51:21
 * @version 1.0
 */
public class SSOrderItem extends TbOrder {

	private List<TbOrderItem> orderItems;
	private TbOrderShopping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShopping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShopping orderShipping) {
		this.orderShipping = orderShipping;
	}

	

}
