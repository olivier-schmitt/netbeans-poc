
package org.netbeans.spi.editor.completion.xhtml.impl.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemDataProvider;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService;
import org.openide.util.Exceptions;

/**
 *
 * @author oschmitt
 */
public class BeanCompletionItemService implements CompletionItemService {

    CompletionItemDataProvider bean;

    @Override
    public boolean accept(String scheme) {
        return "bean".equals(scheme);
    }

    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        try {
            String beanClassName = uri.getSchemeSpecificPart();
            Class beanClass = Thread.currentThread().getContextClassLoader().loadClass(beanClassName);
            this.bean = (CompletionItemDataProvider)beanClass.newInstance();
        } catch (Exception ex) {
            throw new CompletionConfigurationException(ex);
        }
    }

    @Override
    public List<CompletionItemData> getDatas(String query) {
        try {
            return this.bean.getDatas(query);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return Collections.EMPTY_LIST;
    }
}
