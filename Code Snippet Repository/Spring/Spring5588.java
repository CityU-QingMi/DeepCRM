	@Test
	public void removeBinding() {
		context.removeBinding(unboundPrefix);

		context.bindNamespaceUri(prefix, namespaceUri);
		context.removeBinding(prefix);
		assertThat("Returns default namespace URI for removed prefix", context.getNamespaceURI(prefix),
				is(XMLConstants.NULL_NS_URI));
		assertThat("#getPrefix returns null when all prefixes for a namespace URI were removed",
				context.getPrefix(namespaceUri), is(nullValue()));
		assertThat("#getPrefixes returns an empty iterator when all prefixes for a namespace URI were removed",
				context.getPrefixes(namespaceUri).hasNext(), is(false));

		context.bindNamespaceUri("prefix1", additionalNamespaceUri);
		context.bindNamespaceUri("prefix2", additionalNamespaceUri);
		context.removeBinding("prefix1");
		assertThat("Prefix was unbound", context.getNamespaceURI("prefix1"), is(XMLConstants.NULL_NS_URI));
		assertThat("#getPrefix returns a bound prefix after removal of another prefix for the same namespace URI",
				context.getPrefix(additionalNamespaceUri), is("prefix2"));
		assertThat("Prefix was removed from namespace URI", getItemSet(context.getPrefixes(additionalNamespaceUri)),
				is(makeSet("prefix2")));
	}
