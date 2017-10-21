	@Override
	public final String getElementText() throws XMLStreamException {
		XMLEvent event = this.previousEvent;
		if (event == null) {
			throw new XMLStreamException("Must be on START_ELEMENT to read next text, element was null");
		}
		if (!event.isStartElement()) {
			throw new XMLStreamException("Must be on START_ELEMENT to read next text", event.getLocation());
		}

		final StringBuilder text = new StringBuilder();
		while (!event.isEndDocument()) {
			switch (event.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
				case XMLStreamConstants.SPACE:
				case XMLStreamConstants.CDATA: {
					final Characters characters = event.asCharacters();
					text.append(characters.getData());
					break;
				}
				case XMLStreamConstants.ENTITY_REFERENCE: {
					final EntityReference entityReference = (EntityReference)event;
					final EntityDeclaration declaration = entityReference.getDeclaration();
					text.append(declaration.getReplacementText());
					break;
				}
				case XMLStreamConstants.COMMENT:
				case XMLStreamConstants.PROCESSING_INSTRUCTION: {
					//Ignore
					break;
				}
				default: {
					throw new XMLStreamException("Unexpected event type '" + XMLStreamConstantsUtils.getEventName(event.getEventType()) + "' encountered. Found event: " + event, event.getLocation());
				}
			}

			event = this.nextEvent();
		}

		return text.toString();
	}
