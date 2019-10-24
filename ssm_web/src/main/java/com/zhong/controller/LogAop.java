package com.zhong.controller;

import com.zhong.domain.SysLog;
import com.zhong.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService iSysLogService;

    private Date visitTime;//开始的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    @Pointcut("!execution(* com.zhong.controller.SysLogController.*(..)) && execution(* com.zhong.controller.*.*(..))")
    private void pt1(){}

    //前置通知   主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("pt1()")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();//获取到开始访问的时间
        clazz = joinPoint.getTarget().getClass();//获取到当前访问的类
        String methodName = joinPoint.getSignature().getName();//获取到访问的方法的名称
        Object[] args = joinPoint.getArgs();//获取访问的方法的参数

        if(args==null||args.length==0){
            method = clazz.getMethod(methodName); //若访问的方法没有参数，获取无参数的方法
        }else {
            Class[] classeArgs = new Class[args.length];
            for(int i = 0; i<args.length; i++){
                classeArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classeArgs);//若访问的方法有参数，获取有参数的方法
        }
    }

    //后置通知
    @AfterReturning("pt1()")
    public void doAfter(){
        long time = new Date().getTime()-visitTime.getTime(); //获取访问时长

        String[] classValue;
        String[] methodValue;
        String url="";
        //获取url
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            //1.获取类上的@RequestMapping注解的value值
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                classValue = classAnnotation.value();
                url = classValue[0];
            }

            //2.获取方法上的@RequestMapping注解的value值
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if(methodAnnotation!=null){
                methodValue = methodAnnotation.value();
                url=url+methodValue[0];
            }
        }
        //获取访问的ip地址
        String ip = request.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
//        也可以通过request.getSession中获取当前操作用户
//        request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

//        最后将日志相关信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setVisitTime(visitTime);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());

        //调用service方法将日志信息存入数据库
        iSysLogService.saveLog(sysLog);
    }
}
















