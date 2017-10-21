	@Test
	public void namespacePrefixesDom() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document expected = documentBuilder.parse(new InputSource(new StringReader(SIMPLE_XML)));

		Document result = documentBuilder.newDocument();
		AbstractStaxHandler handler = createStaxHandler(new DOMResult(result));
		xmlReader.setContentHandler(handler);
		xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);

		xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
		xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

		xmlReader.parse(new InputSource(new StringReader(SIMPLE_XML)));

		assertThat(expected, isSimilarTo(result).withNodeFilter(nodeFilter));
	}
