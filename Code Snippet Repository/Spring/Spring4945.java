	private void handleCharacters(Characters characters) throws SAXException {
		char[] data = characters.getData().toCharArray();
		if (getContentHandler() != null && characters.isIgnorableWhiteSpace()) {
			getContentHandler().ignorableWhitespace(data, 0, data.length);
			return;
		}
		if (characters.isCData() && getLexicalHandler() != null) {
			getLexicalHandler().startCDATA();
		}
		if (getContentHandler() != null) {
			getContentHandler().characters(data, 0, data.length);
		}
		if (characters.isCData() && getLexicalHandler() != null) {
			getLexicalHandler().endCDATA();
		}
	}
