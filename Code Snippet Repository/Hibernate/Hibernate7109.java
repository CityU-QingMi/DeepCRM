	@Test
	@SuppressWarnings( {""})
	public void testBatchFetch2() {
		Session s = openSession();
		s.beginTransaction();
		int size = 32+14;
		for ( int i = 0; i < size; i++ ) {
			s.save( new BatchLoadableEntity( i ) );
		}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		// load them all as proxies
		for ( int i = 0; i < size; i++ ) {
			BatchLoadableEntity entity = (BatchLoadableEntity) s.load( BatchLoadableEntity.class, i );
			assertFalse( Hibernate.isInitialized( entity ) );
		}
		sessionFactory().getStatistics().clear();
		// now start initializing them...
		for ( int i = 0; i < size; i++ ) {
			BatchLoadableEntity entity = (BatchLoadableEntity) s.load( BatchLoadableEntity.class, i );
			Hibernate.initialize( entity );
			assertTrue( Hibernate.isInitialized( entity ) );
		}
		// so at this point, all entities are initialized.  see how many fetches were performed.
		final int expectedFetchCount;
		if ( sessionFactory().getSettings().getBatchFetchStyle() == BatchFetchStyle.LEGACY ) {
			expectedFetchCount = 3; // (32 + 10 + 4)
		}
		else if ( sessionFactory().getSettings().getBatchFetchStyle() == BatchFetchStyle.DYNAMIC ) {
			expectedFetchCount = 2;  // (32 + 14) : because we limited batch-size to 32
		}
		else {
			// PADDED
			expectedFetchCount = 2; // (32 + 16*) with the 16 being padded
		}
		assertEquals( expectedFetchCount, sessionFactory().getStatistics().getEntityStatistics( BatchLoadableEntity.class.getName() ).getFetchCount() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete BatchLoadableEntity" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
