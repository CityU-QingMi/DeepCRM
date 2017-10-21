	@Override
	protected Object unmarshalSaxReader(XMLReader xmlReader, InputSource inputSource)
			throws XmlMappingException, IOException {

		UnmarshalHandler unmarshalHandler = createUnmarshaller().createHandler();
		try {
			ContentHandler contentHandler = Unmarshaller.getContentHandler(unmarshalHandler);
			xmlReader.setContentHandler(contentHandler);
			xmlReader.parse(inputSource);
			return unmarshalHandler.getObject();
		}
		catch (SAXException ex) {
			throw new UnmarshallingFailureException("SAX reader exception", ex);
		}
	}
