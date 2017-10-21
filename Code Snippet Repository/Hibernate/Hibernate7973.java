	@Test
	@SuppressWarnings( {"", ""})
	public void testCachedJoinedAndJoinFetchedOneToMany() throws Exception {
		Animal a = new Animal();
		a.setDescription( "an animal" );
		Animal mother = new Animal();
		mother.setDescription( "a mother" );
		mother.addOffspring( a );
		a.setMother( mother );
		Animal offspring1 = new Animal();
		offspring1.setDescription( "offspring1" );
		Animal offspring2 = new Animal();
		offspring1.setDescription( "offspring2" );
		a.addOffspring( offspring1 );
		offspring1.setMother( a );
		a.addOffspring( offspring2 );
		offspring2.setMother( a );

		sessionFactory().getCache().evictQueryRegions();
		sessionFactory().getStatistics().clear();

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.save( mother );
		s.save( a );
		s.save( offspring1 );
		s.save( offspring2 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List list = s.createQuery( "from Animal a left join fetch a.offspring" ).setCacheable( true ).list();
		assertEquals( 0, sessionFactory().getStatistics().getQueryCacheHitCount() );
		assertEquals( 1, sessionFactory().getStatistics().getQueryCachePutCount() );
		list = s.createQuery( "select a from Animal a left join fetch a.offspring" ).setCacheable( true ).list();
		assertEquals( 1, sessionFactory().getStatistics().getQueryCacheHitCount() );
		assertEquals( 1, sessionFactory().getStatistics().getQueryCachePutCount() );
		list = s.createQuery( "select a, o from Animal a left join a.offspring o" ).setCacheable( true ).list();
		assertEquals( 1, sessionFactory().getStatistics().getQueryCacheHitCount() );
		assertEquals( 2, sessionFactory().getStatistics().getQueryCachePutCount() );
		list = s.createQuery( "from Animal" ).list();
		for ( Object obj : list ) {
			s.delete( obj );
		}
		t.commit();
		s.close();
	}
