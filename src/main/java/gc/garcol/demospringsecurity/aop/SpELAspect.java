package gc.garcol.demospringsecurity.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author garcol
 */
@Slf4j
@Aspect
@Component
public class SpELAspect {

    @Pointcut("@annotation(SpELAnnotation)")
    public void spELAnnotationPoincut() {}

    @Before("spELAnnotationPoincut()")
    public void beforeSpELAnnotationPoincut(JoinPoint joinPoint) {
        log.info("{} | {} | {}", joinPoint, joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SpELAnnotation spELAnnotation = method.getAnnotation(SpELAnnotation.class);
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(spELAnnotation.spEL());
        Object value = exp.getValue(spELAnnotation.type());

        log.info("spEL value {}", value);
    }

}
