	@Override
	public String getElementText() throws XMLStreamException {
		checkIfClosed();
		if (!peek().isStartElement()) {
			throw new XMLStreamException("Not at START_ELEMENT");
		}

		StringBuilder builder = new StringBuilder();
		while (true) {
			XMLEvent event = nextEvent();
			if (event.isEndElement()) {
				break;
			}
			else if (!event.isCharacters()) {
				throw new XMLStreamException(
						"Unexpected event [" + event + "] in getElementText()");
			}
			Characters characters = event.asCharacters();
			if (!characters.isIgnorableWhiteSpace()) {
				builder.append(event.asCharacters().getData());
			}
		}
		return builder.toString();
	}
