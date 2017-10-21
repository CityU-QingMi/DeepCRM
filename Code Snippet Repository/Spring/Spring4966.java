	@SuppressWarnings("")
	private void doWriteNamespace(Namespace namespace) throws XMLStreamException {
		int last = this.endElements.size() - 1;
		EndElement oldEndElement = this.endElements.get(last);
		Iterator oldNamespaces = oldEndElement.getNamespaces();
		List<Namespace> newNamespaces = new ArrayList<>();
		while (oldNamespaces.hasNext()) {
			Namespace oldNamespace = (Namespace) oldNamespaces.next();
			newNamespaces.add(oldNamespace);
		}
		newNamespaces.add(namespace);
		EndElement newEndElement = this.eventFactory.createEndElement(oldEndElement.getName(), newNamespaces.iterator());
		this.eventWriter.add(namespace);
		this.endElements.set(last, newEndElement);
	}
