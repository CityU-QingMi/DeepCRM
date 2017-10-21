	@Override
	@Nullable
	public XMLEvent nextTag() throws XMLStreamException {
		checkIfClosed();
		while (true) {
			XMLEvent event = nextEvent();
			switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
				case XMLStreamConstants.END_ELEMENT:
					return event;
				case XMLStreamConstants.END_DOCUMENT:
					return null;
				case XMLStreamConstants.SPACE:
				case XMLStreamConstants.COMMENT:
				case XMLStreamConstants.PROCESSING_INSTRUCTION:
					continue;
				case XMLStreamConstants.CDATA:
				case XMLStreamConstants.CHARACTERS:
					if (!event.asCharacters().isWhiteSpace()) {
						throw new XMLStreamException(
								"Non-ignorable whitespace CDATA or CHARACTERS event in nextTag()");
					}
					break;
				default:
					throw new XMLStreamException("Received event [" + event +
							"], instead of START_ELEMENT or END_ELEMENT.");
			}
		}
	}
