	@Override
	protected Binding doBind(
			XMLEventReader staxEventReader,
			StartElement rootElementStartEvent,
			Origin origin) {
		final String rootElementLocalName = rootElementStartEvent.getName().getLocalPart();
		if ( "hibernate-mapping".equals( rootElementLocalName ) ) {
			log.debugf( "Performing JAXB binding of hbm.xml document : %s", origin.toString() );

			XMLEventReader hbmReader = new HbmEventReader( staxEventReader, xmlEventFactory );
			JaxbHbmHibernateMapping hbmBindings = jaxb( hbmReader, LocalSchema.HBM.getSchema(), hbmJaxbContext(), origin );
			return new Binding<JaxbHbmHibernateMapping>( hbmBindings, origin );
		}
		else {
//			final XMLEventReader reader = new JpaOrmXmlEventReader( staxEventReader );
//			return jaxb( reader, LocalSchema.MAPPING.getSchema(), JaxbEntityMappings.class, origin );

			try {
				final XMLEventReader reader = new JpaOrmXmlEventReader( staxEventReader, xmlEventFactory );
				return new Binding<Document>( toDom4jDocument( reader, origin), origin );
			}
			catch (JpaOrmXmlEventReader.BadVersionException e) {
				throw new UnsupportedOrmXsdVersionException( e.getRequestedVersion(), origin );
			}
		}
	}
