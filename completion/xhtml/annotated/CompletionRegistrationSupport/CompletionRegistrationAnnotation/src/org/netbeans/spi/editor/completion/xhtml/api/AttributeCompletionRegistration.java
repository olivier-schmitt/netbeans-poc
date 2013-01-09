package org.netbeans.spi.editor.completion.xhtml.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PACKAGE)
public @interface AttributeCompletionRegistration {

    String id();

    /**
     * <p>Icon classpath.</p>
     * 
     * @return Icon classpath.
     */
    String icon();

    /**
     * <p>Defines the attribute name upon which completion should occur.</p>
     * 
     * @return The attribute name
     */
    String attribute();

    /**
     * <p>Content is an URI : scheme tells what completion item provider to use.</p>
     * 
     * <p>A provider is a concrete class implementing CompletionItemProvider.</p>
     * 
     * <p>
     * Sample URI : <pre>csv:jee/architect/cookbook/netbeans/iso6391/ISO6391.csv</pre>
     * </p>
     * 
     * <p>An URI always starts with a scheme and requires a following part.</p>
     * <p>Smallest URI looks like "myscheme:nop" (nop means no null here).</p>
     * 
     * @return The URI
     */
    String content();

    /**
     * <p>A full classname.</p>
     * <p>The choosen class must implements CompleteAction.</p>
     * 
     * @return The classname
     */
    String action() default "";

    
}