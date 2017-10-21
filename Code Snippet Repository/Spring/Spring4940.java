	public void removeBinding(@Nullable String prefix) {
		if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
			this.defaultNamespaceUri = "";
		}
		else if (prefix != null) {
			String namespaceUri = this.prefixToNamespaceUri.remove(prefix);
			if (namespaceUri != null) {
				Set<String> prefixes = this.namespaceUriToPrefixes.get(namespaceUri);
				if (prefixes != null) {
					prefixes.remove(prefix);
					if (prefixes.isEmpty()) {
						this.namespaceUriToPrefixes.remove(namespaceUri);
					}
				}
			}
		}
	}
