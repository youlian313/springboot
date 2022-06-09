package com.lfwin.dao;

import com.lfwin.pojo.UserCommer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCommerDao {
    UserCommer getByUid(String uid);
}
