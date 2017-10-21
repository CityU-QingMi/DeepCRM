	@Test
	public void testLazyCollectionLoadingWithClearedSession() {
		sessionFactory().getStatistics().clear();

		Session s = openSession();
		s.beginTransaction();
		// first load the store, making sure collection is not initialized
		Store store = (Store) s.get( Store.class, 1 );
		assertNotNull( store );
		assertFalse( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 0, sessionFactory().getStatistics().getSessionCloseCount() );

		// then clear session and try to initialize collection
		s.clear();
		store.getInventories().size();
		assertTrue( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 2, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 1, sessionFactory().getStatistics().getSessionCloseCount() );

		s.clear();
		store = (Store) s.get( Store.class, 1 );
		assertNotNull( store );
		assertFalse( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 2, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 1, sessionFactory().getStatistics().getSessionCloseCount() );

		s.clear();
		store.getInventories().iterator();
		assertTrue( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 3, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 2, sessionFactory().getStatistics().getSessionCloseCount() );

		s.getTransaction().commit();
		s.close();
	}
