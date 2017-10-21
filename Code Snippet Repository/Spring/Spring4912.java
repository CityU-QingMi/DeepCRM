	@Override
	public final void endElement(String uri, String localName, String qName) throws SAXException {
		try {
			endElementInternal(toQName(uri, qName), currentNamespaceMapping());
			removeNamespaceMapping();
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle endElement: " + ex.getMessage(), ex);
		}
	}
