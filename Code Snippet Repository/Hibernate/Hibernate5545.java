	@Test
	public void testEntityManagerCacheModes() {

		EntityManager em;
		Session session;

		em = getOrCreateEntityManager();
		session = ( (HibernateEntityManager) em ).getSession();

		// defaults...
		assertEquals( CacheStoreMode.USE, em.getProperties().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheRetrieveMode.USE, em.getProperties().get( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE ) );
		assertEquals( CacheMode.NORMAL, session.getCacheMode() );

		// overrides...
		em.setProperty( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.REFRESH );
		assertEquals( CacheStoreMode.REFRESH, em.getProperties().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.REFRESH, session.getCacheMode() );

		em.setProperty( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.BYPASS );
		assertEquals( CacheStoreMode.BYPASS, em.getProperties().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.GET, session.getCacheMode() );

		em.setProperty( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS );
		assertEquals( CacheRetrieveMode.BYPASS, em.getProperties().get( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE ) );
		assertEquals( CacheMode.IGNORE, session.getCacheMode() );

		em.setProperty( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.USE );
		assertEquals( CacheStoreMode.USE, em.getProperties().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.PUT, session.getCacheMode() );

		em.setProperty( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.REFRESH );
		assertEquals( CacheStoreMode.REFRESH, em.getProperties().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.REFRESH, session.getCacheMode() );
	}
