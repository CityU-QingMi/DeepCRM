	@Test
	public void testLazyEntityLoadingWithClosedSession() {
		sessionFactory().getStatistics().clear();

		Session s = openSession();
		s.beginTransaction();
		// first load the store, making sure it is not initialized
		Store store = (Store) s.load( Store.class, 1 );
		assertNotNull( store );
		assertFalse( Hibernate.isInitialized( store ) );

		assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 0, sessionFactory().getStatistics().getSessionCloseCount() );

		// close the session and try to initialize store
		s.getTransaction().commit();
		s.close();

		assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 1, sessionFactory().getStatistics().getSessionCloseCount() );

		store.getName();
		assertTrue( Hibernate.isInitialized( store ) );

		assertEquals( 2, sessionFactory().getStatistics().getSessionOpenCount() );
		assertEquals( 2, sessionFactory().getStatistics().getSessionCloseCount() );
	}
