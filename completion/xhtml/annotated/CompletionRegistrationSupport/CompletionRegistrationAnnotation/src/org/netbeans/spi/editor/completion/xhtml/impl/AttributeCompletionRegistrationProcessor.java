package org.netbeans.spi.editor.completion.xhtml.impl;

import java.util.Set;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistration;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistrations;
import org.openide.filesystems.annotations.LayerBuilder;
import org.openide.filesystems.annotations.LayerGeneratingProcessor;
import org.openide.filesystems.annotations.LayerGenerationException;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = Processor.class)
@SupportedAnnotationTypes({
    "org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistration",
    "org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistrations"
})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AttributeCompletionRegistrationProcessor extends LayerGeneratingProcessor {

    @Override
    protected boolean handleProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) throws LayerGenerationException {
        if (roundEnv.processingOver()) {
            return false;
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(AttributeCompletionRegistration.class)) {
            AttributeCompletionRegistration attrCompletionRegistration = element.getAnnotation(AttributeCompletionRegistration.class);
            if (attrCompletionRegistration == null) {
                continue;
            }
            process(element, attrCompletionRegistration);
        }
        for (Element e : roundEnv.getElementsAnnotatedWith(AttributeCompletionRegistrations.class)) {
            AttributeCompletionRegistrations rr = e.getAnnotation(AttributeCompletionRegistrations.class);
            if (rr == null) {
                continue;
            }
            for (AttributeCompletionRegistration t : rr.value()) {
                process(e, t);
            }
        }
        return true;
    }

    protected boolean process(Element element, AttributeCompletionRegistration attributeCompletionRegistration) throws LayerGenerationException {
        LayerBuilder layerBuilder = layer(element);
        LayerBuilder.File layerFile = layerBuilder.file(String.format(
                "Editors/text/xhtml/CompletionProviders/%s.instance",
                attributeCompletionRegistration.id()));
        layerFile.stringvalue("iconBase", attributeCompletionRegistration.iconBase());
        layerFile.stringvalue("attribute", attributeCompletionRegistration.attribute());
        layerFile.stringvalue("content", attributeCompletionRegistration.content());
        layerFile.methodvalue("instanceCreate", "org.netbeans.spi.editor.completion.xhtml.impl.AttributeCompletionProvider", "create");
        layerFile.write();
        return true;
    }
    
}
