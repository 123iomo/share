package cn.net.sight.share.service;

import cn.net.sight.share.pojo.EUDataGridResult;
import cn.net.sight.share.pojo.TbContent;
import cn.net.sight.share.utils.SightshareResult;

public interface ContentService {

	EUDataGridResult getAllContentList(Long categoryId, Integer pageNum, Integer PageSize);
	SightshareResult createContent(TbContent tbContent);
}
