	@Override
	protected Object unmarshalDomNode(Node node) throws XmlMappingException {
		HierarchicalStreamReader streamReader;
		if (node instanceof Document) {
			streamReader = new DomReader((Document) node, this.nameCoder);
		}
		else if (node instanceof Element) {
			streamReader = new DomReader((Element) node, this.nameCoder);
		}
		else {
			throw new IllegalArgumentException("DOMSource contains neither Document nor Element");
		}
        return doUnmarshal(streamReader, null);
	}
