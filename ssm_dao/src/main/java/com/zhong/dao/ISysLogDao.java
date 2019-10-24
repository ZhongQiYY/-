package com.zhong.dao;

import com.zhong.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveLog(SysLog sysLog);

    @Select("select * from sysLog")
    List<SysLog> findAllLog();
}
