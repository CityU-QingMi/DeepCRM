	private void processCachingOverrides() {
		if ( options.getCacheRegionDefinitions() == null ) {
			return;
		}

		for ( CacheRegionDefinition cacheRegionDefinition : options.getCacheRegionDefinitions() ) {
			if ( cacheRegionDefinition.getRegionType() == CacheRegionDefinition.CacheRegionType.ENTITY ) {
				final PersistentClass entityBinding = getEntityBinding( cacheRegionDefinition.getRole() );
				if ( entityBinding == null ) {
					throw new HibernateException(
							"Cache override referenced an unknown entity : " + cacheRegionDefinition.getRole()
					);
				}
				if ( !RootClass.class.isInstance( entityBinding ) ) {
					throw new HibernateException(
							"Cache override referenced a non-root entity : " + cacheRegionDefinition.getRole()
					);
				}
				( (RootClass) entityBinding ).setCacheRegionName( cacheRegionDefinition.getRegion() );
				( (RootClass) entityBinding ).setCacheConcurrencyStrategy( cacheRegionDefinition.getUsage() );
				( (RootClass) entityBinding ).setLazyPropertiesCacheable( cacheRegionDefinition.isCacheLazy() );
			}
			else if ( cacheRegionDefinition.getRegionType() == CacheRegionDefinition.CacheRegionType.COLLECTION ) {
				final Collection collectionBinding = getCollectionBinding( cacheRegionDefinition.getRole() );
				if ( collectionBinding == null ) {
					throw new HibernateException(
							"Cache override referenced an unknown collection role : " + cacheRegionDefinition.getRole()
					);
				}
				collectionBinding.setCacheRegionName( cacheRegionDefinition.getRegion() );
				collectionBinding.setCacheConcurrencyStrategy( cacheRegionDefinition.getUsage() );
			}
		}
	}
