	public String toString(String entityName, Object entity) throws HibernateException {
		EntityPersister entityPersister = factory.getEntityPersister( entityName );

		if ( entityPersister == null ) {
			return entity.getClass().getName();
		}

		Map<String, String> result = new HashMap<String, String>();

		if ( entityPersister.hasIdentifierProperty() ) {
			result.put(
					entityPersister.getIdentifierPropertyName(),
					entityPersister.getIdentifierType().toLoggableString(
							entityPersister.getIdentifier( entity ),
							factory
					)
			);
		}

		Type[] types = entityPersister.getPropertyTypes();
		String[] names = entityPersister.getPropertyNames();
		Object[] values = entityPersister.getPropertyValues( entity );
		for ( int i = 0; i < types.length; i++ ) {
			if ( !names[i].startsWith( "_" ) ) {
				String strValue = values[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY ?
						values[i].toString() :
						types[i].toLoggableString( values[i], factory );
				result.put( names[i], strValue );
			}
		}
		return entityName + result.toString();
	}
