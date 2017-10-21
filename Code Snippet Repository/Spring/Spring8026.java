	@Override
	protected Object unmarshalXmlStreamReader(XMLStreamReader streamReader) {
		try {
			UnmarshallingContext unmarshallingContext = (UnmarshallingContext) createUnmarshallingContext();
			IXMLReader xmlReader = new StAXReaderWrapper(streamReader, null, true);
			unmarshallingContext.setDocument(xmlReader);
			return unmarshallingContext.unmarshalElement();
		}
		catch (JiBXException ex) {
			throw convertJibxException(ex, false);
		}
	}
