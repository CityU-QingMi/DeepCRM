	@Override
	public void evictCollectionRegion(String role) {
		CollectionPersister p = sessionFactory.getMetamodel().collectionPersister( role );
		if ( p.hasCache() ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( "Evicting second-level cache: %s", p.getRole() );
			}
			p.getCacheAccessStrategy().evictAll();
		}
	}
