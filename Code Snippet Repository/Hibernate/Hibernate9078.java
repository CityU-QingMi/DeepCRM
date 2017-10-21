	@SuppressWarnings( {""})
	@Test
	public void testProxyEviction() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Container container = new Container( "container" );
		container.setOwner( new Owner( "owner" ) );
		container.setInfo( new Info( "blah blah blah" ) );
		container.getDataPoints().add( new DataPoint( new BigDecimal( 1 ), new BigDecimal( 1 ), "first data point" ) );
		container.getDataPoints().add( new DataPoint( new BigDecimal( 2 ), new BigDecimal( 2 ), "second data point" ) );
		s.save( container );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Container c = ( Container ) s.load( Container.class, container.getId() );
		assertFalse( Hibernate.isInitialized( c ) );
		s.evict( c );
		try {
			c.getName();
			fail( "expecting LazyInitializationException" );
		}
		catch( LazyInitializationException e ) {
			// expected result
		}

		c = ( Container ) s.load( Container.class, container.getId() );
		assertFalse( Hibernate.isInitialized( c ) );
		Info i = c.getInfo();
		assertTrue( Hibernate.isInitialized( c ) );
		assertFalse( Hibernate.isInitialized( i ) );
		s.evict( c );
		try {
			i.getDetails();
			fail( "expecting LazyInitializationException" );
		}
		catch( LazyInitializationException e ) {
			// expected result
		}

		s.delete( c );

		t.commit();
		s.close();
	}
