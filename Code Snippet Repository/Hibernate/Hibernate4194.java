	@Override
	@SuppressWarnings( {""})
	public CollectionPersister createCollectionPersister(
			Collection collectionBinding,
			CollectionRegionAccessStrategy cacheAccessStrategy,
			PersisterCreationContext creationContext) throws HibernateException {
		// If the metadata for the collection specified an explicit persister class, use it
		Class<? extends CollectionPersister> persisterClass = collectionBinding.getCollectionPersisterClass();
		if ( persisterClass == null ) {
			// Otherwise, use the persister class indicated by the PersisterClassResolver service
			persisterClass = serviceRegistry.getService( PersisterClassResolver.class )
					.getCollectionPersisterClass( collectionBinding );
		}
		return createCollectionPersister( persisterClass, collectionBinding, cacheAccessStrategy, creationContext );
	}
