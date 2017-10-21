	@Test
	@TestForIssue( jiraKey = "" )
	public void testHitCacheInSameSession() {
		sessionFactory().getCache().evictQueryRegions();
		sessionFactory().getStatistics().clear();
		Session s = openSession();
		List list = new ArrayList();
		s.beginTransaction();
		for ( int i = 0; i < 3; i++ ) {
			Item a = new Item();
			a.setName( "a" + i );
			a.setDescription( "a" + i );
			list.add( a );
			s.persist( a );
		}
		s.getTransaction().commit();

//		s.close();
//		s=openSession();

		s.beginTransaction();
		String queryString = "from Item";
		// this query will hit the database and create the cache
		s.createQuery( queryString ).setCacheable( true ).list();
		s.getTransaction().commit();

		s.beginTransaction();
		//and this one SHOULD served by the cache
		s.createQuery( queryString ).setCacheable( true ).list();
		s.getTransaction().commit();
		QueryStatistics qs = s.getSessionFactory().getStatistics().getQueryStatistics( queryString );
		assertEquals( 1, qs.getCacheHitCount() );
		assertEquals( 1, qs.getCachePutCount() );
		s.close();
		s = openSession();
		s.beginTransaction();
		for(Object obj:list){
			s.delete( obj );
		}
		s.getTransaction().commit();
		s.close();

	}
