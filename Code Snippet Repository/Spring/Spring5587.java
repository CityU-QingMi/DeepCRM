	@Test
	public void getPrefixes() {
		assertThat("Returns only \"xmlns\" for \"http://www.w3.org/2000/xmlns/\"",
				getItemSet(context.getPrefixes(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)),
				is(makeSet(XMLConstants.XMLNS_ATTRIBUTE)));
		assertThat("Returns only \"xml\" for \"http://www.w3.org/XML/1998/namespace\"",
				getItemSet(context.getPrefixes(XMLConstants.XML_NS_URI)), is(makeSet(XMLConstants.XML_NS_PREFIX)));

		assertThat("Returns empty iterator for unbound prefix", context.getPrefixes("unbound Namespace URI").hasNext(),
				is(false));
		context.bindNamespaceUri("prefix1", namespaceUri);
		context.bindNamespaceUri("prefix2", namespaceUri);
		assertThat("Returns all prefixes (and only those) bound to the namespace URI",
				getItemSet(context.getPrefixes(namespaceUri)), is(makeSet("prefix1", "prefix2")));
	}
