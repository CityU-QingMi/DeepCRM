	public void bindNamespaceUri(String prefix, String namespaceUri) {
		Assert.notNull(prefix, "No prefix given");
		Assert.notNull(namespaceUri, "No namespaceUri given");
		if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
			this.defaultNamespaceUri = namespaceUri;
		}
		else {
			this.prefixToNamespaceUri.put(prefix, namespaceUri);
			Set<String> prefixes = this.namespaceUriToPrefixes.get(namespaceUri);
			if (prefixes == null) {
				prefixes = new LinkedHashSet<>();
				this.namespaceUriToPrefixes.put(namespaceUri, prefixes);
			}
			prefixes.add(prefix);
		}
	}
