	private Source readStAXSource(InputStream body) {
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, isSupportDtd());
			inputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, isProcessExternalEntities());
			if (!isProcessExternalEntities()) {
				inputFactory.setXMLResolver(NO_OP_XML_RESOLVER);
			}
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(body);
			return new StAXSource(streamReader);
		}
		catch (XMLStreamException ex) {
			throw new HttpMessageNotReadableException("Could not parse document: " + ex.getMessage(), ex);
		}
	}
