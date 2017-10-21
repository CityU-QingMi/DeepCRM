	private List getCachedResults(QueryKey key, SharedSessionContractImplementor session) {
		List cacheable = null;
		try {
			session.getEventListenerManager().cacheGetStart();
			cacheable = (List) cacheRegion.get( session, key );
		}
		finally {
			session.getEventListenerManager().cacheGetEnd( cacheable != null );
		}
		return cacheable;
	}
