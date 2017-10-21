	@Test
	public void testRetrievalOfCachedCollectionWithPropertyRefKey() {
		// create the test data...
		Session session = openSession();
		session.beginTransaction();
		ManagedObject mo = new ManagedObject( "test", "test" );
		mo.getMembers().add( "members" );
		session.save( mo );
		session.getTransaction().commit();
		session.close();

		// First attempt to load it via PK lookup
		session = openSession();
		session.beginTransaction();
		ManagedObject obj = (ManagedObject) session.get( ManagedObject.class, 1L );
		assertNotNull( obj );
		assertTrue( Hibernate.isInitialized( obj ) );
		obj.getMembers().size();
		assertTrue( Hibernate.isInitialized( obj.getMembers() ) );
		session.getTransaction().commit();
		session.close();

		// Now try to access it via natural key
		session = openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria( ManagedObject.class )
				.add( Restrictions.naturalId().set( "name", "test" ) )
				.setCacheable( true )
				.setFetchMode( "members", FetchMode.JOIN );
		obj = (ManagedObject) criteria.uniqueResult();
		assertNotNull( obj );
		assertTrue( Hibernate.isInitialized( obj ) );
		obj.getMembers().size();
		assertTrue( Hibernate.isInitialized( obj.getMembers() ) );
		session.getTransaction().commit();
		session.close();

		// Clean up
		session = openSession();
		session.beginTransaction();
		session.delete( obj );
		session.getTransaction().commit();
		session.close();
	}
