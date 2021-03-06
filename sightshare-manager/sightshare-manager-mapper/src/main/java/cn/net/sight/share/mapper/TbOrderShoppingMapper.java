package cn.net.sight.share.mapper;

import cn.net.sight.share.pojo.TbOrderShopping;
import cn.net.sight.share.pojo.TbOrderShoppingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderShoppingMapper {
    int countByExample(TbOrderShoppingExample example);

    int deleteByExample(TbOrderShoppingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShopping record);

    int insertSelective(TbOrderShopping record);

    List<TbOrderShopping> selectByExample(TbOrderShoppingExample example);

    TbOrderShopping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrderShopping record, @Param("example") TbOrderShoppingExample example);

    int updateByExample(@Param("record") TbOrderShopping record, @Param("example") TbOrderShoppingExample example);

    int updateByPrimaryKeySelective(TbOrderShopping record);

    int updateByPrimaryKey(TbOrderShopping record);
}