package wd.city_division.aspect;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wd.city_division.annotation.ControllerEndpoint;
import wd.city_division.exception.FebsException;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author MrBird
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerEndpointAspect extends BaseAspectSupport {

//    private final ILogService logService;

    @Pointcut("@annotation(wd.city_division.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws FebsException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
//        long start = System.currentTimeMillis();
        Stopwatch sw =  Stopwatch.createStarted();
        sw.stop();
        long elapsed = sw.elapsed(TimeUnit.MILLISECONDS);
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
                String ip = StringUtils.EMPTY;
                if (servletRequestAttributes != null) {
                    ip = servletRequestAttributes.getRequest().getRemoteAddr();
                }
//                logService.saveLog(point, targetMethod, ip, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
//            String error = FebsUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            String error = "ControllerEndpoint-温馨提示："+exceptionMessage;
            throw new FebsException(error);
        }
    }
}



