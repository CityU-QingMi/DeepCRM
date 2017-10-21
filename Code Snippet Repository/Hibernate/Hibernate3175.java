	@Override
	public void refresh(String entityName, Object entity, LockMode lockMode) {
		final EntityPersister persister = this.getEntityPersister( entityName, entity );
		final Serializable id = persister.getIdentifier( entity, this );
		if ( LOG.isTraceEnabled() ) {
			LOG.tracev( "Refreshing transient {0}", MessageHelper.infoString( persister, id, this.getFactory() ) );
		}
		// TODO : can this ever happen???
//		EntityKey key = new EntityKey( id, persister, source.getEntityMode() );
//		if ( source.getPersistenceContext().getEntry( key ) != null ) {
//			throw new PersistentObjectException(
//					"attempted to refresh transient instance when persistent " +
//					"instance was already associated with the Session: " +
//					MessageHelper.infoString( persister, id, source.getFactory() )
//			);
//		}

		if ( persister.hasCache() ) {
			final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
			final Object ck = cache.generateCacheKey( id, persister, getFactory(), getTenantIdentifier() );
			cache.evict( ck );
		}
		String previousFetchProfile = this.getLoadQueryInfluencers().getInternalFetchProfile();
		Object result = null;
		try {
			this.getLoadQueryInfluencers().setInternalFetchProfile( "refresh" );
			result = persister.load( id, entity, getNullSafeLockMode( lockMode ), this );
		}
		finally {
			this.getLoadQueryInfluencers().setInternalFetchProfile( previousFetchProfile );
		}
		UnresolvableObjectException.throwIfNull( result, id, persister.getEntityName() );
	}
