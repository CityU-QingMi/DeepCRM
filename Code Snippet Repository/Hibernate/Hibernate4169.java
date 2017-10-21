	public Object nullSafeGet(
			ResultSet rs,
			String name,
			SharedSessionContractImplementor session,
			Object owner) throws HibernateException, SQLException {
		final Object discriminatorValue = underlyingType.nullSafeGet( rs, name, session, owner );
		final String entityName = persister.getSubclassForDiscriminatorValue( discriminatorValue );
		if ( entityName == null ) {
			throw new HibernateException( "Unable to resolve discriminator value [" + discriminatorValue + "] to entity name" );
		}
		final EntityPersister entityPersister = session.getEntityPersister( entityName, null );
		return ( EntityMode.POJO == entityPersister.getEntityMode() ) ? entityPersister.getMappedClass() : entityName;
	}
