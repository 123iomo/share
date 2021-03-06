package cn.net.sight.share.service;

import java.util.List;

import cn.net.sight.share.pojo.EUTreeNode;
import cn.net.sight.share.utils.SightshareResult;

public interface ContentCategoryService {

	List<EUTreeNode> getContentCategoryList(Long parentId);
	SightshareResult createContentCategory(Long parentId, String categoryName);
	SightshareResult removeContentCategory(Long nodeId);
	SightshareResult updateContentCategory(Long nodeId, String categoryName);
}
