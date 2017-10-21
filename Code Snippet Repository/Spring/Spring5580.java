	@Test
	public void lexicalHandler() throws Exception {
		Resource testLexicalHandlerXml = new ClassPathResource("testLexicalHandler.xml", getClass());

		LexicalHandler expectedLexicalHandler = mockLexicalHandler();
		standardReader.setContentHandler(null);
		standardReader.setProperty("http://xml.org/sax/properties/lexical-handler", expectedLexicalHandler);
		standardReader.parse(new InputSource(testLexicalHandlerXml.getInputStream()));
		inputFactory.setProperty("javax.xml.stream.isCoalescing", Boolean.FALSE);
		inputFactory.setProperty("http://java.sun.com/xml/stream/properties/report-cdata-event", Boolean.TRUE);
		inputFactory.setProperty("javax.xml.stream.isReplacingEntityReferences", Boolean.FALSE);
		inputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", Boolean.FALSE);

		LexicalHandler actualLexicalHandler = mockLexicalHandler();
		willAnswer(invocation -> invocation.getArguments()[0] = "element").
				given(actualLexicalHandler).startDTD(anyString(), anyString(), anyString());
		AbstractStaxXMLReader staxXmlReader = createStaxXmlReader(testLexicalHandlerXml.getInputStream());
		staxXmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", actualLexicalHandler);
		staxXmlReader.parse(new InputSource());

		// TODO: broken comparison since Mockito 2.2 upgrade
		// verifyIdenticalInvocations(expectedLexicalHandler, actualLexicalHandler);
	}
