	@Test
	public void testSQLQueryWithManyToOne() {
		cleanupCache();
		Night n = new Night();
		Calendar c = new GregorianCalendar();
		c.set( 2000, 2, 2 );
		Date now = c.getTime();
		c.add( Calendar.MONTH, -1 );
		Date aMonthAgo = c.getTime();
		c.add( Calendar.MONTH, 2 );
		Date inAMonth = c.getTime();
		n.setDate( now );
		n.setDuration( 9999 );
		Area a = new Area();
		a.setName( "Paris" );
		n.setArea( a );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( a );
		s.persist( n );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Statistics stats = sessionFactory().getStatistics();
		stats.setStatisticsEnabled( true );
		stats.clear();
		Query q = s.getNamedQuery( "night&areaCached" );
		q.setCacheable( true );
		List result = q.list();
		assertEquals( 1, result.size() );
		assertEquals( 1, stats.getQueryCachePutCount() );
		q.setCacheable( true );
		q.list();
		assertEquals( 1, stats.getQueryCacheHitCount() );
		Night n2 = (Night) ( (Object[]) result.get( 0 ) )[0];
		assertEquals( n2.getDuration(), n.getDuration() );
		s.delete( n2.getArea() );
		s.delete( n2 );
		tx.commit();
		s.close();
	}
