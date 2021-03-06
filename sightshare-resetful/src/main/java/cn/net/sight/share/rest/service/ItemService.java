package cn.net.sight.share.rest.service;

import cn.net.sight.share.pojo.TbItem;
import cn.net.sight.share.utils.SightshareResult;

public interface ItemService {

	SightshareResult getItemBaseInfo(Long tbItemId);
	SightshareResult getItemDescInfo(Long tbItemId);
	SightshareResult getItemParamInfo(Long tbItemId);
	TbItem geTbItemById(Long tbItemId);
}
