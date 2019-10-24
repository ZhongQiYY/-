package com.zhong.dao;

import com.zhong.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    /**
     * 根据id查询会员信息
     * @param memberId
     * @return
     */
    @Select("select * from member where id=#{memberId}")
    Member findById(String memberId);
}
