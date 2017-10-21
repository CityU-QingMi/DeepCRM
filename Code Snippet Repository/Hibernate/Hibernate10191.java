	protected void setQueryProperties(Query query) {
		if ( maxResults != null ) {
			query.setMaxResults( maxResults );
		}
		if ( firstResult != null ) {
			query.setFirstResult( firstResult );
		}
		if ( cacheable != null ) {
			query.setCacheable( cacheable );
		}
		if ( cacheRegion != null ) {
			query.setCacheRegion( cacheRegion );
		}
		if ( comment != null ) {
			query.setComment( comment );
		}
		if ( flushMode != null ) {
			query.setFlushMode( flushMode );
		}
		if ( cacheMode != null ) {
			query.setCacheMode( cacheMode );
		}
		if ( timeout != null ) {
			query.setTimeout( timeout );
		}
		if ( lockOptions != null && lockOptions.getLockMode() != LockMode.NONE ) {
			query.setLockMode( REFERENCED_ENTITY_ALIAS, lockOptions.getLockMode() );
		}
	}
