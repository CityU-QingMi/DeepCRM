	@Test
	public void namespacePrefixes() throws Exception {
		Assume.assumeTrue(wwwSpringframeworkOrgIsAccessible());

		StringWriter stringWriter = new StringWriter();
		AbstractStaxHandler handler = createStaxHandler(new StreamResult(stringWriter));
		xmlReader.setContentHandler(handler);
		xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);

		xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
		xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

		xmlReader.parse(new InputSource(new StringReader(COMPLEX_XML)));

		assertThat(stringWriter.toString(), isSimilarTo(COMPLEX_XML).withNodeFilter(nodeFilter));
	}
