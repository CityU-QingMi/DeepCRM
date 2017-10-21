	private Set<String> getPrefixesSet(String namespaceUri) {
		Assert.notNull(namespaceUri, "No namespaceUri given");
		if (this.defaultNamespaceUri.equals(namespaceUri)) {
			return Collections.singleton(XMLConstants.DEFAULT_NS_PREFIX);
		}
		else if (XMLConstants.XML_NS_URI.equals(namespaceUri)) {
			return Collections.singleton(XMLConstants.XML_NS_PREFIX);
		}
		else if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceUri)) {
			return Collections.singleton(XMLConstants.XMLNS_ATTRIBUTE);
		}
		else {
			Set<String> prefixes = this.namespaceUriToPrefixes.get(namespaceUri);
			return (prefixes != null ?  Collections.unmodifiableSet(prefixes) : Collections.emptySet());
		}
	}
