	@Test
	public void testClassQueries() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Night n = new Night();
		Calendar c = new GregorianCalendar();
		c.set( 2000, 2, 2 );
		Date now = c.getTime();
		c.add( Calendar.MONTH, -1 );
		Date aMonthAgo = c.getTime();
		c.add( Calendar.MONTH, 2 );
		Date inAMonth = c.getTime();
		n.setDate( now );
		n.setDuration( 14 );
		s.persist( n );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Query q = s.getNamedQuery( "night.moreRecentThan" );
		q.setDate( "date", aMonthAgo );
		assertEquals( 1, q.list().size() );
		q = s.getNamedQuery( "night.moreRecentThan" );
		q.setDate( "date", inAMonth );
		assertEquals( 0, q.list().size() );
		Statistics stats = sessionFactory().getStatistics();
		stats.setStatisticsEnabled( true );
		stats.clear();
		q = s.getNamedQuery( "night.duration" );
		q.setParameter( "duration", 14l );
		assertEquals( 1, q.list().size() );
		assertEquals( 1, stats.getQueryCachePutCount() );
		q = s.getNamedQuery( "night.duration" );
		q.setParameter( "duration", 14l );
		s.delete( q.list().get( 0 ) );
		assertEquals( 1, stats.getQueryCacheHitCount() );
		tx.commit();
		s.close();
	}
