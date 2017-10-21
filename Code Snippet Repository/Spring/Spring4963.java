	@SuppressWarnings("")
	private Attribute getAttribute(int index) {
		if (!this.event.isStartElement()) {
			throw new IllegalStateException();
		}
		int count = 0;
		Iterator attributes = this.event.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = (Attribute) attributes.next();
			if (count == index) {
				return attribute;
			}
			else {
				count++;
			}
		}
		throw new IllegalArgumentException();
	}
