	@Override
	public String getNamespaceURI() {
		int eventType = getEventType();
		if (eventType == XMLStreamConstants.START_ELEMENT || eventType == XMLStreamConstants.END_ELEMENT) {
			return getName().getNamespaceURI();
		}
		else {
			throw new IllegalStateException("parser must be on START_ELEMENT or END_ELEMENT state");
		}
	}
