	@Override
	@SuppressWarnings("")
	public int getNamespaceCount() {
		Iterator namespaces;
		if (this.event.isStartElement()) {
			namespaces = this.event.asStartElement().getNamespaces();
		}
		else if (this.event.isEndElement()) {
			namespaces = this.event.asEndElement().getNamespaces();
		}
		else {
			throw new IllegalStateException();
		}
		return countIterator(namespaces);
	}
