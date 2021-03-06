package cn.net.sight.share.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.net.sight.share.portal.pojo.Order;
import cn.net.sight.share.portal.service.OrderService;
import cn.net.sight.share.utils.HttpClientUtil;
import cn.net.sight.share.utils.JsonUtils;
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
 * @date 2017年2月7日上午8:41:32
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;

	@Value("${ORDER_SUBMIT_URL}")
	private String ORDER_SUBMIT_URL;

	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前，补全用户信息
		//从cookie中读取SS_Token的内容，根据token调用sso服务
		
		// 调用sightshare-order服务提交订单
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_SUBMIT_URL, JsonUtils.objectToJson(order));
		// 把返回值转换成SightShareResult
		SightshareResult result = SightshareResult.format(json);
		if(result.getStatus() == 200){
			Object orderId = result.getData();
			return orderId.toString();
		}
		return "";
	}

}
