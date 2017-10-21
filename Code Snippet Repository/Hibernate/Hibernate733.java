	@SuppressWarnings("")
	protected <T> T jaxb(XMLEventReader reader, Schema xsd, JAXBContext jaxbContext, Origin origin) {
		final ContextProvidingValidationEventHandler handler = new ContextProvidingValidationEventHandler();

		try {
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			if ( isValidationEnabled() ) {
				unmarshaller.setSchema( xsd );
			}
			else {
				unmarshaller.setSchema( null );
			}
			unmarshaller.setEventHandler( handler );

			return (T) unmarshaller.unmarshal( reader );
		}
		catch ( JAXBException e ) {
			throw new MappingException(
					"Unable to perform unmarshalling at line number " + handler.getLineNumber()
							+ " and column " + handler.getColumnNumber()
							+ ". Message: " + handler.getMessage(),
					e,
					origin
			);
		}
	}
