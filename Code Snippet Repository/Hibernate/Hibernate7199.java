	@Test
	public void testCachedValueAfterEviction() {
		CollectionPersister persister = sessionFactory().getCollectionPersister( Company.class.getName() + ".users" );

		Session session = openSession();
		SessionImplementor sessionImplementor = (SessionImplementor) session;

		CollectionRegionAccessStrategy cache = persister.getCacheAccessStrategy();
		Object key = cache.generateCacheKey( 1, persister, sessionFactory(), session.getTenantIdentifier() );
		Object cachedValue = cache.get( sessionImplementor, key, sessionImplementor.getTimestamp() );
		assertNull( cachedValue );

		Company company = session.get( Company.class, 1 );
		//should add in cache
		assertEquals( 1, company.getUsers().size() );
		session.close();

		session = openSession();
		sessionImplementor = (SessionImplementor) session;
		key = cache.generateCacheKey( 1, persister, sessionFactory(), session.getTenantIdentifier() );
		cachedValue = cache.get( sessionImplementor, key, sessionImplementor.getTimestamp() );
		assertNotNull( "Collection wasn't cached", cachedValue );
		session.close();
	}
