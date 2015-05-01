package com.carplates.web.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Target;

@Qualifier

@Retention(RUNTIME)

@Target({TYPE, METHOD, FIELD, PARAMETER})

public @interface Active {}