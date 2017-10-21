	private void addCollection(String role, String alias, Map propertyResults) {
		SQLLoadableCollection collectionPersister = ( SQLLoadableCollection ) factory.getCollectionPersister( role );
		alias2CollectionPersister.put( alias, collectionPersister );
		String suffix = generateCollectionSuffix();
		LOG.tracev( "Mapping alias [{0}] to collection-suffix [{1}]", alias, suffix );
		alias2CollectionSuffix.put( alias, suffix );
		collectionPropertyResultMaps.put( alias, propertyResults );

		if ( collectionPersister.isOneToMany() || collectionPersister.isManyToMany() ) {
			SQLLoadable persister = ( SQLLoadable ) collectionPersister.getElementPersister();
			addPersister( alias, filter( propertyResults ), persister );
		}
	}
