package pl.springkurs.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Log
public class LengthValidator {

    @Before("execution(* *(@pl.springkurs.shop.commons.aop.Length (*)))")
    public void validate(JoinPoint joinPoint) throws NoSuchMethodException {
        var signature = (MethodSignature) joinPoint.getSignature();
        var methodName = signature.getMethod().getName();
        var parameterTypes = signature.getMethod().getParameterTypes();
        var annotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        var arguments = joinPoint.getArgs();

        for (int index = 0; index < arguments.length; index++) {
            var argument = (String) arguments[index];
            var annotation = getLengthAdnotation(annotations[index]);
            annotation.ifPresent(length -> {
                if (argument.length() < length.value()) {
                    throw new IllegalArgumentException();
                }
            });

        }

    }

    private Optional<Length> getLengthAdnotation(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(Length.class::isInstance)
                .map(Length.class::cast)
                .findFirst();
    }
}
