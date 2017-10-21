	@Override
	public final void startDocument() throws SAXException {
		removeAllNamespaceMappings();
		newNamespaceMapping();
		try {
			startDocumentInternal();
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle startDocument: " + ex.getMessage(), ex);
		}
	}
