	@Override
	public EntityPersister getEntityPersister(String entityName, Object object)
			throws HibernateException {
		checkOpen();
		if ( entityName == null ) {
			return getFactory().getMetamodel().entityPersister( guessEntityName( object ) );
		}
		else {
			return getFactory().getMetamodel().entityPersister( entityName ).getSubclassEntityPersister( object, getFactory() );
		}
	}
