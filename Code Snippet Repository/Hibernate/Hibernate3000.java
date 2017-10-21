	@Override
	public void evictEntityRegion(String entityName) {
		EntityPersister p = sessionFactory.getMetamodel().entityPersister( entityName );
		if ( p.hasCache() ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( "Evicting second-level cache: %s", p.getEntityName() );
			}
			p.getCacheAccessStrategy().evictAll();
		}
	}
