	@Override
	protected void marshalWriter(Object graph, Writer writer) throws XmlMappingException, IOException {
		try {
			IMarshallingContext marshallingContext = createMarshallingContext();
			marshallingContext.startDocument(this.encoding, this.standalone, writer);
			marshalDocument(marshallingContext, graph);
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, true);
		}
	}
