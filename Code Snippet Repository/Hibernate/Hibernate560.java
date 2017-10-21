	private boolean cacheUpdate(EntityPersister persister, Object previousVersion, Object ck) {
		final SharedSessionContractImplementor session = getSession();
		try {
			session.getEventListenerManager().cachePutStart();
			return persister.getCacheAccessStrategy().update( session, ck, cacheEntry, nextVersion, previousVersion );
		}
		finally {
			session.getEventListenerManager().cachePutEnd();
		}
	}
