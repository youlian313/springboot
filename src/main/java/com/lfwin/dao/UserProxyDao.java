package com.lfwin.dao;

import com.lfwin.pojo.UserProxy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserProxyDao {
    @Select("select uid,fid from pos_crm_user_proxy where uid=#{uid}")
    UserProxy getByUid(String uid);
}
