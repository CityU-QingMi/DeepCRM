	@Override
	protected Object unmarshalXmlEventReader(XMLEventReader eventReader) {
		try {
			XMLStreamReader streamReader = StaxUtils.createEventStreamReader(eventReader);
			return unmarshalXmlStreamReader(streamReader);
		}
		catch (XMLStreamException ex) {
			return new UnmarshallingFailureException("JiBX unmarshalling exception", ex);
		}
	}
