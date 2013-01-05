package org.netbeans.spi.editor.completion.xhtml.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PACKAGE)
public @interface AttributeCompletionRegistration {

    String id();

    String iconBase();

    String attribute();

    /*
     * Content is an URI : first part tells what component to use.
     */
    String content();

    String contentType() default "";
}
