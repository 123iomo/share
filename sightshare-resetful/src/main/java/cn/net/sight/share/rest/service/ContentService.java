package cn.net.sight.share.rest.service;

import java.util.List;

import cn.net.sight.share.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentListById(Long contentCategoryId);
}
