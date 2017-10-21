	@Override
	protected Object unmarshalReader(Reader reader) throws XmlMappingException, IOException {
		try {
			IUnmarshallingContext unmarshallingContext = createUnmarshallingContext();
			return unmarshallingContext.unmarshalDocument(reader);
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, false);
		}
	}
