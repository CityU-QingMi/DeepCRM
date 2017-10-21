	@Override
	@SuppressWarnings({ "" })
	public List get(
			final QueryKey key,
			final Type[] returnTypes,
			final boolean isNaturalKeyLookup,
			final Set<Serializable> spaces,
			final SharedSessionContractImplementor session) throws HibernateException {
		if ( DEBUGGING ) {
			LOG.debugf( "Checking cached query results in region: %s", cacheRegion.getName() );
		}

		final List cacheable = getCachedResults( key, session );
		if ( TRACING ) {
			logCachedResultDetails( key, spaces, returnTypes, cacheable );
		}
		if ( cacheable == null ) {
			if ( DEBUGGING ) {
				LOG.debug( "Query results were not found in cache" );
			}
			return null;
		}

		final Long timestamp = (Long) cacheable.get( 0 );
		if ( !isNaturalKeyLookup && !isUpToDate( spaces, timestamp, session ) ) {
			if ( DEBUGGING ) {
				LOG.debug( "Cached query results were not up-to-date" );
			}
			return null;
		}

		if ( DEBUGGING ) {
			LOG.debug( "Returning cached query results" );
		}
		final boolean singleResult = returnTypes.length == 1;
		for ( int i = 1; i < cacheable.size(); i++ ) {
			if ( singleResult ) {
				returnTypes[0].beforeAssemble( (Serializable) cacheable.get( i ), session );
			}
			else {
				TypeHelper.beforeAssemble( (Serializable[]) cacheable.get( i ), returnTypes, session );
			}
		}

		return assembleCachedResult(key, cacheable, isNaturalKeyLookup, singleResult, returnTypes, session);
	}
