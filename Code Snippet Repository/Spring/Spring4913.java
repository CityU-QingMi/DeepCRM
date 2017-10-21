	@Override
	public final void characters(char ch[], int start, int length) throws SAXException {
		try {
			String data = new String(ch, start, length);
			if (!this.inCData) {
				charactersInternal(data);
			}
			else {
				cDataInternal(data);
			}
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle characters: " + ex.getMessage(), ex);
		}
	}
