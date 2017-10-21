	private void handleEndElement(EndElement endElement) throws SAXException {
		if (getContentHandler() != null) {
			QName qName = endElement.getName();
			if (hasNamespacesFeature()) {
				getContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalPart(), toQualifiedName(qName));
				for (Iterator i = endElement.getNamespaces(); i.hasNext();) {
					Namespace namespace = (Namespace) i.next();
					endPrefixMapping(namespace.getPrefix());
				}
			}
			else {
				getContentHandler().endElement("", "", toQualifiedName(qName));
			}

		}
	}
