package io.sample.service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * This example demonstrates the processing of an order for books using 
 * several of the components provided by the Spring Integration Xml 
 * module for dealing with Xml payloads. This includes:
 *
 * <ul>
 *    <li>an XPath based implementation of the splitter pattern to split an
 * order with multiple items into several order messages for separate 
 * processing.</li>
 *    <li>XPath expression namespace support to build an XPath expression to
 * extract the isbn from each order item.</li>
 *    <li>an XPath router implementation to route messages according to the
 * evaluation of an XPath expression which tests to see if the order item is in
 * stock.</li>
 *    <li>an XSLT transformer implementation to transform the payload of the
 * order message into a resupply message where the order item is found to be
 * out of stock.</li>
 * </ul>
 *
 * 
 */
public class BookOrderProcessingTestApp {

	public static void main1(String[] args) throws Exception {
		AbstractApplicationContext applicationContext = 
			new ClassPathXmlApplicationContext("/META-INF/spring/integration/orderProcessingSample.xml",
				BookOrderProcessingTestApp.class);
		MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("ordersChannel");
		GenericMessage<Document> orderMessage = 
			createXmlMessageFromResource("META-INF/spring/integration/order.xml");
		messageChannel.send(orderMessage);
		applicationContext.close();
	}

	private static GenericMessage<Document> createXmlMessageFromResource(String path) throws Exception {
		Resource orderRes = new ClassPathResource(path);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document orderDoc = builder.parse(orderRes.getInputStream());
		return new GenericMessage<Document>(orderDoc);
	}

}
