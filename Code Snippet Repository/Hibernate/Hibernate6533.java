	@Test 
	@RequiresDialectFeature( DialectChecks.SupportsExpectedLobUsagePattern.class )
	public void testPolymorphism() throws Exception {
		Forest forest = new Forest();
		forest.setName( "Fontainebleau" );
		forest.setLength( 33 );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( forest );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Query query = s.createQuery( "from java.lang.Object" );
		assertEquals( 0, query.list().size() );
		query = s.createQuery( "from Forest" );
		assertTrue( 0 < query.list().size() );
		tx.commit();
		s.close();
	}
