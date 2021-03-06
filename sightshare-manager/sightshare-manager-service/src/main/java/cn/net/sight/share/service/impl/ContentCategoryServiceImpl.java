package cn.net.sight.share.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.sight.share.mapper.TbContentCategoryMapper;
import cn.net.sight.share.pojo.EUTreeNode;
import cn.net.sight.share.pojo.TbContentCategory;
import cn.net.sight.share.pojo.TbContentCategoryExample;
import cn.net.sight.share.pojo.TbContentCategoryExample.Criteria;
import cn.net.sight.share.service.ContentCategoryService;
import cn.net.sight.share.utils.SightshareResult;

/**
 * 
 * <p>
 * Title: ContentCategoryServiceImpl
 * </p>
 * <p>
 * Description: CMS系统：获取内容列表
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月17日上午11:53:47
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getContentCategoryList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			if (tbContentCategory.getStatus().equals(1)) {
				// 创建一个节点对象
				EUTreeNode node = new EUTreeNode(tbContentCategory.getId(), tbContentCategory.getName(),
						tbContentCategory.getIsParent() ? "closed" : "open");

				resultList.add(node);
			}
		}

		return resultList;
	}

	@Override
	public SightshareResult createContentCategory(Long parentId, String categoryName) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setName(categoryName);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setUpdated(new Date());

		// 添加到数据库中
		contentCategoryMapper.insert(contentCategory);
		// 查看父节点的IsParent列是否为TRUE，如果不是则改成TRUE
		TbContentCategory parentContentCategoryNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentContentCategoryNode.getIsParent()) {
			parentContentCategoryNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentContentCategoryNode);
		}
		return SightshareResult.ok(contentCategory);
	}

	/**
	 * 
	 * <p>
	 * Title: removeContentCategory
	 * </p>
	 * <p>
	 * Description: 删除当前节点
	 * </p>
	 * 
	 * @param nodeId
	 * @return
	 * @see cn.net.sight.share.service.ContentCategoryService#removeContentCategory(java.lang.Long)
	 */
	@Override
	public SightshareResult removeContentCategory(Long nodeId) {
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(nodeId);
		// 获取父节点ID
		Long parentId = category.getParentId();
		// 删除当前节点,逻辑删除
		category.setStatus(2);
		contentCategoryMapper.updateByPrimaryKey(category);
		// 删除之后，判断父节点之下是否还有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 如果父节点之下没有子节点了，则将IsParent属性制为False
		if (list.size() == 0) {
			TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
			parent.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return SightshareResult.ok();
	}

	/**
	 * 
	 * <p>
	 * Title: updateContentCategory
	 * </p>
	 * <p>
	 * Description: 更新分类节点名称
	 * </p>
	 * 
	 * @param nodeId
	 * @param categoryName
	 * @return
	 * @see cn.net.sight.share.service.ContentCategoryService#updateContentCategory(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public SightshareResult updateContentCategory(Long nodeId, String categoryName) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(nodeId);
		contentCategory.setName(categoryName);
		contentCategory.setUpdated(new Date());
		int count = contentCategoryMapper.updateByPrimaryKey(contentCategory);
		if(count == 1){
			return SightshareResult.ok();
		}else {
			return null;
		}
	}

}
