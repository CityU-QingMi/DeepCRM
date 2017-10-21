	@Override
	protected void marshalDomNode(Object graph, Node node) throws XmlMappingException {
		HierarchicalStreamWriter streamWriter;
		if (node instanceof Document) {
			streamWriter = new DomWriter((Document) node, this.nameCoder);
		}
		else if (node instanceof Element) {
			streamWriter = new DomWriter((Element) node, node.getOwnerDocument(), this.nameCoder);
		}
		else {
			throw new IllegalArgumentException("DOMResult contains neither Document nor Element");
		}
		doMarshal(graph, streamWriter, null);
	}
