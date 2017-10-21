	private static Cache determineCacheSettings(XClass clazzToProcess, MetadataBuildingContext context) {
		Cache cacheAnn = clazzToProcess.getAnnotation( Cache.class );
		if ( cacheAnn != null ) {
			return cacheAnn;
		}

		Cacheable cacheableAnn = clazzToProcess.getAnnotation( Cacheable.class );
		SharedCacheMode mode = determineSharedCacheMode( context );
		switch ( mode ) {
			case ALL: {
				cacheAnn = buildCacheMock( clazzToProcess.getName(), context );
				break;
			}
			case ENABLE_SELECTIVE: {
				if ( cacheableAnn != null && cacheableAnn.value() ) {
					cacheAnn = buildCacheMock( clazzToProcess.getName(), context );
				}
				break;
			}
			case DISABLE_SELECTIVE: {
				if ( cacheableAnn == null || cacheableAnn.value() ) {
					cacheAnn = buildCacheMock( clazzToProcess.getName(), context );
				}
				break;
			}
			default: {
				// treat both NONE and UNSPECIFIED the same
				break;
			}
		}
		return cacheAnn;
	}
