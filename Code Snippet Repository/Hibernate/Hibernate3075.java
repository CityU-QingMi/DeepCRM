	public void addSessionFactory(
			String uuid,
			String name,
			boolean isNameAlsoJndiName,
			SessionFactory instance,
			JndiService jndiService) {
		if ( uuid == null ) {
			throw new IllegalArgumentException( "SessionFactory UUID cannot be null" );
		}

		LOG.debugf( "Registering SessionFactory: %s (%s)", uuid, name == null ? "<unnamed>" : name );
		sessionFactoryMap.put( uuid, instance );
		if ( name != null ) {
			nameUuidXref.put( name, uuid );
		}

		if ( name == null || !isNameAlsoJndiName ) {
			LOG.debug( "Not binding SessionFactory to JNDI, no JNDI name configured" );
			return;
		}

		LOG.debugf( "Attempting to bind SessionFactory [%s] to JNDI", name );

		try {
			jndiService.bind( name, instance );
			LOG.factoryBoundToJndiName( name );
			try {
				jndiService.addListener( name, listener );
			}
			catch (Exception e) {
				LOG.couldNotBindJndiListener();
			}
		}
		catch (JndiNameException e) {
			LOG.invalidJndiName( name, e );
		}
		catch (JndiException e) {
			LOG.unableToBindFactoryToJndi( e );
		}
	}
