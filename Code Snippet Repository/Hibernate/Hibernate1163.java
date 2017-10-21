	private List assembleCachedResult(
			final QueryKey key,
			final List cacheable,
			final boolean isNaturalKeyLookup,
			boolean singleResult,
			final Type[] returnTypes,
			final SharedSessionContractImplementor session) throws HibernateException {

		try {
			final List result = new ArrayList( cacheable.size() - 1 );
			if ( singleResult ) {
				for ( int i = 1; i < cacheable.size(); i++ ) {
					result.add( returnTypes[0].assemble( (Serializable) cacheable.get( i ), session, null ) );
				}
			}
			else {
				for ( int i = 1; i < cacheable.size(); i++ ) {
					result.add(
							TypeHelper.assemble( (Serializable[]) cacheable.get( i ), returnTypes, session, null )
					);
					if ( TRACING ) {
						logCachedResultRowDetails( returnTypes, result.get( i - 1 ) );
					}
				}
			}
			return result;
		}
		catch ( RuntimeException ex ) {
			if ( isNaturalKeyLookup ) {
				// potentially perform special handling for natural-id look ups.
				if ( UnresolvableObjectException.class.isInstance( ex )
						|| EntityNotFoundException.class.isInstance( ex ) ) {
					if ( DEBUGGING ) {
						LOG.debug( "Unable to reassemble cached natural-id query result" );
					}
					cacheRegion.evict( key );

					// EARLY EXIT !
					return null;
				}
			}
			throw ex;
		}
	}
