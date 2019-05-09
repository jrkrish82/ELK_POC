package io.sample.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.GenericMessage;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import io.sample.service.file.FileHandler;

public class XmltoDocServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(XmltoDocServiceImpl.class);
	
	public GenericMessage<Document> convert(String message){
		log.info("Into the doc service : "+message);
		
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		 factory.setNamespaceAware(true);
	        DocumentBuilder builder;  
	        try  
	        {  
	            builder = factory.newDocumentBuilder();  
	            
	            Document doc = builder.parse( new InputSource( new StringReader( message ) ) ); 
	            
	            log.info("Into the doc service"+convertDocumentToString(doc));
	            return new GenericMessage<Document>(doc);
	        } catch (Exception e) {  
	            log.error("Error converting the message",e); 
	        } 
	    	log.info("Completed doc service : "+message);
	        return null;
	   }
	
	
	private String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
