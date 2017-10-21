	@Override
	protected Object unmarshalInputStream(InputStream inputStream) throws XmlMappingException, IOException {
		try {
			IUnmarshallingContext unmarshallingContext = createUnmarshallingContext();
			return unmarshallingContext.unmarshalDocument(inputStream, encoding);
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, false);
		}
	}
