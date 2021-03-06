package cn.net.sight.share.service;

import java.util.List;

import cn.net.sight.share.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getTbItemCatList(Long parentId);
}
