package com.gz.utils.aspect;


import com.gz.utils.annotation.LogExecution;
import com.gz.utils.model.OperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


/**
 * 操作日志记录处理
 *
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    //private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, LogExecution controllerLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "operResult")
    public void doAfterReturning(JoinPoint joinPoint, LogExecution controllerLog, Object operResult) {
        handleLog(joinPoint, controllerLog, null, operResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, LogExecution controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, LogExecution controllerLog, final Exception e, Object operResult) {
        try {
            // *========数据库日志=========*//
            OperLog operLog = new OperLog();
            operLog.setStatus(0);
            if (e != null) {
                operLog.setStatus(1);
                operLog.setErrorMsg(e.getMessage().substring(0, Math.min(e.getMessage().length(), 2000)));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, operResult);

            operLog.setOperTime(new Date(System.currentTimeMillis()));
            // 设置消耗时间
            operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            //todo  保存数据库
            log.debug("测试访问正确保存数据库：{}", operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, LogExecution log, OperLog operLog, Object operResult) throws Exception {
        // 设置标题
        operLog.setTitle(log.value());
        // 是否需要保存request，参数和值
        // 获取参数的信息，传入到数据库中。
        operLog.setMethod(joinPoint.getSignature().getName());

        operLog.setOperParam((joinPoint.getArgs() != null ? Arrays.toString(joinPoint.getArgs()) : ""));
        // 是否需要保存response，参数和值
       // if (!Objects.isNull(operResult)) operLog.setOperResult(JSONUtil.toJsonStr(operResult));

    }
}
