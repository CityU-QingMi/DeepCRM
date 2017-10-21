	@Override
	public void evictNaturalIdRegion(String entityName) {
		EntityPersister p = sessionFactory.getMetamodel().entityPersister( entityName );
		if ( p.hasNaturalIdCache() ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( "Evicting natural-id cache: %s", p.getEntityName() );
			}
			p.getNaturalIdCacheAccessStrategy().evictAll();
		}
	}
