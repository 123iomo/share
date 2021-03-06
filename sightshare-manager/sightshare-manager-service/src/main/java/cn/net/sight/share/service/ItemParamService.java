package cn.net.sight.share.service;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbItemParam;
import cn.net.sight.share.utils.SightshareResult;

public interface ItemParamService {

	SightshareResult geTbItemParamByTbItemCatId(Long itemCatId);
	SightshareResult insertTbItemParam(TbItemParam tbItemParam);
	EUDataGridResult getTbItemParamList(Integer pageNum, Integer pageSize);
	SightshareResult deleteTbItemParam(Long id);
}
