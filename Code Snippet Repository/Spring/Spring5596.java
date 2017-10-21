	@Test
	public void partial() throws Exception {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new StringReader(CONTENT));
		streamReader.nextTag();  // skip to root
		assertEquals("Invalid element", new QName("http://springframework.org/spring-ws", "root"),
				streamReader.getName());
		streamReader.nextTag();  // skip to child
		assertEquals("Invalid element", new QName("http://springframework.org/spring-ws", "child"),
				streamReader.getName());
		StaxStreamXMLReader xmlReader = new StaxStreamXMLReader(streamReader);

		ContentHandler contentHandler = mock(ContentHandler.class);
		xmlReader.setContentHandler(contentHandler);
		xmlReader.parse(new InputSource());

		verify(contentHandler).setDocumentLocator(any(Locator.class));
		verify(contentHandler).startDocument();
		verify(contentHandler).startElement(eq("http://springframework.org/spring-ws"), eq("child"), eq("child"), any(Attributes.class));
		verify(contentHandler).endElement("http://springframework.org/spring-ws", "child", "child");
		verify(contentHandler).endDocument();
	}
