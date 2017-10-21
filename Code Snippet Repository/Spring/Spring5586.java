	@Test
	public void getPrefix() {
		assertThat("Always returns \"xmlns\" for \"http://www.w3.org/2000/xmlns/\"",
				context.getPrefix(XMLConstants.XMLNS_ATTRIBUTE_NS_URI), is(XMLConstants.XMLNS_ATTRIBUTE));
		assertThat("Always returns \"xml\" for \"http://www.w3.org/XML/1998/namespace\"",
				context.getPrefix(XMLConstants.XML_NS_URI), is(XMLConstants.XML_NS_PREFIX));

		assertThat("Returns null for an unbound namespace URI", context.getPrefix(unboundNamespaceUri),
				is(nullValue()));
		context.bindNamespaceUri("prefix1", namespaceUri);
		context.bindNamespaceUri("prefix2", namespaceUri);
		assertThat("Returns a prefix for a bound namespace URI", context.getPrefix(namespaceUri),
				anyOf(is("prefix1"), is("prefix2")));
	}
