	@Override
	protected void startElementInternal(QName name, Attributes attributes,
			Map<String, String> namespaceMapping) throws XMLStreamException {

		this.streamWriter.writeStartElement(name.getPrefix(), name.getLocalPart(), name.getNamespaceURI());

		for (Map.Entry<String, String> entry : namespaceMapping.entrySet()) {
			String prefix = entry.getKey();
			String namespaceUri = entry.getValue();
			this.streamWriter.writeNamespace(prefix, namespaceUri);
			if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
				this.streamWriter.setDefaultNamespace(namespaceUri);
			}
			else {
				this.streamWriter.setPrefix(prefix, namespaceUri);
			}
		}
		for (int i = 0; i < attributes.getLength(); i++) {
			QName attrName = toQName(attributes.getURI(i), attributes.getQName(i));
			if (!isNamespaceDeclaration(attrName)) {
				this.streamWriter.writeAttribute(attrName.getPrefix(), attrName.getNamespaceURI(),
						attrName.getLocalPart(), attributes.getValue(i));
			}
		}
	}
