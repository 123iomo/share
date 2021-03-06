package cn.net.sight.share.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbItemLinkMapper;
import cn.net.sight.share.mapper.TbOrderItemMapper;
import cn.net.sight.share.mapper.TbOrderMapper;
import cn.net.sight.share.order.service.ShipService;
import cn.net.sight.share.pojo.TbItemLink;
import cn.net.sight.share.pojo.TbOrder;
import cn.net.sight.share.pojo.TbOrderExample;
import cn.net.sight.share.pojo.TbOrderItem;
import cn.net.sight.share.pojo.TbOrderItemExample;
import cn.net.sight.share.pojo.TbOrderExample.Criteria;

/**
 * 
 * <p>
 * Title: ShipServiceImpl
 * </p>
 * <p>
 * Description: 自助提货Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月16日下午3:16:13
 * @version 1.0
 */
@Service
public class ShipServiceImpl implements ShipService {

	@Autowired
	private TbOrderMapper orderMapper;

	@Autowired
	private TbItemLinkMapper linkMapper;

	@Autowired
	private TbOrderItemMapper OrderItemMapper;

	@Override
	public String getItemLink(String OrderId, String UserNickName) {
		List<TbItemLink> resultList = new ArrayList<>();

		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andBuyerNickEqualTo(UserNickName);
		criteria.andOrderIdEqualTo(OrderId);

		// 先检查Tb_Order表中是否存在用户对应的购买记录
		List<TbOrder> list = orderMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return "";
		}

		// 若存在，则去Tb_Order_item表中，去获取用户购买的商品列表
		TbOrderItemExample itemExample = new TbOrderItemExample();
		cn.net.sight.share.pojo.TbOrderItemExample.Criteria itemCriteria = itemExample.createCriteria();
		itemCriteria.andOrderIdEqualTo(OrderId);

		// 通过OrderId,得到购买的商品ID列表,一对多关系
		List<TbOrderItem> itemList = OrderItemMapper.selectByExample(itemExample);
		if (itemList == null || itemList.size() == 0) {
			return "";
		}

		for (TbOrderItem tbOrderItem : itemList) {
			if (tbOrderItem != null) {
				TbItemLink itemLink = linkMapper.selectByPrimaryKey(Long.parseLong(tbOrderItem.getItemId()));
				resultList.add(itemLink);
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (TbItemLink item : resultList) {
			sb.append("        <tr>\n");
			sb.append("            <td class=\"tdTitle\" colspan=\"2\">" + item.getItemTitle() + "</td>\n");
			sb.append("            <td class=\"tdLink\" colspan=\"2\"><a href=\"" + item.getItemLink() + "\"/>"
					+ item.getItemLink() + "</td>\n");
			sb.append("            <td class=\"tdPwd\" colspan=\"1\">" + item.getLinkPassword() + "</td>\n");
			sb.append("        </tr>\n");
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();

	}

}
