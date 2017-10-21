	private Object getIdentifierFromPersister(Object entity) {
		Class<?> entityClass = Hibernate.getClass( entity );
		final EntityPersister persister;
		try {
			persister = sessionFactory.getMetamodel().entityPersister( entityClass );
			if ( persister == null ) {
				throw new IllegalArgumentException( entityClass.getName() + " is not an entity" );
			}
		}
		catch (MappingException ex) {
			throw new IllegalArgumentException( entityClass.getName() + " is not an entity", ex );
		}
		return persister.getIdentifier( entity, null );
	}
