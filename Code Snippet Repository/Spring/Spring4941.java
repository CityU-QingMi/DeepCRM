	@Override
	protected void startElementInternal(QName name, Attributes atts,
			Map<String, String> namespaceMapping) throws XMLStreamException {

		List<Attribute> attributes = getAttributes(atts);
		List<Namespace> namespaces = getNamespaces(namespaceMapping);
		this.eventWriter.add(
				this.eventFactory.createStartElement(name, attributes.iterator(), namespaces.iterator()));

	}
