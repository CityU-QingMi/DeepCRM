	@Override
	protected void marshalXmlStreamWriter(Object graph, XMLStreamWriter streamWriter) throws XmlMappingException {
		try {
			MarshallingContext marshallingContext = (MarshallingContext) createMarshallingContext();
			IXMLWriter xmlWriter = new StAXWriter(marshallingContext.getNamespaces(), streamWriter);
			marshallingContext.setXmlWriter(xmlWriter);
			marshallingContext.marshalDocument(graph);
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, false);
		}
	}
