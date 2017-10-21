	@SuppressWarnings( { "" })
	private JaxbCfgHibernateConfiguration unmarshal(XMLEventReader staxEventReader, final Origin origin) {
		XMLEvent event;
		try {
			event = staxEventReader.peek();
			while ( event != null && !event.isStartElement() ) {
				staxEventReader.nextEvent();
				event = staxEventReader.peek();
			}
		}
		catch ( Exception e ) {
			throw new HibernateException( "Error accessing stax stream", e );
		}

		if ( event == null ) {
			throw new HibernateException( "Could not locate root element" );
		}

		if ( !isNamespaced( event.asStartElement() ) ) {
			// if the elements are not namespaced, wrap the reader in a reader which will namespace them as pulled.
			log.debug( "cfg.xml document did not define namespaces; wrapping in custom event reader to introduce namespace information" );
			staxEventReader = new NamespaceAddingEventReader( staxEventReader, HIBERNATE_CONFIGURATION_URI );
		}

		final ContextProvidingValidationEventHandler handler = new ContextProvidingValidationEventHandler();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( JaxbCfgHibernateConfiguration.class );
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setSchema( schema() );
			unmarshaller.setEventHandler( handler );
			return (JaxbCfgHibernateConfiguration) unmarshaller.unmarshal( staxEventReader );
		}
		catch ( JAXBException e ) {
			throw new ConfigurationException(
					"Unable to perform unmarshalling at line number " + handler.getLineNumber()
							+ " and column " + handler.getColumnNumber()
							+ " in " + origin.getType().name() + " " + origin.getName()
							+ ". Message: " + handler.getMessage(), e
			);
		}
	}
