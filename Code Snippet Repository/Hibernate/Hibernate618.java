	private static CacheRegionDefinition parseCacheRegionDefinition(Object cacheDeclaration) {
		if ( JaxbCfgEntityCacheType.class.isInstance( cacheDeclaration ) ) {
			final JaxbCfgEntityCacheType jaxbClassCache = (JaxbCfgEntityCacheType) cacheDeclaration;
			return new CacheRegionDefinition(
					CacheRegionDefinition.CacheRegionType.ENTITY,
					jaxbClassCache.getClazz(),
					jaxbClassCache.getUsage().value(),
					jaxbClassCache.getRegion(),
					"all".equals( jaxbClassCache.getInclude() )
			);
		}
		else {
			final JaxbCfgCollectionCacheType jaxbCollectionCache = (JaxbCfgCollectionCacheType) cacheDeclaration;
			return new CacheRegionDefinition(
					CacheRegionDefinition.CacheRegionType.COLLECTION,
					jaxbCollectionCache.getCollection(),
					jaxbCollectionCache.getUsage().value(),
					jaxbCollectionCache.getRegion(),
					false
			);
		}
	}
