	private void handleCharacters() throws SAXException {
		if (XMLStreamConstants.CDATA == this.reader.getEventType() && getLexicalHandler() != null) {
			getLexicalHandler().startCDATA();
		}
		if (getContentHandler() != null) {
			getContentHandler().characters(this.reader.getTextCharacters(),
					this.reader.getTextStart(), this.reader.getTextLength());
		}
		if (XMLStreamConstants.CDATA == this.reader.getEventType() && getLexicalHandler() != null) {
			getLexicalHandler().endCDATA();
		}
	}
