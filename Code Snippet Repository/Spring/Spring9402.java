	@SuppressWarnings("")
	private SAXSource readSAXSource(InputStream body) throws IOException {
		try {
			XMLReader xmlReader = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", !isSupportDtd());
			xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", isProcessExternalEntities());
			if (!isProcessExternalEntities()) {
				xmlReader.setEntityResolver(NO_OP_ENTITY_RESOLVER);
			}
			byte[] bytes = StreamUtils.copyToByteArray(body);
			return new SAXSource(xmlReader, new InputSource(new ByteArrayInputStream(bytes)));
		}
		catch (SAXException ex) {
			throw new HttpMessageNotReadableException("Could not parse document: " + ex.getMessage(), ex);
		}
	}
