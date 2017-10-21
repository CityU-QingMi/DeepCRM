	public <T> T getJaxbRoot(InputStream stream, Class<T> clazz, Schema schema)
			throws XmlParsingException {

		XMLEventReader staxEventReader;
		try {
			staxEventReader = createXmlEventReader( stream );
		}
		catch ( XMLStreamException e ) {
			throw new XmlParsingException( "Unable to create stax reader", e );
		}

		ContextProvidingValidationEventHandler handler = new ContextProvidingValidationEventHandler();
		try {
			staxEventReader = new JpaNamespaceTransformingEventReader( staxEventReader );
			JAXBContext jaxbContext = JAXBContext.newInstance( ObjectFactory.class );
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setSchema( schema );
			unmarshaller.setEventHandler( handler );
			return clazz.cast( unmarshaller.unmarshal( staxEventReader ) );
		}
		catch ( JAXBException e ) {
			StringBuilder builder = new StringBuilder();
			builder.append( "Unable to perform unmarshalling at line number " );
			builder.append( handler.getLineNumber() );
			builder.append( " and column " );
			builder.append( handler.getColumnNumber() );
			builder.append( ". Message: " );
			builder.append( handler.getMessage() );
			throw new XmlParsingException( builder.toString(), e );
		}
	}
