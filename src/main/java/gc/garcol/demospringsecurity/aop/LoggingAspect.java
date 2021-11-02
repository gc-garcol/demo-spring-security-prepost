package gc.garcol.demospringsecurity.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author garcol
 */
@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("@annotation(gc.garcol.demospringsecurity.aop.LogParam)")
    public void myEndpointPoincut() {}

    @Before("myEndpointPoincut()")
    public void beforeMyEndpointJoinpoint(JoinPoint joinPoint) {
        log.info("{} | {} | {}", joinPoint, joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());

    }

}
