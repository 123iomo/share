package cn.net.sight.share.mapper;

import cn.net.sight.share.pojo.TbItemLink;
import cn.net.sight.share.pojo.TbItemLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemLinkMapper {
    int countByExample(TbItemLinkExample example);

    int deleteByExample(TbItemLinkExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemLink record);

    int insertSelective(TbItemLink record);

    List<TbItemLink> selectByExample(TbItemLinkExample example);

    TbItemLink selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemLink record, @Param("example") TbItemLinkExample example);

    int updateByExample(@Param("record") TbItemLink record, @Param("example") TbItemLinkExample example);

    int updateByPrimaryKeySelective(TbItemLink record);

    int updateByPrimaryKey(TbItemLink record);
}