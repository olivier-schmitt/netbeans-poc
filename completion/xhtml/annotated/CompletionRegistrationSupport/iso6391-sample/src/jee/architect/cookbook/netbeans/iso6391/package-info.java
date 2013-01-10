@AttributeCompletionRegistrations({
   
    @AttributeCompletionRegistration(
        id = "jee-architect-cookbook-netbeans-iso6391-CSVCompletionItemService",
        attribute = "lang",
        icon = "jee/architect/cookbook/netbeans/iso6391/bubble.png",
        content = "csv:jee/architect/cookbook/netbeans/iso6391/ISO6391.csv"),
   
    @AttributeCompletionRegistration(
        id = "jee-architect-cookbook-netbeans-iso6391-RESTCompletionItemService",
        attribute = "accesskey",
        icon = "jee/architect/cookbook/netbeans/iso6391/bubble.png",
        content = "rest:http://api.worldbank.org/country?format=json"),
    
    @AttributeCompletionRegistration(
        id = "jee-architect-cookbook-netbeans-iso6391-BlaRawCompletionService",
        attribute = "bla",
        icon = "jee/architect/cookbook/netbeans/iso6391/bubble.png",
        content = "raw:tom,dick,harry"),
    
    @AttributeCompletionRegistration(
        id = "jee-architect-cookbook-netbeans-iso6391-BeanCompletionItemDataProvider",
        attribute = "bla2",
        icon = "jee/architect/cookbook/netbeans/iso6391/bubble.png",
        content = "bean:jee.architect.cookbook.netbeans.iso6391.Bla2CompletionItemDataProvider"),
    
    @AttributeCompletionRegistration(
        id = "jee-architect-cookbook-netbeans-iso6391-ISO6391AttributeCompletionService",
        attribute = "lang2",
        icon = "jee/architect/cookbook/netbeans/iso6391/bubble.png",
        content = "iso6391:nop") 
})
package jee.architect.cookbook.netbeans.iso6391;

import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistration;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionRegistrations;
