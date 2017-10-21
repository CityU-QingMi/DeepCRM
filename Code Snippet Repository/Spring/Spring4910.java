	@Override
	public final void endDocument() throws SAXException {
		removeAllNamespaceMappings();
		try {
			endDocumentInternal();
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle endDocument: " + ex.getMessage(), ex);
		}
	}
