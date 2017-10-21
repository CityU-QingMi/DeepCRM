	protected void startPrefixMapping(@Nullable String prefix, String namespace) throws SAXException {
		if (getContentHandler() != null) {
			if (prefix == null) {
				prefix = "";
			}
			if (!StringUtils.hasLength(namespace)) {
				return;
			}
			if (!namespace.equals(this.namespaces.get(prefix))) {
				getContentHandler().startPrefixMapping(prefix, namespace);
				this.namespaces.put(prefix, namespace);
			}
		}
	}
