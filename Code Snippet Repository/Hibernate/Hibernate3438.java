	public static LoadState isLoadedWithReference(Object entity, String attributeName, MetadataCache cache) {
		if ( entity instanceof HibernateProxy ) {
			final LazyInitializer li = ( (HibernateProxy) entity ).getHibernateLazyInitializer();
			if ( li.isUninitialized() ) {
				// we have an uninitialized proxy, the attribute cannot be loaded
				return LoadState.NOT_LOADED;
			}
			else {
				// swap the proxy with target (for proper class name resolution)
				entity = li.getImplementation();
			}
		}

		try {
			final Class entityClass = entity.getClass();
			final Object attributeValue = cache.getClassMetadata( entityClass )
					.getAttributeAccess( attributeName )
					.extractValue( entity );
			return isLoaded( attributeValue );
		}
		catch (AttributeExtractionException ignore) {
			return LoadState.UNKNOWN;
		}
	}
