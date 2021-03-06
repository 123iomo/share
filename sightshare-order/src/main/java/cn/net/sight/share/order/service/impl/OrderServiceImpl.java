package cn.net.sight.share.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbOrderItemMapper;
import cn.net.sight.share.mapper.TbOrderMapper;
import cn.net.sight.share.mapper.TbOrderShoppingMapper;
import cn.net.sight.share.order.dao.JedisClient;
import cn.net.sight.share.order.service.OrderService;
import cn.net.sight.share.pojo.TbOrder;
import cn.net.sight.share.pojo.TbOrderItem;
import cn.net.sight.share.pojo.TbOrderShopping;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.IDUtils;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: OrderServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午4:35:04
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShoppingMapper shoppingMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;

	@Value("${ORDER_INIT_ID_KEY}")
	private String ORDER_INIT_ID_KEY;

	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	private Long OrderId = null;
	private Long OrderItemId = null;

	@Override
	public SightshareResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShopping orderShopping) {
		this.OrderId = null;
		// 向订单表中插入记录
		// 正常逻辑，从REDIS中获得订单号

		try {
			String string = jedisClient.get(ORDER_GEN_KEY);
			if (StringUtils.isBlank(string)) {
				jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID_KEY);
			}
			this.OrderId = jedisClient.incr(ORDER_GEN_KEY);
		} catch (Exception e) {
			//如果REDIS中发生错误，则使用ID生成策略生成
			this.OrderId = IDUtils.genOrderId();
			System.out.println(ExceptionUtil.getStackTrace(e));
		}
		// 补全pojo的属性
		order.setOrderId(this.OrderId + "");
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 0：未评价 1：已评价
		order.setBuyerRate(0);
		// 向订单表插入数据
		orderMapper.insert(order);
		// 插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			// 补全订单明细
			// 取订单明细id
			this.OrderItemId = null;
			try {
				OrderItemId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			} catch (Exception e) {
				OrderItemId = IDUtils.genOrderItemId();
				System.out.println(ExceptionUtil.getStackTrace(e));
			}
			tbOrderItem.setId(this.OrderItemId + "");
			tbOrderItem.setOrderId(this.OrderId + "");
			// 向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		// 插入物流表
		// 补全物流表的属性
		orderShopping.setOrderId(this.OrderId.toString());
		orderShopping.setCreated(new Date());
		orderShopping.setUpdated(new Date());
		shoppingMapper.insert(orderShopping);

		return SightshareResult.ok(this.OrderId);
	}

}
