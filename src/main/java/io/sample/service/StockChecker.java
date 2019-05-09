package io.sample.service;

import org.w3c.dom.Document;

import io.sample.service.file.FileHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathExpression;

public class StockChecker {

	private final XPathExpression isbnSelectingXPath;

	private static final Logger log = LoggerFactory.getLogger(StockChecker.class);
	
	public StockChecker(XPathExpression isbnSelectingXPath) {
		this.isbnSelectingXPath = isbnSelectingXPath;
	}

	public Document checkStockLevel(Document doc) {
		
		String isbn = isbnSelectingXPath.evaluateAsString(doc);
		boolean inStock = false;

		// we only carry stock of one book currently
		if ("0321200683".equals(isbn)) {
			inStock = true;
		}
		log.info("Dispatching the stock-ISBN:" + isbn);
		doc.getDocumentElement().setAttribute("in-stock", String.valueOf(inStock));
		return doc;
	}

}
