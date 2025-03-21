package com.example.domains.event;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    @Pointcut("execution(public * com.example..*.*(..))")
    public void cualquierMetodoPublico() {}

    @Pointcut("within(com.example.domains.contracts.repositories..*)")
    public void repositorios() {}

    @Pointcut("within(com.example.domains.services..*)")
    public void servicios() {}

    @Pointcut("execution(* com.example.domains.contracts.services.*.add(..))")
    public void metodosAdd() {}

    @Pointcut("execution(* com.example.domains.contracts.services.*.modify(..))")
    public void metodosModify() {}

    @Pointcut("execution(* com.example.domains.contracts.services.*.delete(..))")
    public void metodosDelete() {}

    @Pointcut("execution(* com.example.domains.contracts.services.*.deleteById(..))")
    public void metodosDeleteById() {}

    @Pointcut("(metodosAdd() || metodosModify() || metodosDelete() || metodosDeleteById()) && (repositorios() || servicios())")
    public void requiredAuthentication() {}
}