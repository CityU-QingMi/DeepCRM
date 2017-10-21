	private void initializeEntitiesAndCollections(
			final List hydratedObjects,
			final Object resultSetId,
			final SharedSessionContractImplementor session,
			final boolean readOnly) throws HibernateException {
		initializeEntitiesAndCollections(
				hydratedObjects,
				resultSetId,
				session,
				readOnly,
				Collections.emptyList()
		);
	}
