	public void removeSessionFactory(
			String uuid,
			String name,
			boolean isNameAlsoJndiName,
			JndiService jndiService) {
		if ( name != null ) {
			nameUuidXref.remove( name );

			if ( isNameAlsoJndiName ) {
				try {
					LOG.tracef( "Unbinding SessionFactory from JNDI : %s", name );
					jndiService.unbind( name );
					LOG.factoryUnboundFromJndiName( name );
				}
				catch (JndiNameException e) {
					LOG.invalidJndiName( name, e );
				}
				catch (JndiException e) {
					LOG.unableToUnbindFactoryFromJndi( e );
				}
			}
		}

		sessionFactoryMap.remove( uuid );
	}
