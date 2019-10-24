package com.zhong.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.domain.SysLog;
import com.zhong.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SysLogController {

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping("/findAllLog.zq")
    public ModelAndView findAllLog(Integer page, Integer size){
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = iSysLogService.findAllLog(page,size);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
