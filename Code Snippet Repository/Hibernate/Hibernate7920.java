	@Test
	public void testCollectionFetchWithDistinctionAndLimit() {
		// create some test data...
		Session s = openSession();
		Transaction t = s.beginTransaction();
		int parentCount = 30;
		for ( int i = 0; i < parentCount; i++ ) {
			Animal child1 = new Animal();
			child1.setDescription( "collection fetch distinction (child1 - parent" + i + ")" );
			s.persist( child1 );
			Animal child2 = new Animal();
			child2.setDescription( "collection fetch distinction (child2 - parent " + i + ")" );
			s.persist( child2 );
			Animal parent = new Animal();
			parent.setDescription( "collection fetch distinction (parent" + i + ")" );
			parent.setSerialNumber( "123-" + i );
			parent.addOffspring( child1 );
			parent.addOffspring( child2 );
			s.persist( parent );
		}
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		// Test simple distinction
		List results;
		results = s.createQuery( "select distinct p from Animal p inner join fetch p.offspring" ).list();
		assertEquals( "duplicate list() returns", 30, results.size() );
		// Test first/max
		results = s.createQuery( "select p from Animal p inner join fetch p.offspring order by p.id" )
				.setFirstResult( 5 )
				.setMaxResults( 20 )
				.list();
		assertEquals( "duplicate returns", 20, results.size() );
		Animal firstReturn = ( Animal ) results.get( 0 );
		assertEquals( "firstResult not applied correctly", "123-5", firstReturn.getSerialNumber() );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete Animal where mother is not null" ).executeUpdate();
		s.createQuery( "delete Animal" ).executeUpdate();
		t.commit();
		s.close();
	}
