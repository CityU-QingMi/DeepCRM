	private void handleDtd(DTD dtd) throws SAXException {
		if (getLexicalHandler() != null) {
			javax.xml.stream.Location location = dtd.getLocation();
			getLexicalHandler().startDTD(null, location.getPublicId(), location.getSystemId());
		}
		if (getLexicalHandler() != null) {
			getLexicalHandler().endDTD();
		}

	}
