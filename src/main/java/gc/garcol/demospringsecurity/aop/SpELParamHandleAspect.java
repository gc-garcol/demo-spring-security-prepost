package gc.garcol.demospringsecurity.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author garcol
 */
@Slf4j
@Aspect
@Component
public class SpELParamHandleAspect {

    ExpressionParser parser = new SpelExpressionParser();

    @Pointcut("@annotation(SpELParamHandleAnnotation)")
    public void spELParamHandleAnnotationPoincut() {}

    @Before("spELParamHandleAnnotationPoincut()")
    public void beforeSpELParamHandleAnnotationPoincut(JoinPoint joinPoint) {
        log.info("{} | {} | {}", joinPoint, joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        List<String> paramNames = Arrays.asList(signature.getParameterNames());
        List<Object> paramValues = Arrays.asList(joinPoint.getArgs());

        EvaluationContext context = new StandardEvaluationContext();
        IntStream.range(0, paramNames.size())
                .forEach(i -> context.setVariable(paramNames.get(i), paramValues.get(i)));

        Method method = signature.getMethod();
        SpELParamHandleAnnotation spELAnnotation = method.getAnnotation(SpELParamHandleAnnotation.class);
        Expression expression = parser.parseExpression(spELAnnotation.spEL());
        Object valueFromSpEL = expression.getValue(context);
        log.info("value from SpELParamHandle {}", valueFromSpEL);
    }

}
