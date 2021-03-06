package cn.net.sight.share.service;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.utils.SightshareResult;

public interface ItemService {

	TbItem geTbItemById(Long itemId);
	EUDataGridResult getTbItemList(Integer pageNum, Integer pageSize);
	SightshareResult createTbItem(TbItem tbItem, String tbItemDesc_Str, String tbItemParams,String link,String password) throws Exception;
	SightshareResult deleteTbItemById(Long itemId);
	SightshareResult instockTbItem(Long id);
	SightshareResult reshelfTbItem(Long id);
}
