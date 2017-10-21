	@Override
	public String getText() {
		if (this.event.isCharacters()) {
			return event.asCharacters().getData();
		}
		else if (this.event.getEventType() == XMLEvent.COMMENT) {
			return ((Comment) this.event).getText();
		}
		else {
			throw new IllegalStateException();
		}
	}
