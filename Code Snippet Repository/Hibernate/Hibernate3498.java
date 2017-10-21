	private void endCollectionLoad(
			final Object resultSetId,
			final SharedSessionContractImplementor session,
			final CollectionPersister collectionPersister) {
		//this is a query and we are loading multiple instances of the same collection role
		session.getPersistenceContext()
				.getLoadContexts()
				.getCollectionLoadContext( (ResultSet) resultSetId )
				.endLoadingCollections( collectionPersister );
	}
