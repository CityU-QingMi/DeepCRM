	@Test
	public void contentHandlerNamespacesPrefixes() throws Exception {
		standardReader.setFeature("http://xml.org/sax/features/namespaces", true);
		standardReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
		standardReader.parse(new InputSource(createTestInputStream()));

		AbstractStaxXMLReader staxXmlReader = createStaxXmlReader(createTestInputStream());
		ContentHandler contentHandler = mockContentHandler();
		staxXmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
		staxXmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
		staxXmlReader.setContentHandler(contentHandler);
		staxXmlReader.parse(new InputSource());

		verifyIdenticalInvocations(standardContentHandler, contentHandler);
	}
