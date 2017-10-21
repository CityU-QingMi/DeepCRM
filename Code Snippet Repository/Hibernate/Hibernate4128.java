	private Object initializeLazyPropertiesFromCache(
			final String fieldName,
			final Object entity,
			final SharedSessionContractImplementor session,
			final EntityEntry entry,
			final CacheEntry cacheEntry) {

		LOG.trace( "Initializing lazy properties from second-level cache" );

		Object result = null;
		Serializable[] disassembledValues = cacheEntry.getDisassembledState();
		final Object[] snapshot = entry.getLoadedState();
		for ( int j = 0; j < lazyPropertyNames.length; j++ ) {
			final Object propValue = lazyPropertyTypes[j].assemble(
					disassembledValues[lazyPropertyNumbers[j]],
					session,
					entity
			);
			if ( initializeLazyProperty( fieldName, entity, session, snapshot, j, propValue ) ) {
				result = propValue;
			}
		}

		LOG.trace( "Done initializing lazy properties" );

		return result;
	}
