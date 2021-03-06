package cn.net.sight.share.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.sight.share.search.mapper.SearchedItemMapper;
import cn.net.sight.share.search.pojo.SearchedItem;
import cn.net.sight.share.search.service.SearchItemService;
import cn.net.sight.share.utils.ExceptionUtil;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: SearchItemServiceImpl
 * </p>
 * <p>
 * Description: SOLR查询服务
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月29日下午12:48:09
 * @version 1.0
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SolrServer server;

	@Autowired
	private SearchedItemMapper itemMapper;

	@Override
	public SightshareResult importAllItems() {

		try {
			// 查询商品列表
			List<SearchedItem> itemList = itemMapper.getItemList();
			// 把商品信息写入索引库
			for (SearchedItem searchedItem : itemList) {
				// 创建一个SolrInputDocument对象
				SolrInputDocument inputDocument = new SolrInputDocument();
				inputDocument.setField("id", searchedItem.getId());
				inputDocument.setField("item_title", searchedItem.getTitle());
				inputDocument.setField("item_sell_point", searchedItem.getSell_point());
				inputDocument.setField("item_price", searchedItem.getPrice());
				inputDocument.setField("item_image", searchedItem.getImage());
				inputDocument.setField("item_category_name", searchedItem.getCategory_name());
				inputDocument.setField("item_desc", searchedItem.getItem_desc());

				// 写入索引库
				server.add(inputDocument);
			}
			server.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return SightshareResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return SightshareResult.ok();
	}

}
