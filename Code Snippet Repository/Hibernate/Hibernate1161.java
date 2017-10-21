	@Override
	@SuppressWarnings({ "" })
	public boolean put(
			final QueryKey key,
			final Type[] returnTypes,
			final List result,
			final boolean isNaturalKeyLookup,
			final SharedSessionContractImplementor session) throws HibernateException {
		if ( isNaturalKeyLookup && result.isEmpty() ) {
			return false;
		}
		if ( DEBUGGING ) {
			LOG.debugf( "Caching query results in region: %s; timestamp=%s", cacheRegion.getName(), session.getTimestamp() );
		}

		final List cacheable = new ArrayList( result.size() + 1 );
		if ( TRACING ) {
			logCachedResultDetails( key, null, returnTypes, cacheable );
		}
		cacheable.add( session.getTimestamp() );

		final boolean isSingleResult = returnTypes.length == 1;
		for ( Object aResult : result ) {
			final Serializable cacheItem = isSingleResult
					? returnTypes[0].disassemble( aResult, session, null )
					: TypeHelper.disassemble( (Object[]) aResult, returnTypes, null, session, null );
			cacheable.add( cacheItem );
			if ( TRACING ) {
				logCachedResultRowDetails( returnTypes, aResult );
			}
		}

		try {
			session.getEventListenerManager().cachePutStart();
			cacheRegion.put( session, key, cacheable );
		}
		finally {
			session.getEventListenerManager().cachePutEnd();
		}

		return true;
	}
