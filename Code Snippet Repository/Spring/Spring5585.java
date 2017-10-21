	@Test
	public void getNamespaceURI() {
		context.bindNamespaceUri(XMLConstants.XMLNS_ATTRIBUTE, additionalNamespaceUri);
		assertThat("Always returns \"http://www.w3.org/2000/xmlns/\" for \"xmlns\"",
				context.getNamespaceURI(XMLConstants.XMLNS_ATTRIBUTE), is(XMLConstants.XMLNS_ATTRIBUTE_NS_URI));
		context.bindNamespaceUri(XMLConstants.XML_NS_PREFIX, additionalNamespaceUri);
		assertThat("Always returns \"http://www.w3.org/XML/1998/namespace\" for \"xml\"",
				context.getNamespaceURI(XMLConstants.XML_NS_PREFIX), is(XMLConstants.XML_NS_URI));

		assertThat("Returns \"\" for an unbound prefix", context.getNamespaceURI(unboundPrefix),
				is(XMLConstants.NULL_NS_URI));
		context.bindNamespaceUri(prefix, namespaceUri);
		assertThat("Returns the bound namespace URI for a bound prefix", context.getNamespaceURI(prefix),
				is(namespaceUri));

		assertThat("By default returns URI \"\" for the default namespace prefix",
				context.getNamespaceURI(XMLConstants.DEFAULT_NS_PREFIX), is(XMLConstants.NULL_NS_URI));
		context.bindDefaultNamespaceUri(defaultNamespaceUri);
		assertThat("Returns the set URI for the default namespace prefix",
				context.getNamespaceURI(XMLConstants.DEFAULT_NS_PREFIX), is(defaultNamespaceUri));
	}
