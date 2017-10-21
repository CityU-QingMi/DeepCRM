	@SuppressWarnings("")
	private Namespace getNamespace(int index) {
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
		int count = 0;
		while (namespaces.hasNext()) {
			Namespace namespace = (Namespace) namespaces.next();
			if (count == index) {
				return namespace;
			}
			else {
				count++;
			}
		}
		throw new IllegalArgumentException();
	}
