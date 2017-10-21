	@Override
	public Object get(String entityName, Serializable id, LockMode lockMode) {
		checkOpen();

		Object result = getFactory().getMetamodel().entityPersister( entityName )
				.load( id, null, getNullSafeLockMode( lockMode ), this );
		if ( temporaryPersistenceContext.isLoadFinished() ) {
			temporaryPersistenceContext.clear();
		}
		return result;
	}
