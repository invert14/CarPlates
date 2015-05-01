package com.carplates.security;

import org.jboss.seam.security.annotations.SecurityBindingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SecurityBindingType

@Retention(RetentionPolicy.RUNTIME)

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})

public @interface Global {

}
