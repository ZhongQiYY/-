package com.zhong.service;

import com.zhong.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void saveLog(SysLog sysLog);

    List<SysLog> findAllLog(int page, int size);
}
