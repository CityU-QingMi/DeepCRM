	public OneToManyPersister(
			Collection collectionBinding,
			CollectionRegionAccessStrategy cacheAccessStrategy,
			PersisterCreationContext creationContext) throws MappingException, CacheException {
		super( collectionBinding, cacheAccessStrategy, creationContext );
		cascadeDeleteEnabled = collectionBinding.getKey().isCascadeDeleteEnabled()
				&& creationContext.getSessionFactory().getDialect().supportsCascadeDelete();
		keyIsNullable = collectionBinding.getKey().isNullable();
		keyIsUpdateable = collectionBinding.getKey().isUpdateable();
	}
