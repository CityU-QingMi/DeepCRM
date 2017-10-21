	@SuppressWarnings("")
	protected XMLReader createXmlReader() throws SAXException {
		XMLReader xmlReader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
		xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", !isSupportDtd());
		xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", isProcessExternalEntities());
		if (!isProcessExternalEntities()) {
			xmlReader.setEntityResolver(NO_OP_ENTITY_RESOLVER);
		}
		return xmlReader;
	}
