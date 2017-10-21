	private boolean cacheInsert(EntityPersister persister, Object ck) {
		SharedSessionContractImplementor session = getSession();
		try {
			session.getEventListenerManager().cachePutStart();
			return persister.getCacheAccessStrategy().insert( session, ck, cacheEntry, version);
		}
		finally {
			session.getEventListenerManager().cachePutEnd();
		}
	}
