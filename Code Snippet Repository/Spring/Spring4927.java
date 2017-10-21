	@Override
	public String getPrefix() {
		int eventType = getEventType();
		if (eventType == XMLStreamConstants.START_ELEMENT || eventType == XMLStreamConstants.END_ELEMENT) {
			return getName().getPrefix();
		}
		else {
			throw new IllegalStateException("parser must be on START_ELEMENT or END_ELEMENT state");
		}
	}
