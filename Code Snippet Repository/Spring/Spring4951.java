	private void handleStartElement() throws SAXException {
		if (getContentHandler() != null) {
			QName qName = this.reader.getName();
			if (hasNamespacesFeature()) {
				for (int i = 0; i < this.reader.getNamespaceCount(); i++) {
					startPrefixMapping(this.reader.getNamespacePrefix(i), this.reader.getNamespaceURI(i));
				}
				for (int i = 0; i < this.reader.getAttributeCount(); i++) {
					String prefix = this.reader.getAttributePrefix(i);
					String namespace = this.reader.getAttributeNamespace(i);
					if (StringUtils.hasLength(namespace)) {
						startPrefixMapping(prefix, namespace);
					}
				}
				getContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalPart(),
						toQualifiedName(qName), getAttributes());
			}
			else {
				getContentHandler().startElement("", "", toQualifiedName(qName), getAttributes());
			}
		}
	}
