package cn.net.sight.share.portal.service;

import cn.net.sight.share.portal.pojo.SearchedItemInfo;

public interface ItemService {

	SearchedItemInfo geTbItemById(Long itemId);
	String getTbItemDescByTbItemId(Long itemId);
	String getTbItemParamByTbItemId(Long itemId);
}
