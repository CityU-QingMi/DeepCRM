	protected QName toQName(String namespaceUri, String qualifiedName) {
		int idx = qualifiedName.indexOf(':');
		if (idx == -1) {
			return new QName(namespaceUri, qualifiedName);
		}
		else {
			String prefix = qualifiedName.substring(0, idx);
			String localPart = qualifiedName.substring(idx + 1);
			return new QName(namespaceUri, localPart, prefix);
		}
	}
