	@Test
	public void partial() throws Exception {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = inputFactory.createXMLEventReader(new StringReader(CONTENT));
		eventReader.nextTag();  // skip to root
		StaxEventXMLReader xmlReader = new StaxEventXMLReader(eventReader);
		ContentHandler contentHandler = mock(ContentHandler.class);
		xmlReader.setContentHandler(contentHandler);
		xmlReader.parse(new InputSource());
		verify(contentHandler).startDocument();
		verify(contentHandler).startElement(eq("http://springframework.org/spring-ws"), eq("child"), eq("child"), any(Attributes.class));
		verify(contentHandler).endElement("http://springframework.org/spring-ws", "child", "child");
		verify(contentHandler).endDocument();
	}
