	@Test
	public void marshalSaxResult() throws Exception {
		ContentHandler contentHandler = mock(ContentHandler.class);
		SAXResult result = new SAXResult(contentHandler);
		marshaller.marshal(flights, result);
		InOrder ordered = inOrder(contentHandler);
		ordered.verify(contentHandler).startDocument();
		ordered.verify(contentHandler).startPrefixMapping("tns", "http://samples.springframework.org/flight");
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"),
				eq("flights"), eq("tns:flights"), isA(Attributes.class));
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"),
				eq("flight"), eq("tns:flight"), isA(Attributes.class));
		ordered.verify(contentHandler).startElement(eq("http://samples.springframework.org/flight"),
				eq("number"), eq("tns:number"), isA(Attributes.class));
		ordered.verify(contentHandler).characters(eq(new char[]{'4', '2'}), eq(0), eq(2));
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "number", "tns:number");
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "flight", "tns:flight");
		ordered.verify(contentHandler).endElement("http://samples.springframework.org/flight", "flights", "tns:flights");
		ordered.verify(contentHandler).endPrefixMapping("tns");
		ordered.verify(contentHandler).endDocument();
	}
