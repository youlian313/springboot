package com.lfwin.dao;

import com.lfwin.pojo.CodeDevice;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CodeDeviceDao {
   CodeDevice getByApikey(String apikey);
}
