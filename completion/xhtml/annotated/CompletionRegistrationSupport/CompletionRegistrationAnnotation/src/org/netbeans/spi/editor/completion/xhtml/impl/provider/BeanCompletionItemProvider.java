/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemValue;
import org.openide.util.Exceptions;

/**
 *
 * @author oschmitt
 */
public class BeanCompletionItemProvider implements CompletionItemProvider {

    Object bean;

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

    @Override
    public List<CompletionItemValue> getCompletionItemValues(String query) {
        try {
            Method method = bean.getClass().getMethod("getCompletionItemValues", String.class);
            return (List<CompletionItemValue>) method.invoke(bean,query);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return Collections.EMPTY_LIST;
    }
}
