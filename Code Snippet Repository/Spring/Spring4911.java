	@Override
	public final void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		try {
			startElementInternal(toQName(uri, qName), atts, currentNamespaceMapping());
			newNamespaceMapping();
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle startElement: " + ex.getMessage(), ex);
		}
	}
