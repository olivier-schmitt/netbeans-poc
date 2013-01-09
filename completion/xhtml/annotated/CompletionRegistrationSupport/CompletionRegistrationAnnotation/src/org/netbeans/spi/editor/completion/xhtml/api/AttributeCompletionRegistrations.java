package org.netbeans.spi.editor.completion.xhtml.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Defines all attribute completions for a package.</p>
 * 
 * @author oschmitt
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PACKAGE)
public @interface AttributeCompletionRegistrations {

    AttributeCompletionRegistration[] value();
}
