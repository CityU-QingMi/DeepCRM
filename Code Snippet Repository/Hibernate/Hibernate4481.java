	protected void collectHints(Map<String, Object> hints) {
		if ( getQueryOptions().getTimeout() != null ) {
			hints.put( HINT_TIMEOUT, getQueryOptions().getTimeout() );
			hints.put( SPEC_HINT_TIMEOUT, getQueryOptions().getTimeout() * 1000 );
		}

		if ( getLockOptions().getTimeOut() != WAIT_FOREVER ) {
			hints.put( JPA_LOCK_TIMEOUT, getLockOptions().getTimeOut() );
		}

		if ( getLockOptions().getScope() ) {
			hints.put( JPA_LOCK_SCOPE, getLockOptions().getScope() );
		}

		if ( getLockOptions().hasAliasSpecificLockModes() && canApplyAliasSpecificLockModeHints() ) {
			for ( Map.Entry<String, LockMode> entry : getLockOptions().getAliasSpecificLocks() ) {
				hints.put(
						ALIAS_SPECIFIC_LOCK_MODE + '.' + entry.getKey(),
						entry.getValue().name()
				);
			}
		}

		putIfNotNull( hints, HINT_COMMENT, getComment() );
		putIfNotNull( hints, HINT_FETCH_SIZE, getQueryOptions().getFetchSize() );
		putIfNotNull( hints, HINT_FLUSH_MODE, getHibernateFlushMode() );

		if ( cacheStoreMode != null || cacheRetrieveMode != null ) {
			putIfNotNull( hints, HINT_CACHE_MODE, CacheModeHelper.interpretCacheMode( cacheStoreMode, cacheRetrieveMode ) );
			putIfNotNull( hints, JPA_SHARED_CACHE_RETRIEVE_MODE, cacheRetrieveMode );
			putIfNotNull( hints, JPA_SHARED_CACHE_STORE_MODE, cacheStoreMode );
		}

		if ( isCacheable() ) {
			hints.put( HINT_CACHEABLE, true );
			putIfNotNull( hints, HINT_CACHE_REGION, getCacheRegion() );
		}

		if ( isReadOnly() ) {
			hints.put( HINT_READONLY, true );
		}

		if ( entityGraphQueryHint != null ) {
			hints.put( entityGraphQueryHint.getHintName(), entityGraphQueryHint.getOriginEntityGraph() );
		}
	}
