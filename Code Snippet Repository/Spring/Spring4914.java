	@Override
	public final void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		try {
			ignorableWhitespaceInternal(new String(ch, start, length));
		}
		catch (XMLStreamException ex) {
			throw new SAXException(
					"Could not handle ignorableWhitespace:" + ex.getMessage(), ex);
		}
	}
