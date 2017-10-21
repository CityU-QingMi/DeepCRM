	public static Serializable fromSharedCache(
			SharedSessionContractImplementor session,
			Object cacheKey,
			RegionAccessStrategy cacheAccessStrategy) {
		final SessionEventListenerManager eventListenerManager = session.getEventListenerManager();
		Serializable cachedValue = null;
		eventListenerManager.cacheGetStart();
		try {
			cachedValue = (Serializable) cacheAccessStrategy.get( session, cacheKey, session.getTimestamp() );
		}
		finally {
			eventListenerManager.cacheGetEnd( cachedValue != null );
		}
		return cachedValue;
	}
