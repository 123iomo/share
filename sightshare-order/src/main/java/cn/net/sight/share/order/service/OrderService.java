package cn.net.sight.share.order.service;

import java.util.List;

import cn.net.sight.share.pojo.TbOrder;
import cn.net.sight.share.pojo.TbOrderItem;
import cn.net.sight.share.pojo.TbOrderShopping;
import cn.net.sight.share.utils.SightshareResult;

public interface OrderService {

	SightshareResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShopping orderShopping);
}
