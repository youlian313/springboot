package com.lfwin.dao;

import com.lfwin.pojo.CommerShop;
import com.lfwin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CommerShopDao {
    @Select("select shopname,lfsignkey,store_id,alipay_store_id,sharing_rule_id,profit_sharing,profit_proportion,auto_sharing,sharing_type,channel from pos_crm_commer_shop where id=#{id}")
    CommerShop getById(String id);
}
