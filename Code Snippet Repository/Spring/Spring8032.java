	@Override
	protected void marshalOutputStream(Object graph, OutputStream outputStream)
			throws XmlMappingException, IOException {
		try {
			IMarshallingContext marshallingContext = createMarshallingContext();
			marshallingContext.startDocument(this.encoding, this.standalone, outputStream);
			marshalDocument(marshallingContext, graph);
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, true);
		}
	}
