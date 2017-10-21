	private void handleEndElement() throws SAXException {
		if (getContentHandler() != null) {
			QName qName = this.reader.getName();
			if (hasNamespacesFeature()) {
				getContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalPart(), toQualifiedName(qName));
				for (int i = 0; i < this.reader.getNamespaceCount(); i++) {
					String prefix = this.reader.getNamespacePrefix(i);
					if (prefix == null) {
						prefix = "";
					}
					endPrefixMapping(prefix);
				}
			}
			else {
				getContentHandler().endElement("", "", toQualifiedName(qName));
			}
		}
	}
