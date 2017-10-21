	private boolean isCached(Serializable id, Class<?> entityClass, String attr) {
		Session session = openSession();
		CollectionPersister persister = sessionFactory().getCollectionPersister( entityClass.getName() + "." + attr );
		CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
		Object key = cache.generateCacheKey( id, persister, sessionFactory(), session.getTenantIdentifier() );
		Object cachedValue = cache.get(
				( (SessionImplementor) session ),
				key,
				( (SessionImplementor) session ).getTimestamp()
		);
		session.close();
		return cachedValue != null;
	}
