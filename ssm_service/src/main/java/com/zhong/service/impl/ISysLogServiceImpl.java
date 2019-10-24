package com.zhong.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.dao.ISysLogDao;
import com.zhong.domain.SysLog;
import com.zhong.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        iSysLogDao.saveLog(sysLog);
    }

    @Override
    public List<SysLog> findAllLog(int page, int size) {
        PageHelper.startPage(page,size);
        return iSysLogDao.findAllLog();
    }
}
