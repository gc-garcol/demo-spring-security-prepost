package gc.garcol.demospringsecurity.testRefFunction;

import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

/**
 * @author garcol
 */
public class TStringUtils {

    public static String addZero(String str) {
        return str + "0";
    }

    @Test
    public void testRefFunction() throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("addZeroRef",
                TStringUtils.class.getDeclaredMethod("addZero", String.class));

        String helloWorldReversed = parser.parseExpression(
                "#addZeroRef('hello')").getValue(context, String.class);
        System.out.println(helloWorldReversed);
    }

}
