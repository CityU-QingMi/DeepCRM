	@Override
	public QName getName() {
		if (this.event.isStartElement()) {
			return this.event.asStartElement().getName();
		}
		else if (this.event.isEndElement()) {
			return this.event.asEndElement().getName();
		}
		else {
			throw new IllegalStateException();
		}
	}
