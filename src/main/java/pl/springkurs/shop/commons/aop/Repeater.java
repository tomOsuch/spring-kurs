package pl.springkurs.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class Repeater {

    @Around("@annotation(retry)")
    public Object tryExecute(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        var attempt = 0;
        Throwable throwable;
        do {
            attempt++;
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                log.info("%s method execution failed (attemp: %d)".formatted(joinPoint.getSignature().getName(),attempt));
                throwable = e;
            }

        } while (attempt < retry.attempts());
        throw throwable;
    }

}
