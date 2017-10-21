	protected void stopCacheRegions() {
		log.debug( "Clear region references" );
		getCacheCommandFactory().clearRegions( regions );
		// Ensure we cleanup any caches we created
		regions.forEach( region -> {
			region.getCache().stop();
			manager.undefineConfiguration( region.getCache().getName() );
		} );
		regions.clear();
	}
