package com.example.studentservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Aspect
public class LoggingAspect  {
	
	
	
	/**
     * Aspect that logs the execution time of methods in the specified controller.
     *
     * @param joinPoint - provides reflective access to both the state available at a join point 
     *                   and the static part of the join point (method name, parameters, etc.)
     * @return the result of the method execution
     * @throws Throwable if the method execution fails
     */
    @Around("execution(* com.example.studentservice.studentcontroller.StudentController.*(..))") // Modify the package as per your project's structure
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        
        // Log message before method execution
        log.info("Before executing " + joinPoint.getSignature());
        
        // Record the start time of method execution
        long startTime = System.currentTimeMillis();
        
        // Proceed with the method execution
        Object proceed = joinPoint.proceed();
        
        // Calculate the execution time
        long executionTime = System.currentTimeMillis() - startTime;
        
        // Log message after method execution
        log.info("After executing " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        // Return the result of the method execution
        return proceed;
    }

}
