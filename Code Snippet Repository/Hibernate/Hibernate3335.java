	@Override
	public final XMLEvent nextTag() throws XMLStreamException {
		XMLEvent event = this.nextEvent();
		while ((event.isCharacters() && event.asCharacters().isWhiteSpace())
				|| event.isProcessingInstruction()
				|| event.getEventType() == XMLStreamConstants.COMMENT) {

			event = this.nextEvent();
		}

		if (!event.isStartElement()  && event.isEndElement()) {
			throw new XMLStreamException("Unexpected event type '" + XMLStreamConstantsUtils.getEventName(event.getEventType()) + "' encountered. Found event: " + event, event.getLocation());
		}

		return event;
	}
