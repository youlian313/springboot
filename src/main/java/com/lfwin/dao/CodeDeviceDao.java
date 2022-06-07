package com.lfwin.dao;

import com.lfwin.pojo.CodeDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CodeDeviceDao {
   // @Select("select id,uid,sid,apikey,devicename,type,sn,is_high_precision where apikey=#{apikey}")
    public CodeDevice getByApikey(String apikey);
}
