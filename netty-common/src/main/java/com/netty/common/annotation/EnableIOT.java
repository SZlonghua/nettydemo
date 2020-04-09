package com.netty.common.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({IOTBootstrapConfiguration.class})
public @interface EnableIOT {
}
