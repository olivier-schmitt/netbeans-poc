/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;
import org.openide.util.Exceptions;

/**
 *
 * @author oschmitt
 */
public class BeanCompletionItemProvider implements CompletionItemProvider {

    Object bean;
    
    @Override
    public List<CompletionItem> getCompletionItem(AttributeInCompletion attribute, Map annotationConfMap) {
        try {
            Method method = bean.getClass().getMethod("getCompletionItem", AttributeInCompletion.class,Map.class);
            return (List<CompletionItem>) method.invoke(bean,attribute,annotationConfMap);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean accept(String scheme) {
        return "bean".equals(scheme);
    }

    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        try {
            String beanClassName = uri.getSchemeSpecificPart();
            Class beanClass = Thread.currentThread().getContextClassLoader().loadClass(beanClassName);
            this.bean = beanClass.newInstance();
        } catch (Exception ex) {
            throw new CompletionConfigurationException(ex);
        }
    }
}
