	@Test
	public void marshalSAXResult() throws Exception {
		ContentHandler contentHandler = mock(ContentHandler.class);
		SAXResult result = new SAXResult(contentHandler);
		marshaller.marshal(flights, result);
		InOrder ordered = inOrder(contentHandler);
		ordered.verify(contentHandler).setDocumentLocator(isA(Locator.class));
		ordered.verify(contentHandler).startDocument();
		ordered.verify(contentHandler).startPrefixMapping("", "http://samples.springframework.org/flight");
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"), eq("flights"), eq("flights"), isA(Attributes.class));
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"), eq("flight"), eq("flight"), isA(Attributes.class));
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"), eq("number"), eq("number"), isA(Attributes.class));
		ordered.verify(contentHandler).characters(isA(char[].class), eq(0), eq(2));
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "number", "number");
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "flight", "flight");
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "flights", "flights");
		ordered.verify(contentHandler).endPrefixMapping("");
		ordered.verify(contentHandler).endDocument();
	}
