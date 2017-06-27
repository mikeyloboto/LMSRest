package com.gcit.library.utility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UselessAspect {
	@Around(value = "@annotation(annotation)")
    public Object UselessAspect(final ProceedingJoinPoint joinPoint, final UselessAnnotation annotation) throws Throwable {
        try {
            System.out.println("Pointless Aspect Called");
            final Object retVal = joinPoint.proceed();
            return retVal;
        } finally {
            System.out.println("You terribly written code was executed");
        }

    }
}

