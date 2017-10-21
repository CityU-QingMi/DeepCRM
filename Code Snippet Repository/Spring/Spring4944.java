	private void handleStartElement(StartElement startElement) throws SAXException {
		if (getContentHandler() != null) {
			QName qName = startElement.getName();
			if (hasNamespacesFeature()) {
				for (Iterator i = startElement.getNamespaces(); i.hasNext();) {
					Namespace namespace = (Namespace) i.next();
					startPrefixMapping(namespace.getPrefix(), namespace.getNamespaceURI());
				}
				for (Iterator i = startElement.getAttributes(); i.hasNext();){
					Attribute attribute = (Attribute) i.next();
					QName attributeName = attribute.getName();
					startPrefixMapping(attributeName.getPrefix(), attributeName.getNamespaceURI());
				}

				getContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalPart(), toQualifiedName(qName),
						getAttributes(startElement));
			}
			else {
				getContentHandler().startElement("", "", toQualifiedName(qName), getAttributes(startElement));
			}
		}
	}
