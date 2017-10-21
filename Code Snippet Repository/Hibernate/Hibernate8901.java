	@Test
	public void testLazyCollectionLoadingWithClosedSession() {
		sessionFactory().getStatistics().clear();

		Session s = openSession();
		s.beginTransaction();
		// first load the store, making sure collection is not initialized
		Store store = (Store) s.get( Store.class, 1 );
		assertNotNull( store );
		assertFalse( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 0, sessionFactory().getStatistics().getSessionCloseCount() );

		// close the session and try to initialize collection
		s.getTransaction().commit();
		s.close();

		assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 1, sessionFactory().getStatistics().getSessionCloseCount() );

		store.getInventories().size();
		assertTrue( Hibernate.isInitialized( store.getInventories() ) );

		assertEquals( 2, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 2, sessionFactory().getStatistics().getSessionCloseCount() );
	}
