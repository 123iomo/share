package cn.net.sight.share.portal.pojo;

import java.util.List;

import cn.net.sight.share.pojo.TbOrder;
import cn.net.sight.share.pojo.TbOrderItem;
import cn.net.sight.share.pojo.TbOrderShopping;

public class Order extends TbOrder {

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
